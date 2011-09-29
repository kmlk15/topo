package service.constants;

public enum LocationScoring {

	SEARCH(1),
	RESULT_LIKE(1),
	RESULT_DISLIKE(-1),
	DETAIL_VIEW(1),
	DETAIL_LIKE(1),
	DETAIL_DISLIKE(-1);
	
	LocationScoring(final double value) {
		_value = value;
	}
	
	private double _value;
	
	public double getValue() {
		return _value;
	}
}
