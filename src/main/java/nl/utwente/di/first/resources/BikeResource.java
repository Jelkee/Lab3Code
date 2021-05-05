package nl.utwente.di.first.resources;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import nl.utwente.di.first.dao.BikeDao;
import nl.utwente.di.first.model.Bike;

public class BikeResource {
	

	@Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;
    Bike bike;

    public BikeResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
        getBikeDetails();
    }
    
    @GET
    @Produces({MediaType.TEXT_HTML})
	public String getBikeDetails() {
		Bike bike = BikeDao.instance.getModel().get(id);
		if(bike==null)
		      throw new RuntimeException("Get: Todo with " + id +  " not found");
		return "ID: " + bike.getID()
				+ "<br>Owner Name: " + bike.getOwnerName()
				+ "<br>Colour: " + bike.getColour()
				+ "<br>Gender: " + bike.getGender() + "<br><br>";
	}
    
	@Path("/order")
    @POST
    @Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
    public String orderBike() {
		if (BikeDao.instance.getModel().containsKey(id)) {
			return "Order succesfull";
		}
		return "Order not succesfull";
    	
    }
	
	@DELETE
	public void deleteBike() {
		Bike c = BikeDao.instance.getModel().remove(id);
	    if(c==null)
	      throw new RuntimeException("Delete: Todo with " + id +  " not found");
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateBike(JAXBElement<Bike> bike) {
      Bike c = bike.getValue();
      return putAndGetResponse(c);
    }
	
	private Response putAndGetResponse(Bike todo) {
	    Response res;
	    if(BikeDao.instance.getModel().containsKey(todo.getID())) {
	      res = Response.noContent().build();
	    } else {
	      res = Response.created(uriInfo.getAbsolutePath()).build();
	    }
	    BikeDao.instance.getModel().put(todo.getID(), todo);
	    return res;
	 }
}
