package service.impl;

import java.util.List;

import models.Location;
import models.User;
import service.api.LocationSearchService;

public class LocationSearchServiceImpl implements LocationSearchService {

	public List<Location> getRecommendedLocations(User u) {
		List<Location> locations = Location.findAll();
		return locations;
	}
	
	public List<Location> getPopularLocations(User u) {
		List<Location> locations = Location.find("order by popularity desc").fetch();
		return locations;
	}

	public List<Location> getObcureLocations(User u) {
		List<Location> locations = Location.findAll();
		return locations;
	}

}
