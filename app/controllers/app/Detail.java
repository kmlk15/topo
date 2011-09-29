package controllers.app;

import models.Location;
import models.User;
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
		Long userid = Cache.get(session.getId()+"-user", Long.class);
		User user = User.findById(userid);
		Location location = Location.findById(id);
		ss.calculate(location, LocationScoring.DETAIL_VIEW);
		ss.calculate(user, location, UserScoring.DETAIL_VIEW);
		render();
	}
}
