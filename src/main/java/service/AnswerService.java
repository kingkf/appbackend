package service;

import java.util.List;

import models.Answer;

public interface AnswerService {
	
	public Long addAnswer(String info, Long userId, Long topicId);
	
	public Answer getAnswerById(Long id);
	
	public List<Answer> getAnswersByTopicId(Long topicId);
	
	public List<Answer> getAnswersByUserId(Long userId);
	

}
