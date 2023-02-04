package com.tecraa.openweather.Current;

import com.google.gson.annotations.SerializedName;

public class Coord{

	@SerializedName("lon")
	private Object lon;

	@SerializedName("lat")
	private Object lat;

	public Object getLon(){
		return lon;
	}

	public Object getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"Coord{" + 
			"lon = '" + lon + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}