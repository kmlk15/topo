package service.impl;

import java.util.ArrayList;
import java.util.List;

import models.Location;
import models.Score;
import models.User;

import org.apache.commons.collections.CollectionUtils;

import service.api.LocationSearchService;

public class LocationSearchServiceImpl implements LocationSearchService {

	public List<Location> getRecommendedLocations(User u) {
		List<Score> scores = Score.find("select s from Score s where s.user = :uid order by score desc ").bind("uid", u).fetch(9);
		if (CollectionUtils.isNotEmpty(scores)) {
			List<Location> locations = new ArrayList<Location>();
			for (Score s : scores) {
				List<Location> l = Location.find("select distinct l from Location l join l.category c where c.id = :cid").bind("cid", s.category.id).fetch(9);
				locations.addAll(l);
				if (locations.size() >= 9) {
					break;
				}
			}
			return locations;
		}
		return getPopularLocations(u);
	}

	public List<Location> getPopularLocations(User u) {
		List<Location> locations = Location.find("order by popularity desc").fetch(9);
		return locations;
	}

	public List<Location> getObcureLocations(User u) {
		List<Location> locations = Location.all().fetch(9);
		return locations;
	}

}
