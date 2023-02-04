package com.tecraa.openweather.Forecast;

import com.google.gson.annotations.SerializedName;

public class Wind{

	@SerializedName("deg")
	private int deg;

	@SerializedName("speed")
	private Object speed;

	@SerializedName("gust")
	private Object gust;

	public void setDeg(int deg){
		this.deg = deg;
	}

	public int getDeg(){
		return deg;
	}

	public void setSpeed(Object speed){
		this.speed = speed;
	}

	public Object getSpeed(){
		return speed;
	}

	public void setGust(Object gust){
		this.gust = gust;
	}

	public Object getGust(){
		return gust;
	}

	@Override
 	public String toString(){
		return 
			"Wind{" + 
			"deg = '" + deg + '\'' + 
			",speed = '" + speed + '\'' + 
			",gust = '" + gust + '\'' + 
			"}";
		}
}