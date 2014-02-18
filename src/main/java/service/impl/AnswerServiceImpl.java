package service.impl;

import models.Answer;

import org.springframework.beans.factory.annotation.Autowired;

import dao.AnswerDAO;

import service.AnswerService;

public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerDAO answerDAO;
	
	@Override
	public Long addAnswer(String info, Long userId, Long topicId) {
		return answerDAO.addAnswer(info, userId, topicId);
	}
	
	@Override
	public Answer getAnswerById(Long id) {
		return answerDAO.getAnswerById(id);
	}

}
