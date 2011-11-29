package controllers.app;

import java.util.List;

import models.Location;
import models.User;
import models.app.Knapsack;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;
import service.api.ScoringService;
import service.constants.LocationScoring;
import service.constants.UserScoring;

import com.google.inject.Guice;

import dependencies.AppModule;

public class Detail extends Controller {

	private static ScoringService ss = Guice.createInjector(new AppModule()).getInstance(ScoringService.class);
	
	public static void index(long id) {
    	Knapsack knapsack = Cache.get(session.getId()+"-knapsack", Knapsack.class);
    	List<Location> knapsacks = null; 
    	if (knapsack.hasKnapsack()) {
    		knapsacks = Location.find("select l from Location l where l.id in :lids").bind("lids", knapsack.getLocations()).fetch();
    	}
        render(knapsacks, id);
    }
	
	public static void detail(long id) {
		Location location = Location.findById(id);
		render(location);
	}
	
	public static void area(long id) {
		Location location = Location.findById(id);
		render(location);
	}
	
	public static void add(long id) {
    	Long userid = Cache.get(session.getId()+"-user", Long.class);
		User user = User.findById(userid);
		Location location = Location.findById(id);
		ss.calculate(location, LocationScoring.DETAIL_LIKE);
		ss.calculate(user, location, UserScoring.DETAIL_LIKE);
		Knapsack knapsack = Cache.get(session.getId()+"-knapsack", Knapsack.class);
		knapsack.add(id);
		Logger.debug(knapsack.toString());
		Cache.set(session.getId()+"-knapsack", knapsack);
		response.status = 200;
	}
	
	public static void remove(long id) {
		Knapsack knapsack = Cache.get(session.getId()+"-knapsack", Knapsack.class);
		knapsack.remove(id);
		Logger.debug(knapsack.toString());
		response.status = 200;
	}
}
