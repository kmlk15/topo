package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class Phone extends Model {

	@ManyToOne
	public Location location;
	
	@Expose
	public String type;
	
	@Expose
	public String countryCode;
	
	@Expose
	public String areaCode;
	
	@Expose
	public String number;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (location != null) {
			sb.append(location.name).append(": ");
		} else {
			sb.append(id).append(": ");
		}		
		if (StringUtils.isNotEmpty(countryCode)) {
			sb.append(countryCode).append("-");
		}
		if (StringUtils.isNotEmpty(areaCode)) {
			sb.append(areaCode).append("-");
		}
		sb.append(number);
		return sb.toString();
	}
}
