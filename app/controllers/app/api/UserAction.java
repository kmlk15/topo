package controllers.app.api;

import java.util.HashMap;
import java.util.Map;

import models.Location;
import models.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;
import service.api.ScoringService;
import service.constants.LocationScoring;
import service.constants.UserScoring;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;

import dependencies.AppModule;

public class UserAction extends Controller {

	private static ScoringService ss = Guice.createInjector(new AppModule()).getInstance(ScoringService.class);
	
	public static void click(long id, int action) {
		Map<String, Object> m = new HashMap<String, Object>();
		Gson g = new GsonBuilder().create();
		
		try {
			Long userid = Cache.get(session.getId()+"-user", Long.class);
			User user = User.findById(userid);
			Location location = Location.findById(id);
			UserScoring us = UserScoring.byId(action);
			LocationScoring ls = LocationScoring.byId(action);
			ss.calculate(location, ls);
			ss.calculate(user, location, us);
			m.put("status", 200);
		} catch (Exception e) {
			Logger.error(e, "*** Error");
			m.put("status", 500);
		}
		renderText(g.toJson(m));
		
	}
	
}
