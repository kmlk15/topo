package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class Country extends Model {

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	public List<Address> address;
	
	@Expose
	public String name;
	
	@Expose
	public String nameKey;

	public String toString() {
		return name;
	}
	
	public String toYML() {
		StringBuffer sb = new StringBuffer();
		sb.append("Country(country").append(id).append("):\n");
		sb.append("    name: ").append(name).append("\n");
		sb.append("    nameKey: ").append(nameKey).append("\n");
		return sb.toString();
	}

}
