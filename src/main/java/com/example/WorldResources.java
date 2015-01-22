package com.example;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import code.QueryService;
import jsonObjects.BaseLocation;

@Path("world/")
public class WorldResources {
	
	@POST
	@Path("bases")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBases(@FormParam("username") String username) {
		List<BaseLocation> bases = QueryService.getBases(username);
		return Response.ok().entity(bases).build();
	}
}
