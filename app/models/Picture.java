package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;

import play.db.jpa.Model;

@Entity
public class Picture extends Model {

	@ManyToOne
	public Location location;

	public String type;

	public String url;

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (location != null) {
			sb.append(location.name).append(": ");
		}
		sb.append(id).append(": ");
		if (StringUtils.isNotEmpty(type)) {
			sb.append(type);
		}
		return sb.toString();
	}
	
	public String toYML() {
		StringBuffer sb = new StringBuffer();
		sb.append("Picture(picture").append(id).append("):\n");
		if (location != null) {
			sb.append("    location: location").append(location.id).append("\n");
		}
		if (StringUtils.isNotEmpty(type)) {
			sb.append("    type: ").append(type).append("\n");
		}
		if (StringUtils.isNotEmpty(url)) {
			sb.append("    url: ").append(url).append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
}
