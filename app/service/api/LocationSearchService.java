package service.api;

import java.util.List;

import models.Location;
import models.User;
import models.app.SearchQuery;

public interface LocationSearchService {

	public List<Location> getRecommendedLocations(User u, SearchQuery q);
	
	public List<Location> getPopularLocations(User u, SearchQuery q);

	public List<Location> getObscureLocations(User u, SearchQuery q);

}
