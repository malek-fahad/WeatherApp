package com.tecraa.openweather;

import com.tecraa.openweather.Current.ResponseWeather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherDTO {
    @GET("weather?q=Dhaka&units=metric&appid="+Constants.API_KEY)
    Call<ResponseWeather> getCurrentWeather();
}
