package controllers.internal;

import java.util.List;

import models.Address;
import models.Admission;
import models.Category;
import models.City;
import models.Country;
import models.District;
import models.HoursOfOperation;
import models.Itinerary;
import models.Location;
import models.Phone;
import models.Picture;
import models.Score;
import models.StateProvince;
import models.User;

import org.joda.time.DateTime;

import play.Logger;
import play.mvc.Controller;

public class DataExport extends Controller {
	
	public static void index() {
		render();
	}
	
	public static void yml() {
		response.setHeader("Content-Type", "text/yml; charset=utf-8");
		response.setHeader(
				"Content-Disposition",
				"attachment; filename=export-"
						+ new DateTime().toString("yyyyMMdd-hhmmss") + ".yml");
		try {
			List<Category> category = Category.find("order by id asc").fetch();
			for (Category c : category) {
				response.out.write(c.toYML().getBytes());
			}
			List<Location> location = Location.findAll();
			for (Location l : location) {
				response.out.write(l.toYML().getBytes());
			}
			List<HoursOfOperation> hours = HoursOfOperation.findAll();
			for (HoursOfOperation h : hours) {
				response.out.write(h.toYML().getBytes());
			}
			List<District> district = District.findAll();
			for (District d : district) {
				response.out.write(d.toYML().getBytes());
			}
			List<City> city = City.findAll();
			for (City c : city) {
				response.out.write(c.toYML().getBytes());
			}
			List<StateProvince> state = StateProvince.findAll();
			for (StateProvince s : state) {
				response.out.write(s.toYML().getBytes());
			}
			List<Country> country = Country.findAll();
			for (Country c : country) {
				response.out.write(c.toYML().getBytes());
			}
			List<Address> address = Address.findAll();
			for (Address a : address) {
				response.out.write(a.toYML().getBytes());
			}
			List<Admission> admission = Admission.findAll();
			for (Admission a : admission) {
				response.out.write(a.toYML().getBytes());
			}
			List<Phone> phone = Phone.findAll();
			for (Phone p : phone) {
				response.out.write(p.toYML().getBytes());
			}
			List<Picture> picture = Picture.findAll();
			for (Picture p : picture) {
				response.out.write(p.toYML().getBytes());
			}
			List<User> user = User.findAll();
			for (User u : user) {
				response.out.write(u.toYML().getBytes());
			}
			List<Itinerary> itinerary = Itinerary.findAll();
			for (Itinerary i : itinerary) {
				response.out.write(i.toYML().getBytes());
			}
			List<Score> score = Score.findAll();
			for (Score s : score) {
				response.out.write(s.toYML().getBytes());
			}
		} catch (Exception e) {
			Logger.error(e, "Error on exporting", e);
			response.status = 404;
		}
	}	
}
