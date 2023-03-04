package com.tecraa.openweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tecraa.openweather.Forecast.ListItem;
import com.tecraa.openweather.Forecast.ResponseForecast;
import com.tecraa.openweather.databinding.ActivityMainBinding;
import com.tecraa.openweather.Current.ResponseWeather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    List<ListItem> forecastList;

    ForecastAdapter adapter;

    Location location;
    double lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();




        Shader shader = new LinearGradient(0, 70, 0, binding.currentTemperatureTV.getTextSize(), Color.WHITE, getResources().getColor(R.color.lightDark),
                Shader.TileMode.CLAMP);
        binding.currentTemperatureTV.getPaint().setShader(shader);

        Dexter.withContext(this)
                .withPermissions(
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
                    @Override public void onPermissionsChecked(MultiplePermissionsReport report) {

                        location = getLocation();


                        if (location!=null){
                            lat = location.getLatitude();
                            lng = location.getLongitude();
                        }
                        callCurrentWeather();
                        calForecastWeather();

                    }
                    @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                }).check();




//       forecastList = new ArrayList<>();
//       forecastList.add(new Test("malek","21"));
//       forecastList.add(new Test("khalek","21"));
//       forecastList.add(new Test("satter","21"));
//       forecastList.add(new Test("aysa","21"));
//       adapter = new ForecastAdapter(MainActivity.this, forecastList);
//
//        binding.forecastRV.setAdapter(adapter);
//
//       String tt = String.valueOf(forecastList.size());
//
//        binding.test.setText(tt);




    }

    private void calForecastWeather() {

        forecastList = new ArrayList<>();

        ForecastDTO forecastDTO = RetrofitClient.getRetrofit().create(ForecastDTO.class);



        Call<ResponseForecast> forecastCall = forecastDTO.getForecastWeather(lat,lng,"metric",Constants.API_KEY);
        forecastCall.enqueue(new Callback<ResponseForecast>() {
            @Override
            public void onResponse(Call<ResponseForecast> call, Response<ResponseForecast> response) {
                Log.i("TAG", "onResponse: "+response.code());


                forecastList.addAll(response.body().getList());
                binding.currentCityTV.setText(response.body().getCity().getName());


//                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
//                    response.body().getList().forEach(p->{
//
//
//                        double temperature = (double) p.getMain().getTemp();
//                        long date =p.getDt();
//                        String icon = p.getWeather().get(0).getIcon();
//                        Log.i("TAG", "onResponse: "+temperature);
//
//
//                    });
//                }
                adapter = new ForecastAdapter(MainActivity.this, forecastList);
                binding.forecastRV.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<ResponseForecast> call, Throwable t) {
                Log.i("TAG", "onFailure: "+t.getMessage());
            }
        });

    }

    private void callCurrentWeather() {
        WeatherDTO dto = RetrofitClient.getRetrofit().create(WeatherDTO.class);

        Call<ResponseWeather> weatherCall = dto.getCurrentWeather(lat,lng,"metric",Constants.API_KEY);

        weatherCall.enqueue(new Callback<ResponseWeather>() {
            @Override
            public void onResponse(Call<ResponseWeather> call, Response<ResponseWeather> response) {
                if (response.isSuccessful()){
                    binding.currentTemperatureTV.setText(response.body().getMain().getTemp().toString());
                    //binding.currentCityTV.setText(response.body().getName());
                    binding.currentWeatherNameTV.setText(response.body().getWeather().get(0).getDescription());
                    Date date = new Date(System.currentTimeMillis());
                    DateFormat dateFormat = new SimpleDateFormat("EEEE, DD MMM");
                    binding.todayDateTV.setText(dateFormat.format(date));
                    String iconSrt = response.body().getWeather().get(0).getIcon();
                    if (iconSrt.equals("50n")||iconSrt.equals("50d")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_mist);
                    }else if (iconSrt.equals("13n")||iconSrt.equals("13d")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_snow);
                    }else if (iconSrt.equals("11d")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_thunderstorm_sun);
                    }else if (iconSrt.equals("11n")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_thunderstorm_moon);
                    }else if (iconSrt.equals("10d")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_sun_rain);
                    }else if (iconSrt.equals("10n")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_moon_rain);
                    }else if (iconSrt.equals("09n")||iconSrt.equals("09d")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_shower_rain);
                    }else if (iconSrt.equals("04n")||iconSrt.equals("04d")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_broken_clouds);
                    }else if (iconSrt.equals("03n")||iconSrt.equals("03d")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_clouds);
                    }else if (iconSrt.equals("02d")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_cloud_sun);
                    }else if (iconSrt.equals("02n")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_cloud_moon);
                    }else if (iconSrt.equals("01d")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_sun);
                    }else if (iconSrt.equals("01n")){
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_moon);
                    }else {
                        binding.weatherIconIV.setImageResource(R.drawable.img_ic_clouds);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseWeather> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_SHORT).show();

            }
        });

    }
    public Location getLocation(){
        Location location =null;
        Location bestLocation = null;

        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        List<String>provider = locationManager.getProviders(true);
        for (String p: provider){
            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                return null;
            }
            location = locationManager.getLastKnownLocation(p);

            if (location==null){
                continue;
            }
            if (bestLocation==null||location.getAccuracy()>bestLocation.getAccuracy()){
                bestLocation = location;
            }

        }


        return bestLocation;
    }
}