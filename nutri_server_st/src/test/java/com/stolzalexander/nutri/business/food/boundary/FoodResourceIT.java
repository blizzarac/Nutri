package com.stolzalexander.nutri.business.food.boundary;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import static com.airhacks.rulz.jaxrsclient.JAXRSClientProvider.buildWithURI;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Rule;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author alexanderstolz
 */
public class FoodResourceIT {

    @Rule
    public JAXRSClientProvider provider = buildWithURI("http://localhost:53598/nutri_server/api/foods");
    
    @After
    public void afterClass() {
//        Response response = this.provider.target().request(MediaType.APPLICATION_JSON).get();
//        JsonArray allTodos = response.readEntity(JsonArray.class);
//        
//        for (JsonValue val :allTodos) {
//             Response postResponse = this.provider.target().request().post(val);
//        }
    }
    
    @Test
    public void createAndFindFood() {
        JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
        JsonObject foodToCreate = todoBuilder
                .add("caption", "Joylent Sport")
                .add("description", "Full bag 660g")
                .build();
        
        Response postResponse = this.provider.target().request().post(Entity.json(foodToCreate));
        assertThat(postResponse.getStatus(), is(201));
        
        String location = postResponse.getHeaderString("Location");
        
        JsonObject dedicatedTodo = this.provider.client().target(location)
                .request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        
        assertTrue(dedicatedTodo.getString("caption").contains("Joylent Sport"));
    }
    
    @Test
    public void createAndUpdateFood() {
        JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
        JsonObject foodToCreate = todoBuilder
                .add("caption", "Joylent Sport")
                .add("description", "Full bag 660g")
                .build();
        
        Response postResponse = this.provider.target().request().post(Entity.json(foodToCreate));
        String location = postResponse.getHeaderString("Location");
        
        JsonObjectBuilder updateBuilder = Json.createObjectBuilder();
        JsonObject updated = updateBuilder
                .add("caption", "Joylent Vegan")
                .build();
        Response updateResponse = this.provider.client().target(location)
                .request(MediaType.APPLICATION_JSON).put(Entity.json(updated));
        assertThat(updateResponse.getStatus(), is(200));
        
        // Find again
        JsonObject updatedFood = this.provider.client().target(location)
                .request(MediaType.APPLICATION_JSON).get(JsonObject.class);
        
        assertTrue(updatedFood.getString("caption").contains("Joylent Vegan"));
    }
    
    
    @Test
    public void createAndFindAll() {
        JsonObjectBuilder foodBuilder1 = Json.createObjectBuilder();
        JsonObject foodToCreate1 = foodBuilder1
                .add("caption", "Joylent Sport")
                .add("description", "Full bag 660g")
                .build();
        
        JsonObjectBuilder foodBuilder2 = Json.createObjectBuilder();
        JsonObject foodToCreate2 = foodBuilder2
                .add("caption", "Joylent Sport")
                .add("description", "Full bag 660g")
                .build();
        
        Response postResponse1 = this.provider.target().request().post(Entity.json(foodToCreate1));
        String location1 = postResponse1.getHeaderString("Location");
        
        Response postResponse2 = this.provider.target().request().post(Entity.json(foodToCreate1));
        String location2 = postResponse1.getHeaderString("Location");
        
        // Findall
        Response response = this.provider.target().request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonArray allTodos = response.readEntity(JsonArray.class);
        System.out.println("Payload: " + allTodos);
        
        assertThat(allTodos.isEmpty(), is(false));
        
        JsonObject todo = allTodos.getJsonObject(0);
        assertTrue(todo.getString("caption").startsWith("Joylent Vegan"));
    }
    
    @Test
    public void createAndDelete() {
         JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
        JsonObject foodToCreate = todoBuilder
                .add("caption", "Joylent Sport")
                .add("description", "Full bag 660g")
                .build();
        
        Response postResponse = this.provider.target().request().post(Entity.json(foodToCreate));
        String location = postResponse.getHeaderString("Location");
        
        
        Response deleteResponse = this.provider.client().target(location)
            .request(MediaType.APPLICATION_JSON).get();
        assertThat(deleteResponse.getStatus(), is(200));
    
    }
    
}
