package com.tecraa.openweather;

import com.tecraa.openweather.Current.ResponseWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherDTO {
    //@GET("weather?q=Dhaka&units=metric&appid="+Constants.API_KEY)
    @GET("weather?"+Constants.API_KEY)
    Call<ResponseWeather> getCurrentWeather(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("units") String units,
            @Query("appid") String appid
    );
}
