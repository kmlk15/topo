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
		render();
	}
	
	public static void recommended() {
		Long userid = Cache.get(session.getId()+"-user", Long.class);
		User user = User.findById(userid);
		List<Location> locations = lss.getRecommendedLocations(user);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
	public static void popular() {
		List<Location> locations = lss.getPopularLocations(null);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
	public static void obcure() {
		Long userid = Cache.get(session.getId()+"-user", Long.class);
		User user = User.findById(userid);
		List<Location> locations = lss.getObscureLocations(user);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
}
