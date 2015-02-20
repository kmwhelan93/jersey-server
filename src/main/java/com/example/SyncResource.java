package com.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jsonObjects.GoldSync;


@Path("sync")
@Produces(MediaType.APPLICATION_JSON)
public class SyncResource {
	
	@Path("gold")
	@GET
	public Response syncGold() {
		return Response.ok().entity(new GoldSync(10, 1)).build();
	}
}
