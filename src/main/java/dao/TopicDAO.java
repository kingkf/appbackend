package dao;

import models.Topic;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

@DAO
public interface TopicDAO {
	
	@SQL("select COUNT(*) from `topic`")
	public Long getTopicNumber();
	
	@SQL("select id, score, grade_number, info from `topic` where id=(:id)")
	public Topic getTopicById(@SQLParam("id") Long id);
	
	@SQL("update `topic` set score=(:score), grade_number=(:gradeNumber) where id=(:topicId)")
	public Long gradeTopic(@SQLParam("topicId") Long topicId,
			@SQLParam("score") Long score, @SQLParam("gradeNumber") Long gradeNumber);

}
