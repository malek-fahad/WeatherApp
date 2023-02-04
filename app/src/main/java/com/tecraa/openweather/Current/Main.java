package com.tecraa.openweather.Current;

import com.google.gson.annotations.SerializedName;

public class Main{

	@SerializedName("temp")
	private Object temp;

	@SerializedName("temp_min")
	private Object tempMin;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("pressure")
	private int pressure;

	@SerializedName("feels_like")
	private Object feelsLike;

	@SerializedName("temp_max")
	private Object tempMax;

	public Object getTemp(){
		return temp;
	}

	public Object getTempMin(){
		return tempMin;
	}

	public int getHumidity(){
		return humidity;
	}

	public int getPressure(){
		return pressure;
	}

	public Object getFeelsLike(){
		return feelsLike;
	}

	public Object getTempMax(){
		return tempMax;
	}

	@Override
 	public String toString(){
		return 
			"Main{" + 
			"temp = '" + temp + '\'' + 
			",temp_min = '" + tempMin + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",pressure = '" + pressure + '\'' + 
			",feels_like = '" + feelsLike + '\'' + 
			",temp_max = '" + tempMax + '\'' + 
			"}";
		}
}