package com.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("sync")
@Produces(MediaType.APPLICATION_JSON)
public class SyncResource {

	@Path("gold")
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public Response syncGold() {
		System.out.println("Sync Gold Request Received");
		return Response.ok().entity("10").build();
	}
}
