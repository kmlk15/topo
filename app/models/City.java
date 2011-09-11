package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.apache.commons.lang.StringUtils;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class City extends Model {

	@OneToMany(mappedBy="city", cascade = CascadeType.ALL)
	public List<Address> address;
	
	@Expose
	public String name;
	
	@Expose
	public String nameKey;
	
	public String toString() {
		return name;
	}
	
	public Map<String, String> toTokenMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "city:"+id);
		map.put("name", "City: "+name);
		return map;
	}
	
	public String toYML() {
		StringBuffer sb = new StringBuffer();
		sb.append("City(city").append(id).append("):\n");
		if (StringUtils.isNotEmpty(name)) {
			sb.append("    name: ").append(name).append("\n");
		}
		if (StringUtils.isNotEmpty(nameKey)) {
			sb.append("    nameKey: ").append(nameKey).append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	
}
