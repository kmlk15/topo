package controllers;

import java.util.List;

import models.Category;
import models.Location;
import play.mvc.Controller;
import services.ScoringService;
import services.UserAction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test extends Controller {

	public static void ttt() {
		Location l = Location.find("order by id desc").first();
		render(l);
	}
	
	public static void jjj() {
		Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		Location l = Location.find("order by id desc").first();
		renderText(g.toJson(l));
	}
	
	public static void sss() {
		ScoringService s = new ScoringService();
		s.calculate(1, 1, UserAction.DETAIL_LIKE);
		renderText("haha");
	}
	
	public static void yml() {
		List<Category> list = Category.findAll();
		for (Category c : list) {
			System.out.println(c.toYML());
		}
		renderText("haha");
	}
}
