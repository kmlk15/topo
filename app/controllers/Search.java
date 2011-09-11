package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Category;
import models.City;
import models.Location;
import play.mvc.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Search extends Controller {

    public static void index() {
        render();
    }
	
    public static void search(String query) {
    	System.out.println(query);
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
