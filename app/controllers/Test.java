package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Category;
import play.mvc.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test extends Controller {
	
	public static void yml() {
		List<Category> list = Category.findAll();
		for (Category c : list) {
			System.out.println(c.toYML());
		}
		renderText("haha");
	}
	
	public static void map() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("status", "200");
		Gson g = new GsonBuilder().create();
		renderText(g.toJson(m));
	}
}
