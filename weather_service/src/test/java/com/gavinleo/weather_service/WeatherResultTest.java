package com.gavinleo.weather_service;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeatherResultTest {

	@Test
	public void test() {
		
		
		String testString = "{\"coord\":{\"lon\":106.83,\"lat\":-6.18},\"weather\":[{\"id\":721,\"main\":\"Haze\",\"description\":\"haze\",\"icon\":\"50n\"},{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50n\"}],\"base\":\"stations\",\"main\":{\"temp\":298.92,\"pressure\":1010,\"humidity\":83,\"temp_min\":298.71,\"temp_max\":299.15},\"visibility\":4000,\"wind\":{\"speed\":1.73,\"deg\":341.586},\"clouds\":{\"all\":20},\"dt\":1558132148,\"sys\":{\"type\":1,\"id\":9383,\"message\":0.0049,\"country\":\"ID\",\"sunrise\":1558133664,\"sunset\":1558176239},\"id\":1642911,\"name\":\"Jakarta\",\"cod\":200}\n" + 
				"";
				
		WeatherResult result = new WeatherResult( testString );
		
		System.out.print( result.getFormattedReport() );
		assertTrue( ( result.getTempCurrentInC() - 25.8 ) < 0.1 );
		assertTrue( ( result.getTempCurrentInF() - 78.4 ) < 0.1 );
	}

}
