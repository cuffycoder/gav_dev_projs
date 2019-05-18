package com.gavinleo.weather_service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WeatherGrabber {

	private String openWeatherAPPID = "6420ea6fe44f191b545df92c8b8115c4";

	public WeatherGrabber() {
	}

	public String getWeather(String cityName, String countryCode) {

		String urlAsString = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s,%s&APPID=%s", cityName,
				countryCode, this.openWeatherAPPID);

		String result = "";
		String output = "";
		try {
			URL url = new URL(urlAsString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}

			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			while ((output = br.readLine()) != null) {
				result += output;
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
