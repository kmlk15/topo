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
	
	public String toYML() {
		StringBuffer sb = new StringBuffer();
		sb.append("HoursOfOperation(hoursOfOperation").append(id).append("):\n");
		sb.append("    mondayOpen: ").append(mondayOpen).append("\n");
		sb.append("    mondayClosed: ").append(mondayClosed).append("\n");
		sb.append("    tuesdayOpen: ").append(tuesdayOpen).append("\n");
		sb.append("    tuesdayClosed: ").append(tuesdayClosed).append("\n");
		sb.append("    wednesdayOpen: ").append(wednesdayOpen).append("\n");
		sb.append("    wednesdayClosed: ").append(wednesdayClosed).append("\n");
		sb.append("    thursdayOpen: ").append(thursdayOpen).append("\n");
		sb.append("    thursdayClosed: ").append(thursdayClosed).append("\n");
		sb.append("    fridayOpen: ").append(fridayOpen).append("\n");
		sb.append("    fridayClosed: ").append(fridayClosed).append("\n");
		sb.append("    saturdayOpen: ").append(saturdayOpen).append("\n");
		sb.append("    saturdayClosed: ").append(saturdayClosed).append("\n");
		sb.append("    sundayOpen: ").append(sundayOpen).append("\n");
		sb.append("    sundayClosed: ").append(sundayClosed).append("\n");
		if (location != null) {
			sb.append("    location: location").append(location.id).append("\n");
		}
		return sb.toString();
	}	
}
