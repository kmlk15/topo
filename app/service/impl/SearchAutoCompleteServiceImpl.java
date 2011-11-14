package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Category;
import models.City;
import service.api.SearchAutoCompleteService;

public class SearchAutoCompleteServiceImpl implements SearchAutoCompleteService {
	
	public List<Map<String, String>> getCityList(String q) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<City> start = City.find("name like :start order by name asc").bind("start", q+"%").fetch(6);
		for (City c : start) {
    		result.add(c.toTokenMap());
    	}
		int extra = result.size() < 6 ? 6 - result.size() : 6;
		if (extra > 0) {
			List<City> contain = City.find("name like :contain and name not like :start order by name asc").bind("contain", "%"+q+"%").bind("start", q+"%").fetch(extra);
			for (City c : contain) {
	    		result.add(c.toTokenMap());
	    	}
		}
		return result;
	}
	
	public List<Map<String, String>> getCategoryList(String q) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<Category> start = Category.find("name like :start order by name asc").bind("start", q+"%").fetch(6);
		for (Category c : start) {
    		result.add(c.toTokenMap());
    	}
		int extra = result.size() < 6 ? 6 - result.size() : 6;
		if (extra > 0) {
			List<Category> contain = Category.find("name like :contain and name not like :start order by name asc").bind("contain", "%"+q+"%").bind("start", q+"%").fetch(extra);
			for (Category c : contain) {
	    		result.add(c.toTokenMap());
	    	}
		}
		return result;
	}
}
