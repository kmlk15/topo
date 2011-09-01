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
}
