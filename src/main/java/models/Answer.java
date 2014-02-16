package models;

public class Answer {
	private Long id;
	private Long score;
	private Long gradeNumber;
	private String info;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Long getGradeNumber() {
		return gradeNumber;
	}
	public void setGradeNumber(Long gradeNumber) {
		this.gradeNumber = gradeNumber;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}
