package controllers.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Category;
import models.City;
import models.Location;
import models.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Search extends Controller {

    public static void index() {
        render();
    }
	
    public static void search(String query) {
    	Logger.debug(query);
    	Long userid = Cache.get(session.getId()+"-user", Long.class);
    	if (userid == null) {
    		User user = User.find("byEmail", "alan@test.com").first();
    		Cache.set(session.getId()+"-user", user.id, "30mn");
    	}
    	Result.index();
    }
    
    public static void suggest(String q) {
    	Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		List<City> city = City.find("name like ?", "%"+q+"%").fetch(5);
    	List<Map<String, String>> result = new ArrayList<Map<String, String>>();
    	for (City c : city) {
    		result.add(c.toTokenMap());
    	}
    	List<Category> category = Category.find("name like ?", "%"+q+"%").fetch(5);
    	for (Category c : category) {
    		result.add(c.toTokenMap());
    	}
    	List<Location> location = Location.find("name like ?", "%"+q+"%").fetch(5);
    	for (Location l : location) {
    		result.add(l.toTokenMap());
    	}
		renderText(g.toJson(result));
    }
}
