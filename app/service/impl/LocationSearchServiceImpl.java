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

	private static int PAGE_SIZE = 15;
	
	public List<Location> getRecommendedLocations(User u, SearchQuery q) {
		List<Score> scores = null;
		if (q.hasCategory()) {
			List<Long> levelTwo = getLevelTwoCategories(q.getCategories());
			scores = Score.find("select s from Score s join s.category c where s.user = :uid and c.id in :cids order by score desc").bind("uid", u).bind("cids", levelTwo).fetch(PAGE_SIZE);			
		} else {
			scores = Score.find("select s from Score s join s.category c where s.user = :uid and c.level = 2 order by score desc").bind("uid", u).fetch(PAGE_SIZE);
		}
		if (CollectionUtils.isNotEmpty(scores)) {
			List<Location> locations = new ArrayList<Location>();
			Iterator<Score> iter = scores.iterator();
			int count = 5;
			while (iter.hasNext() && locations.size() < PAGE_SIZE) {
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
				locations = Location.find("select distinct l from Location l join l.category c join l.address a where c.id = :cid and a.city.id in :aids").bind("cid", q.getCategories()).bind("aids", q.getCities()).fetch(PAGE_SIZE);	
			} else {
				locations = Location.find("select distinct l from Location l join l.category c where c.id = :cid").bind("cid", q.getCategories()).fetch(PAGE_SIZE);
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
			locations = Location.find("select distinct l from Location l join l.category c join l.address a where c.id in :cids and a.city.id in :aids order by popularity desc").bind("cids", cids).bind("aids", q.getCities()).fetch(PAGE_SIZE);
		} else if (q.hasCity()) {
			locations = Location.find("select distinct l from Location l join l.address a where a.city.id in :aids order by popularity desc").bind("aids", q.getCities()).fetch(PAGE_SIZE);
		} else if (q.hasCategory()) {
			locations = Location.find("select distinct l from Location l join l.category c where c.id in :cids order by popularity desc").bind("cids", cids).fetch(PAGE_SIZE);
		} else {
			locations = Location.find("order by popularity desc").fetch(PAGE_SIZE);
		}
		return locations;
	}

	public List<Location> getObscureLocations(User u, SearchQuery q) {
		List<Long> levelTwo = new ArrayList<Long>();
		List<Score> scores = null;
		if (q.hasCategory()) {
			levelTwo = getLevelTwoCategories(q.getCategories());
			scores = Score.find("select s from Score s join s.category c where s.user = :uid and s.score > 0 and c.id in :cids order by s.score desc ").bind("uid", u).bind("cids", levelTwo).fetch(PAGE_SIZE);
		} else {
			scores = Score.find("select s from Score s where s.user = :uid and s.score > 0 order by s.score desc ").bind("uid", u).fetch(PAGE_SIZE);
		}
		if (CollectionUtils.isNotEmpty(scores) || q.hasCategory()) {
			List<Long> cids = new ArrayList<Long>();
			for (Score s : scores) {
				cids.add(s.category.id);
			}
			if (CollectionUtils.isEmpty(cids)) {
				cids.addAll(levelTwo);
			}
			List<Location> locations = null;
			if (q.hasCity() && q.hasCategory()) {
				List<Location> levelOneLocations = Location.find("select distinct l from Location l join l.category c join l.address a where c.id in :levelOnes and a.city.id in :aids order by l.popularity asc").bind("levelOnes", q.getCategories()).bind("aids", q.getCities()).fetch(PAGE_SIZE);
				locations = handleObscureWithCategories(levelOneLocations, cids);
			} else if (q.hasCity()) {
				locations = Location.find("select distinct l from Location l join l.category c join l.address a where c.id not in :cids and a.city.id in :aids order by l.popularity asc").bind("cids", cids).bind("aids", q.getCities()).fetch(PAGE_SIZE);
			} else if (q.hasCategory()) {
				List<Location> levelOneLocations = Location.find("select l from Location l join l.category c where c.id in :levelOnes order by l.popularity asc").bind("levelOnes", q.getCategories()).fetch();
				locations = handleObscureWithCategories(levelOneLocations, cids);
			} else {
				locations = Location.find("select distinct l from Location l join l.category c where c.id not in :cids order by l.popularity asc").bind("cids", cids).fetch(PAGE_SIZE);
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

	private List<Long> getLevelTwoCategories(List<Long> categories) {
		List<Long> levelTwo = new ArrayList<Long>();
		for (Long i : categories) {
			List<Long> lids = Location.find("select l.id from Location l join l.category c where c.id = :cid").bind("cid", i).fetch();				
			List<Long> ids = Location.find("select c.id from Location l join l.category c where l.id in :lids and c.level = 2").bind("lids", lids).fetch();
			levelTwo.addAll(ids);
		}
		return levelTwo;
	}
	
	private List<Location> handleObscureWithCategories(List<Location> levelOneLocations, List<Long> cids) {
		List<Location> locations = new ArrayList<Location>();
		for (int i = 0; i < levelOneLocations.size() && locations.size() < PAGE_SIZE; i++) {
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
