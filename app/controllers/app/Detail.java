package controllers.app;

import play.mvc.Controller;

public class Detail extends Controller {

//	private static ScoringService ss = Guice.createInjector(new AppModule()).getInstance(ScoringService.class);
	
	public static void index(long id) {
		render();
	}
	
	public static void detail() {
		render();
	}
	
	public static void area() {
		render();
	}
}
