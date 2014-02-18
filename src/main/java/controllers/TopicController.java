package controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import service.TopicService;

import models.Topic;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

@Path("")
public class TopicController {
	@Autowired
	private TopicService topicService;
	
	@Get("GetOneTopic")
	public String getOneTopic(Invocation inv) {
		Topic topic = topicService.getOneTopic();
		
		return "@" + new Gson().toJson(topic);
	}
	

}
