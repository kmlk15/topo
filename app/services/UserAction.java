package services;

public enum UserAction {

	SEARCH(1),
	RESULT_LIKE(1),
	RESULT_DISLIKE(-1),
	DETAIL_VIEW(1),
	DETAIL_LIKE(1),
	DETAIL_DISLIKE(-1);
	
	UserAction(final double value) {
		_value = value;
	}
	
	private double _value;
	
	public double getValue() {
		return _value;
	}
}
