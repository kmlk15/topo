package models.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class Knapsack implements Serializable {

	private List<Long> locations;
		
	public List<Long> getLocations() {
		return locations;
	}
	
	public void add(Long id) {
		if (locations == null) {
			locations = new ArrayList<Long>();
		}
		locations.add(id);
	}
		
	public void remove(Long id) {
		if (CollectionUtils.isNotEmpty(locations)) {
			locations.remove(id);
		}
	}
	
	public String toString() {
		return StringUtils.join(locations, ",");
	}
}
