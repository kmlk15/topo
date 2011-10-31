package controllers.app;

import java.util.List;

import models.Location;
import models.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;
import service.api.LocationSearchService;
import service.api.ScoringService;
import service.constants.LocationScoring;
import service.constants.UserScoring;

import com.google.inject.Guice;

import dependencies.AppModule;

public class Result extends Controller {

	private static LocationSearchService lss = Guice.createInjector(new AppModule()).getInstance(LocationSearchService.class);
	private static ScoringService ss = Guice.createInjector(new AppModule()).getInstance(ScoringService.class);
	
	public static void index() {
		render();
	}
	
	public static void recommended() {
		Long userid = Cache.get(session.getId()+"-user", Long.class);
		Logger.debug(""+userid);
		User user = User.findById(userid);
		List<Location> locations = lss.getRecommendedLocations(user);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
	public static void popular() {
		Long userid = Cache.get(session.getId()+"-user", Long.class);
		Logger.debug(""+userid);
		User user = User.findById(userid);
		List<Location> locations = lss.getPopularLocations(user);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
	public static void obscure() {
		Long userid = Cache.get(session.getId()+"-user", Long.class);
		Logger.debug(""+userid);
		User user = User.findById(userid);
		List<Location> locations = lss.getObscureLocations(user);
		Logger.debug(""+locations.size());
		render(locations);
	}
	
	public static void add(long id) {
    	Long userid = Cache.get(session.getId()+"-user", Long.class);
    	Logger.debug(""+userid);
		User user = User.findById(userid);
		Location location = Location.findById(id);
		ss.calculate(location, LocationScoring.RESULT_LIKE);
		ss.calculate(user, location, UserScoring.RESULT_LIKE);
		response.status = 200;
	}
	
	public static void remove(long id) {
    	Long userid = Cache.get(session.getId()+"-user", Long.class);
    	Logger.debug(""+userid);
		User user = User.findById(userid);
		Location location = Location.findById(id);
		ss.calculate(location, LocationScoring.RESULT_DISLIKE);
		ss.calculate(user, location, UserScoring.RESULT_DISLIKE);
		response.status = 200;
	}
}
