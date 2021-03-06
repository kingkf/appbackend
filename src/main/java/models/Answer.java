package models;

public class Answer {
	private Long id;
	private Long score;
	private Long gradeNumber;
	private String info;
	private Long userId;
	private Long topicId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
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
