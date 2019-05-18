package com.gavinleo.weather_service;


import org.junit.Test;

import junit.framework.TestCase;

public class WeatherGrabberTest extends TestCase {

	@Test
	public void testGetWeatherForCityCountry() {
		WeatherGrabber weatherNow = new WeatherGrabber();

//		String weather = weatherNow.getWeather("jakarta", "id");
		String weather = weatherNow.getWeather("07078", "US");

		WeatherResult result = new WeatherResult( weather );
	
		System.out.println(result.getFormattedReport());
	}

}
