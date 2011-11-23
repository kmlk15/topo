package service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Category;
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
			scores = Score.find("select s from Score s join s.category c where s.user = :uid and c.level = 2 order by score desc").bind("uid", u).fetch(9);
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
		} else if (q.hasCategory()) {
			List<Location> locations = new ArrayList<Location>();
			if (q.hasCity()) {
				locations = Location.find("select distinct l from Location l join l.category c join l.address a where c.id = :cid and a.city.id in :aids").bind("cid", q.getCategories()).bind("aids", q.getCities()).fetch(9);	
			} else {
				locations = Location.find("select distinct l from Location l join l.category c where c.id = :cid").bind("cid", q.getCategories()).fetch(9);
			}
			return locations;
		}
		return getPopularLocations(u, q);
	}

	public List<Location> getPopularLocations(User u, SearchQuery q) {
		List<Long> cids = new ArrayList<Long>();
		if (q.hasCategory()) {
			cids.addAll(q.getCategories());
			cids.addAll(getLevelOneCategories(q.getCategories()));
		}
		List<Location> locations = null;
		if (q.hasCity() && q.hasCategory()) {
			locations = Location.find("select distinct l from Location l join l.category c join l.address a where c.id in :cids and a.city.id in :aids order by popularity desc").bind("cids", cids).bind("aids", q.getCities()).fetch(9);
		} else if (q.hasCity()) {
			locations = Location.find("select distinct l from Location l join l.address a where a.city.id in :aids order by popularity desc").bind("aids", q.getCities()).fetch(9);
		} else if (q.hasCategory()) {
			locations = Location.find("select distinct l from Location l join l.category c where c.id in :cids order by popularity desc").bind("cids", cids).fetch(9);
		} else {
			locations = Location.find("order by popularity desc").fetch(9);
		}
		return locations;
	}

	public List<Location> getObscureLocations(User u, SearchQuery q) {
		List<Long> levelOne = new ArrayList<Long>();
		List<Score> scores = null;
		if (q.hasCategory()) {
			levelOne = getLevelOneCategories(q.getCategories());
			scores = Score.find("select s from Score s join s.category c where s.user = :uid and s.score > 0 and c.id in :cids order by s.score desc ").bind("uid", u).bind("cids", q.getCategories()).fetch(9);
		} else {
			scores = Score.find("select s from Score s where s.user = :uid and s.score > 0 order by s.score desc ").bind("uid", u).fetch(9);
		}
		if (CollectionUtils.isNotEmpty(scores) || q.hasCategory()) {
			List<Long> cids = new ArrayList<Long>();
			for (Score s : scores) {
				cids.add(s.category.id);
			}
			if (CollectionUtils.isEmpty(cids)) {
				cids.addAll(q.getCategories());
			}
			List<Location> locations = null;
			if (q.hasCity() && q.hasCategory()) {
				List<Location> levelOneLocations = Location.find("select distinct l from Location l join l.category c join l.address a where c.id in :levelOnes and a.city.id in :aids order by l.popularity asc").bind("levelOnes", levelOne).bind("aids", q.getCities()).fetch(9);
				locations = handleObscureWithCategories(levelOneLocations, cids);
			} else if (q.hasCity()) {
				locations = Location.find("select distinct l from Location l join l.category c join l.address a where c.id not in :cids and a.city.id in :aids order by l.popularity asc").bind("cids", cids).bind("aids", q.getCities()).fetch(9);
			} else if (q.hasCategory()) {
				List<Location> levelOneLocations = Location.find("select l from Location l join l.category c where c.id in :levelOnes order by l.popularity asc").bind("levelOnes", levelOne).fetch();
				locations = handleObscureWithCategories(levelOneLocations, cids);
			} else {
				locations = Location.find("select distinct l from Location l join l.category c where c.id not in :cids order by l.popularity asc").bind("cids", cids).fetch(9);
			}
			return locations;
		}
		return getPopularLocations(u, q);
	}
	
	private List<Long> getLevelOneCategories(List<Long> categories) {
		List<Long> levelOne = new ArrayList<Long>();
		for (Long i : categories) {
			Long lid = Location.find("select l.id from Location l join l.category c where c.id = :cid").bind("cid", i).first();				
			Long id = Location.find("select c.id from Location l join l.category c where l.id = :lid and c.level = 1").bind("lid", lid).first();
			levelOne.add(id);
		}
		return levelOne;
	}
	
	private List<Location> handleObscureWithCategories(List<Location> levelOneLocations, List<Long> cids) {
		List<Location> locations = new ArrayList<Location>();
		for (int i = 0; i < levelOneLocations.size() && locations.size() < 9; i++) {
			Location l = levelOneLocations.get(i);
			boolean include = true;
			for (Category c : l.category) {
				if (cids.contains(c.id)) {
					include = false;
				}
			}
			if (include) {
				locations.add(l);
			}
		}
		return locations;
	}
	
}
