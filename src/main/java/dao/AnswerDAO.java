package dao;

import java.util.List;

import models.Answer;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

@DAO
public interface AnswerDAO {
	
	@ReturnGeneratedKeys
	@SQL("insert into `answer` (info, user_id, topic_id) values (:info, :userId, :topicId)")
	public Long addAnswer(@SQLParam("info") String info,
			@SQLParam("userId") Long userId, @SQLParam("topicId") Long topicId);
	
	@SQL("select id, score, grade_number, info, user_id, topic_id from `answer` where id=(:id)")
	public Answer getAnswerById(@SQLParam("id") Long id);
	
	@SQL("select id, score, grade_number, info, user_id, topic_id from `answer` where topic_id=(:id)")
	public List<Answer> getAnswersByTopicId(@SQLParam("id") Long id);
	
	@SQL("select id, score, grade_number, info, user_id, topic_id from `answer` where user_id=(:id)")
	public List<Answer> getAnswersByUserId(@SQLParam("id") Long id);
	
	

}
