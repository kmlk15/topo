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
	public District district;
	
	@ManyToOne
	@Expose
	public City city;
	
	@ManyToOne
	@Expose
	public StateProvince stateProvince;
	
	@ManyToOne
	@Expose
	public Country country;
	
	public String getStreetAddress() {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(street1)) {
			sb.append(street1);
		}
		if (StringUtils.isNotEmpty(street2)) {
			sb.append(", ").append(street2);
		}
		return sb.toString();
	}
	
	public String getDistrictAddress() {
		StringBuffer sb = new StringBuffer();
		if (district != null) {
			sb.append(district.toString()).append(", ");
		}
		if (city != null) {
			sb.append(city.toString());
		}
		return sb.toString();
	}
	
	public String getAddress() {
		StringBuffer sb = new StringBuffer();
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
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (location != null) {
			sb.append(location.name).append(": ");
		} else {
			sb.append(id).append(": ");
		}	
		sb.append(getAddress());
		return sb.toString();
	}

	public String toYML() {
		StringBuffer sb = new StringBuffer();
		sb.append("Address(address").append(id).append("):\n");
		if (StringUtils.isNotEmpty(street1)) {
			sb.append("    street1: ").append(street1).append("\n");
		}
		if (StringUtils.isNotEmpty(street2)) {
			sb.append("    street2: ").append(street2).append("\n");
		}
		if (latitude != null) {
			sb.append("    latitude: ").append(latitude).append("\n");
		}
		if (longitude != null) {
			sb.append("    longitude: ").append(longitude).append("\n");
		}
		if (location != null) {
			sb.append("    location: location").append(location.id).append("\n");
		}
		if (district != null) {
			sb.append("    district: district").append(district.id).append("\n");
		}
		if (city != null) {
			sb.append("    city: city").append(city.id).append("\n");
		}
		if (stateProvince != null) {
			sb.append("    stateProvince: stateProvince").append(stateProvince.id).append("\n");
		}
		if (location != null) {
			sb.append("    country: country").append(country.id).append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

}
