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
    public String i18nKey;
    
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

}
