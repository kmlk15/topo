package service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Location;
import models.Score;
import models.User;
import models.app.SearchQuery;

import org.apache.commons.collections.CollectionUtils;

import service.api.LocationSearchService;

public class LocationSearchServiceImpl implements LocationSearchService {

	public List<Location> getRecommendedLocations(User u, SearchQuery q) {
		List<Score> scores = null;
		if (q.hasCategory()) {
			scores = Score.find("select s from Score s join s.category c where s.user = :uid and c.id in :cids order by score desc").bind("uid", u).bind("cids", q.getCategories()).fetch(9);			
		} else {
			scores = Score.find("select s from Score s join s.category c where s.user = :uid order by score desc").bind("uid", u).fetch(9);
		}
		if (CollectionUtils.isNotEmpty(scores)) {
			List<Location> locations = new ArrayList<Location>();
			Iterator<Score> iter = scores.iterator();
			int count = 4;
			while (iter.hasNext() && locations.size() < 9) {
				Score s = iter.next();
				List<Location> l = null;
				if (q.hasCity()) {
					l = Location.find("select distinct l from Location l join l.category c join l.address a where c.id = :cid and a.city.id in :aids").bind("cid", s.category.id).bind("aids", q.getCities()).fetch(count);	
				} else {
					l = Location.find("select distinct l from Location l join l.category c where c.id = :cid").bind("cid", s.category.id).fetch(count);
				}
				locations.addAll(l);
				if (count > 1) {
					count--;
				}
			}
			return locations;
		}
		return getPopularLocations(u, q);
	}

	public List<Location> getPopularLocations(User u, SearchQuery q) {
		List<Location> locations = null;
		if (q.hasCity() && q.hasCategory()) {
			if (q.hasCategory()) {
				locations = Location.find("select distinct l from Location l join l.category c join l.address a where c.id in :cids and a.city.id in :aids order by popularity desc").bind("cids", q.getCategories()).bind("aids", q.getCities()).fetch(9);
			} 
		} else if (q.hasCity()) {
			locations = Location.find("select distinct l from Location l join l.address a where a.city.id in :aids order by popularity desc").bind("aids", q.getCities()).fetch(9);
		} else if (q.hasCategory()) {
			locations = Location.find("select distinct l from Location l join l.category c where c.id in :cids order by popularity desc").bind("cids", q.getCategories()).fetch(9);
		} else {
			locations = Location.find("order by popularity desc").fetch(9);
		}
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
