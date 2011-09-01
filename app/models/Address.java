package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class Address extends Model {

	@ManyToOne
	public Location location;
	
	@Expose
	public String street1;
	
	@Expose
	public String street2;
	
	@Expose
	public Long latitude;
	
	@Expose
	public Long longitude;
	
	@ManyToOne
	@Expose
	public City city;
	
	@ManyToOne
	@Expose
	public StateProvince stateProvince;
	
	@ManyToOne
	@Expose
	public Country country;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (location != null) {
			sb.append(location.name).append(": ");
		} else {
			sb.append(id).append(": ");
		}	
		if (StringUtils.isNotEmpty(street1)) {
			sb.append(street1);
		}
		if (StringUtils.isNotEmpty(street2)) {
			sb.append(", ").append(street2);
		}
		if (city != null) {
			sb.append(", ").append(city.toString());
		}
		if (stateProvince != null) {
			sb.append(", ").append(stateProvince.toString());
		}
		if (country != null) {
			sb.append(", ").append(country.toString());
		}
		return sb.toString();
	}

}
