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

import org.apache.commons.lang.StringUtils;

import play.db.jpa.Model;

import com.google.gson.annotations.Expose;

@Entity
public class Location extends Model {
	
	@Expose
	public String name;
	
	@Expose
    public String nameKey;
	
	public String shortDescKey;
	
	public String longDescKey;
    
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

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    public List<Picture> picture;
    
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
		if (StringUtils.isNotEmpty(name)) { 
			sb.append("    name: ").append(name).append("\n");
		}
		if (StringUtils.isNotEmpty(nameKey)) {
			sb.append("    nameKey: ").append(nameKey).append("\n");
		}
		if (StringUtils.isNotEmpty(shortDescKey)) {
			sb.append("    shortDescKey: ").append(shortDescKey).append("\n");
		}
		if (StringUtils.isNotEmpty(longDescKey)) {
			sb.append("    longDescKey: ").append(longDescKey).append("\n");
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
