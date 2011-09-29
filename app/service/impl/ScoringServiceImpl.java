package service.impl;

import models.Category;
import models.Location;
import models.Score;
import models.User;
import play.data.validation.Validation;
import service.api.ScoringService;
import service.constants.LocationScoring;
import service.constants.UserScoring;

public class ScoringServiceImpl implements ScoringService {

	public void calculate(User user, Location location, UserScoring scoring) {
		if (user != null && location != null) {
			user.score.clear();
			for (Category c : location.category) {
				Category current = c;
				while (current != null) {
					double score = current.level() * scoring.getValue();
					Score s = Score.findOrCreate(user, current, score);
					user.score.add(s);
					current = current.parent;
				}
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
