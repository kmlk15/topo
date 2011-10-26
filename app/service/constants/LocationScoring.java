package service.constants;

public enum LocationScoring {

	SEARCH(1, 1),
	RESULT_LIKE(2, 1),
	RESULT_DISLIKE(3, -1),
	DETAIL_VIEW(4, 1),
	DETAIL_LIKE(5, 1),
	DETAIL_DISLIKE(6, -1);
	
	LocationScoring(final int id, final double value) {
		_id = id;
		_value = value;
	}
	
	private int _id;
	private double _value;
	
	public static LocationScoring byId(int id) {
		for (LocationScoring ls : LocationScoring.values()) {
			if (ls.getId() == id) {
				return ls;
			}
		}
		return null;
	}
	
	public int getId() {
		return _id;
	}
	
	public double getValue() {
		return _value;
	}
}
