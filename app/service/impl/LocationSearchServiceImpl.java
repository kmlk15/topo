package service.impl;

import java.util.ArrayList;
import java.util.List;

import models.Location;
import models.Score;
import models.User;
import models.app.SearchQuery;

import org.apache.commons.collections.CollectionUtils;

import service.api.LocationSearchService;

public class LocationSearchServiceImpl implements LocationSearchService {

	public List<Location> getRecommendedLocations(User u, SearchQuery q) {
		List<Score> scores = Score.find("select s from Score s where s.user = :uid order by score desc ").bind("uid", u).fetch(9);
		if (CollectionUtils.isNotEmpty(scores)) {
			List<Location> locations = new ArrayList<Location>();
			for (Score s : scores) {
				List<Location> l = null;
				if (q.hasCity()) {
					l = Location.find("select distinct l from Location l join l.category c join l.address a where c.id = :cid and a.city.id in :aid").bind("cid", s.category.id).bind("aid", q.getCities()).fetch(9);	
				} else {
					l = Location.find("select distinct l from Location l join l.category c where c.id = :cid").bind("cid", s.category.id).fetch(9);
				}
				locations.addAll(l);
				if (locations.size() >= 9) {
					break;
				}
			}
			return locations;
		}
		return getPopularLocations(u, q);
	}

	public List<Location> getPopularLocations(User u, SearchQuery q) {
		List<Location> locations = Location.find("order by popularity desc").fetch(9);
		return locations;
	}

	public List<Location> getObscureLocations(User u, SearchQuery q) {
		List<Score> scores = Score.find("select s from Score s where s.user = :uid order by score desc ").bind("uid", u).fetch(9);
		if (CollectionUtils.isNotEmpty(scores)) {
			List<Long> cids = new ArrayList<Long>();
			for (Score s : scores) {
				cids.add(s.category.id);
			}
			List<Location> locations = Location.find("select distinct l from Location l join l.category c where c.id not in :cids order by popularity desc").bind("cids", cids).fetch(9);
			return locations;
		}
		return getPopularLocations(u, q);
	}

}
