package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sqlTableObjects.Base;
import code.QueryService;

@Path("world/")
@Produces(MediaType.APPLICATION_JSON)
public class WorldResources {
	
	@POST
	@Path("bases")
	public Response getBases(@FormParam("username") String username) {
		try {
			System.out.println("Get Bases Request Received");
			List<Base> bases = QueryService.getUserBases(username);
			return Response.ok().entity(new GenericEntity<List<Base>>(bases) {}).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
	
	@POST
	@Path("bases/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBase(@BeanParam TestInfo testInfo) {
		System.out.println("create base request received");
		System.out.println(testInfo.score);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response test(TestInfo ti) throws JsonParseException, JsonMappingException, IOException {
		try {
		System.out.println("Test hit");
		
		return Response.ok().entity(ti).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
}
