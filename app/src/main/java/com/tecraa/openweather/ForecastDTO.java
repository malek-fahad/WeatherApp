package com.tecraa.openweather;

import com.tecraa.openweather.Forecast.ResponseForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForecastDTO {
    //@GET("forecast?q=Dhaka&units=metric&appid="+Constants.API_KEY)
    @GET("forecast?")
    Call<ResponseForecast> getForecastWeather(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("units") String units,
            @Query("appid") String appid

    );

}
