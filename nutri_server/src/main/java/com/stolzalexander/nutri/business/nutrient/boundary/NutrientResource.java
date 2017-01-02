package com.stolzalexander.nutri.business.nutrient.boundary;

import com.stolzalexander.nutri.business.food.entity.Food;
import com.stolzalexander.nutri.business.nutrient.entity.Nutrient;
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
@Path("nutrients")
public class NutrientResource {
    
    @Inject
    NutrientManager manager;
    
    @GET
    @Path("{id}")
    public Nutrient find(@PathParam("id") long id) {
        return manager.findById(id);
    }
    
    @GET
    public List<Nutrient> all() {
        return manager.all();
    }
    
    @POST
    public Response save(Nutrient nutrient, @Context UriInfo info) {
        Nutrient saved = manager.save(nutrient);
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
    public Nutrient update(@PathParam("id") long id, Nutrient nutrient) {
        nutrient.setId(id);
        return manager.save(nutrient);
    }
}
