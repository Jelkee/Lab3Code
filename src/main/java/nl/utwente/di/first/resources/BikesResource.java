package nl.utwente.di.first.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;


import nl.utwente.di.first.dao.BikeDao;
import nl.utwente.di.first.model.Bike;

@Path("/bikes")
public class BikesResource {
	
    // Allows to insert contextual objects into the class, 
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
	
    @GET
	@Produces(MediaType.TEXT_XML)
	public List<Bike> getBikesBrowser(){
		List<Bike> bikes = new ArrayList<Bike>();
		bikes.addAll(BikeDao.instance.getModel().values());
		return bikes;
	}
    
	// Return the list of todos for applications
    @GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Bike> getBikes(){
		List<Bike> bikes = new ArrayList<Bike>();
		bikes.addAll(BikeDao.instance.getModel().values());
		return bikes;
	}
	  
    @POST
    @Produces(MediaType.TEXT_HTML)
    public void newTodo(@FormParam("id") String id,
    	      @FormParam("ownerName") String ownerName,
    	      @FormParam("colour") String colour,
    	      @FormParam("gender") String gender,
    	      @Context HttpServletResponse servletResponse) throws IOException {
    	    Bike bike = new Bike(id,ownerName,colour,gender);
    	    
    	    BikeDao.instance.getModel().put(id, bike);
    	    
    	  }
    
 // Defines that the next path parameter after todos is
    // treated as a parameter and passed to the TodoResources
    // Allows to type http://localhost:8080/de.vogella.jersey.todo/rest/todos/1
    // 1 will be treaded as parameter todo and passed to TodoResource
    @Path("{id}")
    public BikeResource getBike(@PathParam("id") String id) {
        return new BikeResource(uriInfo, request, id);
    }
    
//    @Path("{id}/order")
//    public void order(@PathParam("id") String id) {
//    	new BikeResource(uriInfo, request, id).orderBike();
//    }
    
		
}
