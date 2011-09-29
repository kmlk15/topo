package service.constants;

public enum UserScoring {

	SEARCH(1),
	RESULT_LIKE(2),
	RESULT_DISLIKE(-2),
	DETAIL_VIEW(1),
	DETAIL_LIKE(2),
	DETAIL_DISLIKE(-2);
	
	UserScoring(final double value) {
		_value = value;
	}
	
	private double _value;
	
	public double getValue() {
		return _value;
	}
}
