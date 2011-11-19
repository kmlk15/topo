package models;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

import org.apache.commons.lang.StringUtils;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class Category extends Model {

	@Expose
	public String name;
	
	@Expose
	public Integer level;
				
	public int compareTo(Category other) {
		return name.compareTo(other.name);
	}
	
	public String toString() {
		return name;
	}
	
	public Map<String, String> toTokenMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "category:"+id);
		map.put("name", "Category: "+name);
		return map;
	}

	public String toYML() {
		StringBuffer sb = new StringBuffer();
		sb.append("Category(category").append(id).append("):\n");
		if (StringUtils.isNotEmpty(name)) {
			sb.append("    name: ").append(name).append("\n");
		}
		if (level != null) {
			sb.append("    level: ").append(level).append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
}
