package controllers.app;

import java.util.List;

import models.Location;
import models.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;
import service.api.LocationSearchService;

import com.google.inject.Guice;

import dependencies.AppModule;

public class Result extends Controller {

	private static LocationSearchService lss = Guice.createInjector(new AppModule()).getInstance(LocationSearchService.class);
	
	public static void index() {
		User user = Cache.get(session.getId()+"-user", User.class);
		if (user != null) {
			Logger.info(user.displayName);
		}
		render();
	}
	
	public static void recommended() {
		List<Location> locations = lss.getRecommendedLocations(null);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
	public static void popular() {
		List<Location> locations = lss.getPopularLocations(null);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
	public static void obcure() {
		List<Location> locations = lss.getObcureLocations(null);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
}
