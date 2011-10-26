package service.constants;

public enum UserScoring {

	SEARCH(1, 1),
	RESULT_LIKE(2, 2),
	RESULT_DISLIKE(3, -2),
	DETAIL_VIEW(4, 1),
	DETAIL_LIKE(5, 2),
	DETAIL_DISLIKE(6, -2);
	
	UserScoring(final int id, final double value) {
		_id = id;
		_value = value;
	}
	
	private int _id;
	private double _value;
	
	public static UserScoring byId(int id) {
		for (UserScoring us : UserScoring.values()) {
			if (us.getId() == id) {
				return us;
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
