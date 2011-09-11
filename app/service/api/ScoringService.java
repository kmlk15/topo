package service.api;

import service.constants.UserAction;

public interface ScoringService {

	public void calculate(long userId, long locationId, UserAction action);
}
