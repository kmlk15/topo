package controllers.app;

import models.Location;
import play.mvc.Controller;
import service.api.ScoringService;
import service.constants.LocationScoring;

import com.google.inject.Guice;

import dependencies.AppModule;

public class Detail extends Controller {

	private static ScoringService ss = Guice.createInjector(new AppModule()).getInstance(ScoringService.class);
	
	public static void index(long id) {
		Location location = Location.findById(id);
		ss.calculate(location, LocationScoring.DETAIL_VIEW);
		render();
	}
}
