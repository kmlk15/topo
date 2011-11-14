package controllers.app;

import java.util.List;
import java.util.Map;

import models.User;
import models.app.SearchQuery;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;
import service.api.SearchAutoCompleteService;
import service.api.SearchQueryParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;

import dependencies.AppModule;

public class Search extends Controller {

	private static SearchQueryParser sqp = Guice.createInjector(new AppModule()).getInstance(SearchQueryParser.class);
	private static SearchAutoCompleteService sacs = Guice.createInjector(new AppModule()).getInstance(SearchAutoCompleteService.class);
	
    public static void index() {
        render();
    }
	
    public static void search(String query) {
    	Long userid = Cache.get(session.getId()+"-user", Long.class);
    	if (userid == null) {
    		User user = User.find("byEmail", "alan@test.com").first();
    		Cache.set(session.getId()+"-user", user.id, "30mn");
    	}
    	Logger.debug(query);
    	SearchQuery searchQuery = sqp.parseQuery(query);
    	Cache.set(session.getId()+"-search", searchQuery, "30mn");
    	Result.index();
    }
    
    public static void suggest(String q) {
    	Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    	List<Map<String, String>> result = sacs.getCityList(q);
    	result.addAll(sacs.getCategoryList(q));
		renderText(g.toJson(result));
    }
    
}
