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

import sqlTableObjects.BaseObj;
import sqlTableObjects.Portal;
import code.GameLogicService;
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
			List<BaseObj> bases = QueryService.getUserBases(username);
			return Response.ok().entity(mapper.writeValueAsString(bases)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
	
	@POST
	@Path("bases/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBase(BaseObj referenceBase) {
		System.out.println("createBase Request Received");
		String username = referenceBase.username;
		List<BaseObj> bases = QueryService.getUserBases(username);
		int magnitude = 1;
		BaseObj toAdd = null;
		outer: while (true) {
			int initialDirection = random.nextInt(4);
			for (int i = 0; i < 4; i++) {
				int direction = (i + initialDirection) % 4;
				Point p = Point.getPoint(direction).scale(magnitude).add(referenceBase.world);
				BaseObj newBase = new BaseObj(username, p, Point.getRandomDirection());
				newBase.prodRate = random.nextInt(200) + 10;
				if (!newBase.isSpaceOccupied(bases)) {
					toAdd = newBase;
					break outer;
				}
				
			}
			magnitude++;
		}
		int baseId = QueryService.persistNewBase(toAdd);
		QueryService.createPortal(referenceBase.username, referenceBase.baseId, toAdd.baseId, System.currentTimeMillis());
		return Response.ok().entity("Base created!").build();
	}
	
	@POST
	@Path("portals")
	public Response getPortals(String username) {
		try {
			System.out.println("Get Portals Request Received");
			List<Portal> portals = QueryService.getPortals(username);
			return Response.ok().entity(mapper.writeValueAsString(portals)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
	
//	@POST
//	@Path("portals/unfinished")
//	public Response getUnfinishedPortals(String username) {
//		try {
//			System.out.println("Get Portals Request Received");
//			List<Portal> portals = QueryService.getUnfinishedPortals(username);
//			return Response.ok().entity(mapper.writeValueAsString(portals)).build();
//		} catch(Exception e) {
//			e.printStackTrace();
//			return Response.ok().build();
//		}
//	}
	
	@POST
	@Path("portals/create")
	public Response createPortals(@FormParam("username") String username, 
			@FormParam("baseId1") int baseId1, 
			@FormParam("baseId2") int baseId2,
			@FormParam("timeFinished") long timeFinished) {
		System.out.println("Create Portals Request Received");
		// NOTE: This method consumes the data like this because I don't think
		// it is capable of receiving a BaseObj[]
		if (!QueryService.portalExists(username, baseId1, baseId2)) {
			QueryService.createPortal(username, baseId1, baseId2, timeFinished);
			return Response.ok().entity("Portal creation started!").build();
		}
		else {
			System.out.println("Portal already exists");
			return Response.ok().entity("Portal already exists").build();
		}
	}
	
	@POST
	@Path("troops/move")
	public Response moveTroops(@FormParam("username") String username,
			@FormParam("baseId1") int baseId1,
			@FormParam("baseId2") int baseId2,
			@FormParam("numTroops") int numTroops) {
		System.out.println("Move Troops Request Received");
		// Check portal exists between bases
		if (QueryService.portalExists(username, baseId1, baseId2)) {
			// Check that base1 has units >= numTroops
			if (QueryService.getNumTroops(username, baseId1) >= numTroops) {
				// Move troops
				QueryService.moveTroops(username, baseId1, baseId2, numTroops);
				return Response.ok().entity("Units moved!").build();
			}
			else {
				System.out.println("Not enough troops");
				return Response.ok().entity("Not enough units").build();
			}
		}
		else {
			System.out.println("No portal exists between bases");
			return Response.ok().entity("No portal exists between bases").build();
		}
	}
	
	@POST
	@Path("clear")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response clearWorld(BaseObj avoid) {
		System.out.println("clear request received");
		// clear everything but this base
		List<BaseObj> bases = QueryService.getUserBases(avoid.username);
		for (BaseObj b : bases) {
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
