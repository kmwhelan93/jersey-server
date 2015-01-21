package com.example;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;


import javax.ws.rs.core.MediaType;

import code.HelloWorld;
import jsonObjects.AuthenticateBean;

@Path("authenticate")
public class AuthenticateResource {
	public static HelloWorld hw = new HelloWorld();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public AuthenticateBean authenticate(@FormParam("username") String username,
			@FormParam("password") String password) {
		boolean result = hw.authenticate(username, password);
		System.out.println("Authentication request: " + username + " " + password);
		return new AuthenticateBean(result);
	}
}
