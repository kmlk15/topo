package controllers;

import java.util.List;

import org.joda.time.DateTime;

import models.Category;
import play.Logger;
import play.mvc.Controller;

public class Export extends Controller {

	public static void yml() {
		response.setHeader("Content-Type", "text/yml; charset=utf-8");
		response.setHeader(
				"Content-Disposition",
				"attachment; filename=export-"
						+ new DateTime().toString("yyyyMMdd") + ".yml");

		try {
			List<Category> category = Category.findAll();
			for (Category c : category) {
				response.out.write(c.toYML().getBytes());
			}
		} catch (Exception e) {
			Logger.error(e, "Error on exporting", e);
			response.status = 404;
		}
	}

}
