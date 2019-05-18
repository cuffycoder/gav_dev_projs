package com.gavinleo.weather_service;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherResult {

	private int locationID;
	private String locationName;
	private double tempLowInKelvin;
	private double tempHighInKelvin;
	private double tempCurrentInKelvin;
	private ArrayList<String> conditions;
	
	public WeatherResult( String openWeatherResultJSON ) {		
		JSONObject resultAsJSON = new JSONObject( openWeatherResultJSON );
		
		this.locationID = resultAsJSON.getInt( "id" );
		this.locationName = resultAsJSON.getString( "name");
		
		JSONObject sectionMain = resultAsJSON.getJSONObject( "main" );
		
		this.tempLowInKelvin = sectionMain.getDouble( "temp_min" );
		this.tempHighInKelvin = sectionMain.getDouble( "temp_max" );
		this.tempCurrentInKelvin = sectionMain.getDouble( "temp" );		
		
		JSONArray conditionsJSON = resultAsJSON.getJSONArray( "weather" );
		
		int numEntries = conditionsJSON.length();
		
		conditions = new ArrayList<String>();
		
		for( int i = 0; i < numEntries; i++ ) {
			JSONObject condition = conditionsJSON.getJSONObject( i );
			
			conditions.add( condition.getString( "description"));
		}
	}
	
	private double kelvinToCelsius( double tempInK ) {
		return tempInK - 273.15;
	}
	
	private double  kelvinToFahrenheit( double tempInK ) {
		return ( ( tempInK - 273.15 ) * 9/5 ) + 32;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public int getLocationID() {
		return locationID;
	}
	public double getTempCurrentInF() {
		return this.kelvinToFahrenheit( this.tempCurrentInKelvin );
	}
	
	public double getTempLowInF() {
		return this.kelvinToFahrenheit( this.tempLowInKelvin );
	}
	
	public double getTempHighInF() {
		return this.kelvinToFahrenheit( this.tempHighInKelvin );
	}
	
	public double getTempCurrentInC() {
		return this.kelvinToCelsius( this.tempCurrentInKelvin );
	}
	
	public double getTempLowInC() {
		return this.kelvinToCelsius( this.tempLowInKelvin );
	}
	
	public double getTempHighInC() {
		return this.kelvinToCelsius( this.tempHighInKelvin );
	}
	
	public ArrayList<String> getConditions() {
		return this.conditions;
	}
	
	public String getFormattedReport() {
		String report = "";
		
		report += String.format( "Location: %s\n", this.getLocationName() );
		report += "Temperatures:\n";
		report += String.format( "Current: %.1fF / %.1fC\n", this.getTempCurrentInF(), this.getTempCurrentInC() );
		report += String.format( "Low:     %.1fF / %.1fC\n", this.getTempLowInF(), this.getTempLowInC() );
		report += String.format( "High:    %.1fF / %.1fC\n", this.getTempHighInF(), this.getTempHighInC() );

		report += "Conditions: ";
		String delim = "";
		
		for( String condition : this.conditions ) {
			report += delim + condition;
			delim = ", ";
		}
		
		return report;
	}
}
