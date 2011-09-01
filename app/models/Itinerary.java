package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
}
