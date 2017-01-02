package com.stolzalexander.nutri.business.food.boundary;

import com.stolzalexander.nutri.business.food.entity.Food;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author alexanderstolz
 */
@Stateless
@Path("foods")
public class FoodResource {
    
    @Inject
    FoodManager manager;
    
    @GET
    @Path("{id}")
    public Food find(@PathParam("id") long id) {
        return manager.findById(id);
    }
    
    @GET
    public List<Food> all() {
        return manager.all();
    }
    
    @POST
    public Response save(Food food, @Context UriInfo info) {
        Food saved = manager.save(food);
        long id = saved.getId();
        URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
        
        return Response.created(uri).build();
    }
    
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        manager.delete(id);
    }
    
    @PUT
    @Path("{id}")
    public Food update(@PathParam("id") long id, Food food) {
        food.setId(id);
        return manager.save(food);
    }
}
