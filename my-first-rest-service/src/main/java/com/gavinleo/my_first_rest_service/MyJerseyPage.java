package com.gavinleo.my_first_rest_service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class MyJerseyPage {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello( @QueryParam("yourName") String yourName, @QueryParam("yourLocation") String yourLocation ) {
		return "Wha a gwaan, " + yourName + " in " + yourLocation + "?";
		
		//http://localhost:8080/my-first-rest-service/thisthat/hello?yourName=Gavin&yourLocation=NJ
	}
	
}
