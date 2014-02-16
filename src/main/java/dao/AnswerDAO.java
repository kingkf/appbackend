package dao;

import models.Answer;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

@DAO
public interface AnswerDAO {
	
	@ReturnGeneratedKeys
	@SQL("insert into `answer` info values (:info)")
	public Long addAnswer(@SQLParam("info") String info);
	
	@SQL("select id, score, grade_number, info from `answer` where id=(:id)")
	public Answer getAnswerById(@SQLParam("id") Long id);
	

}
