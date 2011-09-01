package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class Category extends Model {

	@Expose
	public String name;
		
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@Expose
	public List<Category> child;
	
	@ManyToOne
	public Category parent;
	
	public int compareTo(Category other) {
		return name.compareTo(other.name);
	}
	
	public int level() {
		if (parent == null) {
			return 1;
		}
		return 1 + parent.level();
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
		sb.append("    name: ").append(name).append("\n");
		if (parent != null) {
			sb.append("    parent: category").append(parent.id).append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
}
