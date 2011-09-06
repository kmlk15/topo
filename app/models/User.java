package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class User extends Model {

	public String email;
	
	public String displayName;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public List<Score> score;

	@OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
	public List<Itinerary> itinerary;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(displayName).append(": ").append(email);
		return sb.toString(); 
	}
	
	public String toYML() {
		StringBuffer sb = new StringBuffer();
		sb.append("User(user").append(id).append("):\n");
		sb.append("    email: ").append(email).append("\n");
		sb.append("    displayName: ").append(displayName).append("\n");
		return sb.toString();
	}	
}
