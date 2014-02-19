package service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import dao.TopicDAO;

import models.Topic;
import service.TopicService;

public class TopicServiceImpl implements TopicService {
	
	@Autowired
	private TopicDAO topicDAO;
	
	//FIXME:应该根据用户的答题来选择topic，而不是随机
	@Override
	public Topic getOneTopic() {
		Long topicNumber = topicDAO.getTopicNumber();
		
		Random random = new Random();
		Long ulTopicIndex = random.nextLong();
		while(ulTopicIndex <= 0) {
			ulTopicIndex = random.nextLong();
		}
		ulTopicIndex = ulTopicIndex % topicNumber + 1;
		
		Topic topic = topicDAO.getTopicById(ulTopicIndex);
		return topic;
		
	}
	
	@Override
	public Topic getTopicById(Long topicId) {
		return topicDAO.getTopicById(topicId);
	}

	@Override
	public void score(Long topicId, Long score) {
		if (score == 0) return;
	    Topic topic = topicDAO.getTopicById(topicId);
	    Long topicScore = topic.getScore();
	    Long gradeNumber = topic.getGradeNumber();
	    topicScore = (topicScore*gradeNumber + score)/(gradeNumber+1);
	    gradeNumber += 1;
	    topicDAO.gradeTopic(topicId, topicScore, gradeNumber);
	}

}
