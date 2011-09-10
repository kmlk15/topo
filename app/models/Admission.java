package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class Admission extends Model {

	@ManyToOne
	public Location location;
	
	@Expose
	public String type;
	
	@Expose
	public double amount;
	
	@Expose
	public String currency;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (location != null) {
			sb.append(location.name).append(": ");
		} else {
			sb.append(id).append(": ");
		}	
		sb.append(currency).append(amount);
		return sb.toString();
	}
	
	public String toYML() {
		StringBuffer sb = new StringBuffer();
		sb.append("Admission(admission").append(id).append("):\n");
		if (type != null) {
			sb.append("    type: ").append(type).append("\n");
		}
		sb.append("    amount: ").append(amount).append("\n");
		if (currency != null) {
			sb.append("    currency: ").append(currency).append("\n");
		}
		if (location != null) {
			sb.append("    location: location").append(location.id).append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

}
