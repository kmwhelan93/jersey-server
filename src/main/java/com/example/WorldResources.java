package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
			System.out.println(username);
			System.out.println(bases);
			return Response.ok().entity(new GenericEntity<List<Base>>(bases) {}).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
	
	@POST
	@Path("test")
	public Response test() {
		System.out.println("test request received");
		return Response.ok().entity("{\"hi\":3}").build();
	}
}
