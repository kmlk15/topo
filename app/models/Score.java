package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class Score extends Model {

	public Score(User u, Category c, double s) {
		this.user = u;
		this.category = c;
		this.score = s;
	}
	
	@OneToOne
	public Category category;
	
	@ManyToOne
	public User user;
	
	public Double score;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(user.toString()).append(" - ").append(category.toString()).append(" - ").append(score);
		return sb.toString();
	}
	
	public static Score findOrCreate(User u, Category c, double score) {
		Score s = Score.find("user = :uid and category = :cid").bind("uid", u).bind("cid", c).first();
		if (s != null) {
			s.score += score;
		} else {
			s = new Score(u, c, score);
		}
		s.save();
		return s;
	}
}
