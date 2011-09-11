package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;

import play.db.jpa.Model;

@Entity
public class Itinerary extends Model {

	public String name;
	
	@ManyToOne
	public User user;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Location_Itinerary", joinColumns = { @JoinColumn(name = "location_id") }, inverseJoinColumns = { @JoinColumn(name = "itinerary_id") })
	public List<Location> location;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(user.toString()).append(": ").append(name);
		return sb.toString();
	}
	
	public String toYML() {
		StringBuffer sb = new StringBuffer();
		sb.append("Itinerary(itinerary").append(id).append("):\n");
		if (StringUtils.isNotEmpty(name)) {
			sb.append("    name: ").append(name).append("\n");
		}
		if (user != null) {
			sb.append("    user: user").append(user.id).append("\n");
		}
		if (location != null) {
			for (Location l : location) {
				sb.append("    location: location").append(l.id).append("\n");
			}
		}
		sb.append("\n");
		return sb.toString();
	}
}
