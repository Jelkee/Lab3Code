package nl.utwente.di.first.model;

public class Bike {
	
	private String id; 
	private String ownerName;
	private String colour; 
	private String gender;
 
	public Bike(String id, String ownerName, String colour, String gender) {
		this.id = id;
		this.ownerName = ownerName;
		this.colour = colour;
		this.gender = gender;
	}
	
	public String getID() {
		return this.id;
	}
	
	public String getOwnerName() {
		return this.ownerName;
	}
	
	public String getColour() {
		return this.colour;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public void setOwnerName(String name) {
		this.ownerName = name;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
}
