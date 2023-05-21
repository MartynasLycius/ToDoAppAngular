package com.todo.sample.resource;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.todo.sample.entity.Item;
import com.todo.sample.service.ItemService;

/**
 * Created by Md. Zohir Raihan on 5/21/2023.
 */

@Path("/items")
public class ItemResource {

    @Context
    UriInfo uriInfo;

    @EJB
    ItemService itemService;

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response create(Item item) {
        itemService.create(item);

        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(item.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Item find(@PathParam("id") int id) {
        return itemService.find(id);
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Item> find() {
        return itemService.find();
    }

    @PUT
    @Path("{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Item item) {
    	Item existingPlayer = itemService.find(id);
    	if(existingPlayer == null) {
    		itemService.update(existingPlayer);
            return Response.noContent().build();
    	}    	
    	return Response.status(422).build();
    }
}
