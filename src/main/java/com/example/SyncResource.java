package com.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import code.GameLogicService;
import code.QueryService;
import jsonObjects.GoldInfo;
import jsonObjects.GoldSync;


@Path("sync")
@Produces(MediaType.APPLICATION_JSON)
public class SyncResource {
	
	@Path("gold")
	@GET
	public Response syncGold() {
		GoldSync gs = GameLogicService.syncGold("kmw8sf");
		return Response.ok().entity(gs).build();
	}
}
