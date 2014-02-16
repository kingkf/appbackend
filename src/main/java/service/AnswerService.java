package service;

import models.Answer;

public interface AnswerService {
	
	public Long addAnswer(String info);
	
	public Answer getAnswerById(Long id);
	

}
