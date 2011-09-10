package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class Location extends Model {
	
	@Expose
	public String name;
	
	@Expose
    public String nameKey;
    
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @Expose
    public List<HoursOfOperation> hours;
    
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @Expose
    public List<Address> address;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @Expose
    public List<Admission> admission;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @Expose
    public List<Phone> phone;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Location_Category", joinColumns = { @JoinColumn(name = "location_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	@Expose
    public List<Category> category;
		
	public String toString() {
		return name;
	}
	
	public Map<String, String> toTokenMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "location:"+id);
		map.put("name", "Location: "+name);
		return map;
	}

	public String toYML() {
		StringBuffer sb = new StringBuffer();
		sb.append("Location(location").append(id).append("):\n");
		if (name != null) { 
			sb.append("    name: ").append(name).append("\n");
		}
		if (nameKey != null) {
			sb.append("    nameKey: ").append(nameKey).append("\n");
		}
		if (category != null) {
			for (Category c : category) {
				sb.append("    category: category").append(c.id).append("\n");
			}
		}
		sb.append("\n");
		return sb.toString();
	}

}
