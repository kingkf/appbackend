package dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

@DAO
@Deprecated
public interface TopicUserAnsDAO {

	@SQL("insert into `topic_user_ans` (user_id, topic_id, answer_id) values (:userId, :topicId, :answerId)")
	public void addRecord(@SQLParam("userId") Long userId,
			@SQLParam("topicId") Long topicId,
			@SQLParam("answerId") Long answerId);
	
	@SQL("select user_id from `topic_user_ans` where topic_id=(:topicId)")
	public Long getUserIdByTopicId(@SQLParam("topicId") Long topicId);

}
