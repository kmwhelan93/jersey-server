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
import sqlTableObjects.WormHoleObj;
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
		
		List<WormHoleObj> newWormholes = GameLogicService.createNewWormholes(toAdd);
		System.out.println(newWormholes);
		for (WormHoleObj wormhole : newWormholes) {
			QueryService.persistNewWormHole(wormhole);
		}
		return Response.ok().entity("Base created!").build();
	}
	
	@POST
	@Path("bases/valid")
	public Response getValidBaseIds(@FormParam("username") String username,
			@FormParam("base1Id") int base1Id) {
		int[] validBaseIds = QueryService.getValidBaseIds(username, base1Id);
		String baseIds = "";
		if (validBaseIds.length > 0) {
			baseIds += validBaseIds[0] + "";
			for (int i = 1; i < validBaseIds.length; i++) {
				baseIds += ";" + validBaseIds[i];
			}
		}
		return Response.ok().entity(baseIds).build();
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
	
	@POST
	@Path("portals/create")
	public Response createPortals(@FormParam("username") String username, 
			@FormParam("baseId1") int baseId1, 
			@FormParam("baseId2") int baseId2,
			@FormParam("timeFinished") long timeFinished,
			@FormParam("cost") int cost) {
		System.out.println("Create Portals Request Received");
		// NOTE: This method consumes the data like this because I don't think
		// it is capable of receiving a BaseObj[]
		if (!QueryService.portalExists(username, baseId1, baseId2)) {
			QueryService.createPortal(username, baseId1, baseId2, timeFinished);
			QueryService.decrementGold(username, cost);
			return Response.ok().entity("Portal creation started!").build();
		}
		else {
			System.out.println("Portal already exists");
			return Response.ok().entity("Portal already exists").build();
		}
	}
	
	@POST
	@Path("troops/buy")
	public Response buyTroops(@FormParam("username") String username,
			@FormParam("baseId") int baseId,
			@FormParam("numTroops") int numTroops,
			@FormParam("costPerTroop") int costPerTroop) {
		// Update base's num units
		QueryService.addTroops(username, baseId, numTroops);
		// Update user's gold (WILL STILL NEED TO sync/gold FROM CLIENT)
		QueryService.decrementGold(username, numTroops * costPerTroop);
		return Response.ok().build();
	}
	
	@POST
	@Path("troops/startMove")
	public Response getPortal(@FormParam("username") String username, 
			@FormParam("baseId1") int baseId1, 
			@FormParam("baseId2") int baseId2,
			@FormParam("numTroops") int numTroops) {
		// Update move info in Portals table and return portal
		try {
			Portal p = QueryService.getAndUpdatePortal(username, baseId1, baseId2, numTroops);
			return Response.ok().entity(mapper.writeValueAsString(p)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
	
	@POST
	@Path("troops/restartMove")
	public Response restartMove(@FormParam("username") String username) {
		// Get all portals with troops to move
		try {
			Portal[] p = QueryService.getMovePortals(username);
			return Response.ok().entity(mapper.writeValueAsString(p)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
	
	@POST
	@Path("troops/finishMove")
	public Response finishMove(@FormParam("username") String username, 
			@FormParam("portalId") int portalId) {
		// Update move info in Portals table
		try {
			QueryService.finishMoveTroops(username, portalId);
			return Response.ok().entity("Units moved!").build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.ok().build();
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
	
	@POST
	@Path("wormholes")
	public Response getWormHoles(@FormParam("username") String username) {
		try {
			System.out.println("Get WormHoles Request Received for " + username);
			List<WormHoleObj> wormholes = QueryService.getWormholes(username.trim());
			return Response.ok().entity(mapper.writeValueAsString(wormholes)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
}
