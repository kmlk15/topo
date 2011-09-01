package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class HoursOfOperation extends Model {

	@ManyToOne
	public Location location;

	@Expose
	public String mondayOpen;

	@Expose
	public String mondayClosed;
	
	@Expose
	public String tuesdayOpen;
	
	@Expose
	public String tuesdayClosed;
	
	@Expose
	public String wednesdayOpen;
	
	@Expose
	public String wednesdayClosed;
	
	@Expose
	public String thursdayOpen;
	
	@Expose
	public String thursdayClosed;
	
	@Expose
	public String fridayOpen;
	
	@Expose
	public String fridayClosed;
	
	@Expose
	public String saturdayOpen;
	
	@Expose
	public String saturdayClosed;
	
	@Expose
	public String sundayOpen;

	@Expose
	public String sundayClosed;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (location != null) {
			sb.append(location.name);
		} else {
			sb.append(id);
		}
		return sb.toString();
	}
}
