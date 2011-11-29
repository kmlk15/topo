package controllers.app;

import java.util.List;

import models.Location;
import models.app.Knapsack;
import play.cache.Cache;
import play.mvc.Controller;

public class Itinerary extends Controller {

    public static void index() {
    	Knapsack knapsack = Cache.get(session.getId()+"-knapsack", Knapsack.class);
    	List<Location> knapsacks = null; 
    	if (knapsack.hasKnapsack()) {
    		knapsacks = Location.find("select l from Location l where l.id in :lids").bind("lids", knapsack.getLocations()).fetch();
    	}
        render(knapsacks);
    }

}