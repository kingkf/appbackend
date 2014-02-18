package service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import dao.TopicDAO;

import models.Topic;
import service.TopicService;

public class TopicServiceImpl implements TopicService {
	
	@Autowired
	private TopicDAO topicDAO;
	
	public Topic getOneTopic() {
		Long topicNumber = topicDAO.getTopicNumber();
		
		Random random = new Random();
		Long ulTopicIndex = random.nextLong();
		while(ulTopicIndex <= 0) {
			ulTopicIndex = random.nextLong();
		}
		ulTopicIndex = ulTopicIndex % topicNumber;
		
		Topic topic = topicDAO.getTopicById(ulTopicIndex);
		
	}

}
