package com.gavinleo.weather_service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/grabWeather")
public class WeatherGrabberREST {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getWeatherForCityAndCountry(@QueryParam("cityName") String cityName,
			@QueryParam("countryCode") String countryCode ) {
		WeatherGrabber weatherNow = new WeatherGrabber();

		String weather = weatherNow.getWeather(cityName, countryCode);

		WeatherResult result = new WeatherResult(weather);

		String report = result.getFormattedReport();

		return report;

		// http://localhost:8080/weather_service/WeatherService/grabWeather?cityName=Jakarta&countryCode=ID
	}

}
