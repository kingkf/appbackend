package dao;

import models.User;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

/**
 * @author Administrator
 *
 */
@DAO
public interface UserDAO {
	public static String VIEW_USER = " id, username, email, password ";
	
	@SQL("select " + VIEW_USER + "from `user` where email=(:email)")
	public User getUserByEmail(@SQLParam("email") String email);
	
	@SQL("select " + VIEW_USER + "from `user` where username=(:username)")
	public User getUserByUsername(@SQLParam("username") String username);

}
