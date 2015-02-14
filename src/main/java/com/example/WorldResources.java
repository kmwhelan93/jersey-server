package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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




import jsonObjects.Point;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sqlTableObjects.Base;
import sqlTableObjects.Portal;
import code.QueryService;

@Path("world/")
@Produces(MediaType.APPLICATION_JSON)
public class WorldResources {
	
	private static Random random = new Random();
	private static ObjectMapper mapper = new ObjectMapper();
	
	@POST
	@Path("bases")
	public Response getBases(@FormParam("username") String username) {
		try {
			System.out.println("Get Bases Request Received");
			List<Base> bases = QueryService.getUserBases(username);
			return Response.ok().entity(mapper.writeValueAsString(bases)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
	
	@POST
	@Path("portals")
	public Response getPortals(@FormParam("username") String username) {
		try {
			System.out.println("Get Portals Request Received");
			List<Portal> portals = QueryService.getPortals(username);
			return Response.ok().entity(mapper.writeValueAsString(portals)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
	
	@POST
	@Path("bases/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBase(Base referenceBase) {
		System.out.println("createBase Request Received");
		String username = referenceBase.username;
		List<Base> bases = QueryService.getUserBases(username);
		int magnitude = 1;
		Base toAdd = null;
		outer: while (true) {
			int initialDirection = random.nextInt(4);
			for (int i = 0; i < 4; i++) {
				int direction = (i + initialDirection) % 4;
				Point p = Point.getPoint(direction).scale(magnitude).add(referenceBase.world);
				Base newBase = new Base(username, p, Point.getRandomDirection());
				if (!bases.contains(newBase)) {
					toAdd = newBase;
					break outer;
				}
				
			}
			magnitude++;
		}
		QueryService.persistNewBase(toAdd);
		System.out.println(QueryService.createPortal(referenceBase.username, referenceBase.baseId, toAdd.baseId));
		return Response.ok().build();
	}
	
	@POST
	@Path("clear")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response clearWorld(Base avoid) {
		System.out.println("clear request received");
		// clear everything but this base
		List<Base> bases = QueryService.getUserBases(avoid.username);
		for (Base b : bases) {
			if (!b.equals(avoid)) {
				QueryService.disownBase(b.baseId);
			}
		}
		QueryService.disownPortals(avoid.username);
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
