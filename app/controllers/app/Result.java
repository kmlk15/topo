package controllers.app;

import java.util.List;

import models.Location;
import play.Logger;
import play.mvc.Controller;
import service.api.LocationSearchService;
import service.impl.LocationSearchServiceImpl;

public class Result extends Controller {

	public static void index() {
		render();
	}
	
	public static void recommended() {
		LocationSearchService lss = new LocationSearchServiceImpl();
		List<Location> locations = lss.getRecommendedLocations(null);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
	public static void popular() {
		LocationSearchService lss = new LocationSearchServiceImpl();
		List<Location> locations = lss.getPopularLocations(null);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
	public static void obcure() {
		LocationSearchService lss = new LocationSearchServiceImpl();
		List<Location> locations = lss.getObcureLocations(null);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
}
