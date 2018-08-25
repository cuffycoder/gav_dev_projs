package com.gavinleo.my_first_rest_service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/goodbye")
public class SecondPage {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlGoodbye( @QueryParam("yourName") String yourName, @QueryParam("yourLocation") String yourLocation ) {
		return "Likkle more, " + yourName + " in " + yourLocation + "?";
		
		//http://localhost:8080/my-first-rest-service/TalkToMe/goodbye?yourName=Gavin&yourLocation=NJ
	}
	
}
