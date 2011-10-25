package service.api;

import java.util.List;

import models.Location;
import models.User;

public interface LocationSearchService {

	public List<Location> getRecommendedLocations(User u);
	
	public List<Location> getPopularLocations(User u);

	public List<Location> getObscureLocations(User u);

}
