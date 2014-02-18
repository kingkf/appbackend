package service;

import models.Answer;

public interface AnswerService {
	
	public Long addAnswer(String info, Long userId, Long topicId);
	
	public Answer getAnswerById(Long id);
	

}
