package com.tecraa.openweather;

import com.tecraa.openweather.Forecast.ResponseForecast;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ForecastDTO {
    @GET("forecast?q=Dhaka&units=metric&appid="+Constants.API_KEY)
    Call<ResponseForecast> getForecastWeather();

}
