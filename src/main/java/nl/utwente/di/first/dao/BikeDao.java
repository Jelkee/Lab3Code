package nl.utwente.di.first.dao;

	
	import java.util.HashMap;
	import java.util.Map;

import nl.utwente.di.first.model.Bike;
	
	public enum BikeDao {
		instance();
	
		private Map<String, Bike> contentProvider = new HashMap<String, Bike>();
	
		private BikeDao() {
	
			Bike bike1 = new Bike("1", "Jan", "Red", "Male");
			contentProvider.put("1", bike1);
			
			Bike bike2 = new Bike("2", "Piet", "Blue", "Male");
			contentProvider.put("2", bike2);
	
		}
	
	public Map<String, Bike> getModel() {
		return contentProvider;
	}

}

