package service;

import models.Topic;

public interface TopicService {
	
	public Topic getOneTopic();
	
	public Topic getTopicById(Long id);
	
	public void score(Long topicId, Long score);
}
