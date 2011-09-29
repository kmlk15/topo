package service.api;

import models.Location;
import models.User;
import service.constants.LocationScoring;
import service.constants.UserScoring;

public interface ScoringService {

	public void calculate(User user, Location location, UserScoring scoring);
	
	public void calculate(Location location, LocationScoring scoring);
}
