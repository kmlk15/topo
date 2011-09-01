package services;

import models.Category;
import models.Location;
import models.Score;
import models.User;
import play.data.validation.Validation;

public class ScoringService {

	public void calculate(long userId, long locationId, UserAction action) {
		User u = User.findById(userId);
		Location l = Location.findById(locationId);
		if (u != null && l != null) {
			u.score.clear();
			for (Category c : l.category) {
				Category current = c;
				while (current != null) {
					double score = current.level() * action.getValue();
					Score s = Score.findOrCreate(u, current, score);
					u.score.add(s);
					current = current.parent;
				}
			}
			Validation.current().valid(u);
			u.save();
		}
	}
}
