package service.api;

import java.util.List;
import java.util.Map;

public interface SearchAutoCompleteService {

	public List<Map<String, String>> getCityList(String q);
	
	public List<Map<String, String>> getCategoryList(String q);
}
