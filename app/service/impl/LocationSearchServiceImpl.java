package service.impl;

import java.util.ArrayList;
import java.util.List;

import service.api.LocationSearchService;

import models.Location;
import models.User;

public class LocationSearchServiceImpl implements LocationSearchService {

	public List<Location> getRecommendedLocations(User u) {
		return new ArrayList<Location>();
	}
	
	public List<Location> getPopularLocations(User u) {
		return new ArrayList<Location>();
	}

	public List<Location> getObcureLocations(User u) {
		return new ArrayList<Location>();
	}

}
