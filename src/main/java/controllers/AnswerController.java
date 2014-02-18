package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import service.AnswerService;
import service.TopicService;
import service.UserService;

import models.Answer;
import models.Topic;
import models.User;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

@Path("")
public class AnswerController {
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TopicService topicService;
	
	@Get("GetReference")
	public String getAnswers(Invocation inv) {
		HttpServletRequest request = inv.getRequest();
		Long topicId = Long.parseLong(request.getParameter("topicId"));
		List<Object> response = new ArrayList<Object>();
		
		List<Answer> answers = answerService.getAnswersByTopicId(topicId);
		for (Answer ans : answers) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("answerId", ans.getId());
			map.put("answerScore", ans.getScore());
			map.put("answerInfo", ans.getInfo());
			Long userId = ans.getUserId();
			User user = userService.getUserById(userId);
			map.put("userName", user.getUsername());
			response.add(map);
		}
		
		return "@" + new Gson().toJson(response);
		
	}
	
	@Get("GetStudyHistory")
	public String getStudyHistory(Invocation inv) {
		HttpServletRequest request = inv.getRequest();
		
		Long userId = Long.parseLong(request.getParameter("userId"));
		List<Answer> answers = answerService.getAnswersByUserId(userId);
		List<Object> response = new ArrayList<Object>();
		
		for (Answer ans : answers) {
			Map<String, Object> map = new HashMap<String, Object>();
			Long topicId = ans.getTopicId();
			Topic topic = topicService.getTopicById(topicId);
			map.put("topicId", topicId);
			map.put("topicInfo", topic.getInfo());
			map.put("answerId", ans.getId());
			map.put("answerScore", ans.getScore());
			map.put("answerInfo", ans.getInfo());
			response.add(map);
		}
		
		return "@" + new Gson().toJson(response);
	}
	
	@Post("SubmitAnswer")
	public String submitAnswer(Invocation inv) {
		HttpServletRequest request = inv.getRequest();
		Long userId = Long.parseLong(request.getParameter("userId"));
		Long topicId = Long.parseLong(request.getParameter("topicId"));
		String answerInfo = request.getParameter("answerInfo");
		Long userTopicRating = Long.parseLong(request.getParameter("userTopicRating"));
		Map<String, Object> map = new HashMap<String, Object>();
		if (answerInfo.equals("")) {
			map.put("succ", -1);
			map.put("msg", "回答不能为空");
			
			return "@" + new Gson().toJson(map);
			
		}
		
		answerService.addAnswer(answerInfo, userId, topicId);
		topicService.score(topicId, userTopicRating);
		map.put("succ", 1);
		map.put("msg", "答案添加成功");
		
		return "@" + new Gson().toJson(map);
	}

}
