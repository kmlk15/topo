package service.impl;

import java.util.ArrayList;

import models.Category;
import models.Location;
import models.Score;
import models.User;

import org.apache.commons.collections.CollectionUtils;

import play.data.validation.Validation;
import service.api.ScoringService;
import service.constants.LocationScoring;
import service.constants.UserScoring;

public class ScoringServiceImpl implements ScoringService {

	public void calculate(User user, Location location, UserScoring scoring) {
		if (user != null && location != null) {
			if (CollectionUtils.isEmpty(user.score)) {
				user.score = new ArrayList<Score>();
			}
			user.score.clear();
			for (Category c : location.category) {
				double score = scoring.getValue();
				Score s = Score.findOrCreate(user, c, score);
				user.score.add(s);
			}
			Validation.current().valid(user);
			user.save();
		}
	}
	
	public void calculate(Location location, LocationScoring scoring) {
		if (location != null) {
			location.popularity += scoring.getValue();
			location.save();
		}
	}
}
