package com.tecraa.openweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tecraa.openweather.Forecast.ListItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {
    Context context;
    List<ListItem> forecastList;

        public ForecastAdapter(Context context, List<ListItem> forecastList) {
        this.context = context;
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ForecastViewHolder(LayoutInflater.from(context).inflate(R.layout.item_forecast_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {

        ListItem listItem = forecastList.get(position);

        //String temp = String.valueOf(listItem.getMain().getTemp());

        holder.itemListTemperatureTV.setText(listItem.getMain().getTemp().toString());
        Date date = new Date(listItem.getDt());
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        DateFormat weekName = new SimpleDateFormat("EEEE");


        holder.itemListWeekNameTV.setText(weekName.format(date));

        holder.itemListDateTV.setText(dateFormat.format(date));



        String iconSrt = listItem.getWeather().get(0).getIcon();
        if (iconSrt.equals("50n")||iconSrt.equals("50d")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_mist);
        }else if (iconSrt.equals("13n")||iconSrt.equals("13d")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_snow);
        }else if (iconSrt.equals("11d")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_thunderstorm_sun);
        }else if (iconSrt.equals("11n")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_thunderstorm_moon);
        }else if (iconSrt.equals("10d")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_sun_rain);
        }else if (iconSrt.equals("10n")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_moon_rain);
        }else if (iconSrt.equals("09n")||iconSrt.equals("09d")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_shower_rain);
        }else if (iconSrt.equals("04n")||iconSrt.equals("04d")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_broken_clouds);
        }else if (iconSrt.equals("03n")||iconSrt.equals("03d")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_clouds);
        }else if (iconSrt.equals("02d")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_cloud_sun);
        }else if (iconSrt.equals("02n")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_cloud_moon);
        }else if (iconSrt.equals("01d")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_sun);
        }else if (iconSrt.equals("01n")){
            holder.itemListIcon.setImageResource(R.drawable.img_ic_moon);
        }else {
            holder.itemListIcon.setImageResource(R.drawable.img_ic_clouds);
        }




    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }
}
