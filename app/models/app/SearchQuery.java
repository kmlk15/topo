package models.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class SearchQuery implements Serializable {

	public static final String CITY = "city:";
	public static final String CATEGORY = "category:";
	
	private List<Long> cities;
	private List<Long> categories;
	
	public SearchQuery() {
		cities = new ArrayList<Long>();
		categories = new ArrayList<Long>();
	}
	
	public List<Long> getCities() {
		return cities;
	}

	public void setCities(List<Long> cities) {
		this.cities = cities;
	}

	public void addCity(String id) {
		addCity(Long.valueOf(id));
	}
	
	public void addCity(Long id) {
		CollectionUtils.addIgnoreNull(cities, id);
	}
	
	public boolean hasCity() {
		return CollectionUtils.isNotEmpty(cities);
	}
	
	public List<Long> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Long> categories) {
		this.categories = categories;
	}
	
	public void addCategory(String id) {
		addCategory(Long.valueOf(id));
	}
	
	public void addCategory(Long id) {
		categories.add(id);
	}
	
	public boolean hasCategory() {
		return CollectionUtils.isNotEmpty(categories);
	}

	public String toString() {
		String result[] = new String[4];
		result[0] = StringUtils.join(cities, ",");
		result[1] = StringUtils.join(categories, ",");
		return StringUtils.join(result, ":");
	}
}
