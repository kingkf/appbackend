package service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;





import service.UserService;
import dao.UserDAO;
import exception.NoSuchUserOrPasswordErrorException;
import models.User;

/**
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;
	
	private final Log logger = LogFactory.getLog(getClass());
	
	private static final String SALT = "amtmYnVwdEBnbWFpbC5jb20=";
	
	@Override
	public User login(String emailOrUsername, String password) throws NoSuchUserOrPasswordErrorException, UnsupportedEncodingException, NoSuchAlgorithmException {
		User user = getUserByEmailOrUsername(emailOrUsername);
		logger.info("login user: " + new Gson().toJson(user));
		verifyPassword(user, password);
		
		return user;
	}
	
	@Override
	public User getUserById(Long id) {
		return userDAO.getUserById(id);
	}
	
	private User getUserByEmailOrUsername(String emailOrUsername) {
		emailOrUsername = StringUtils.trim(emailOrUsername);
		if (StringUtils.isBlank(emailOrUsername)) {
			return null;
		}
		if (emailOrUsername.contains("@")) {
			return userDAO.getUserByEmail(emailOrUsername);
		}
		
		return userDAO.getUserByUsername(emailOrUsername);
		
	}
	
	private void verifyPassword(User user, String password)
			throws NoSuchUserOrPasswordErrorException, UnsupportedEncodingException, NoSuchAlgorithmException {
		if (user == null) {
			logger.info("user null");
			throw new NoSuchUserOrPasswordErrorException();
		}
		
		String hashPw = md5Hash(password);
		logger.info("login password: " + hashPw);
		logger.info("user password: " + user.getPassword());
		
		if(!StringUtils.equals(hashPw, user.getPassword())) {
			logger.info("no  such user");
			throw new NoSuchUserOrPasswordErrorException();
		}
		

	}
	
	private String md5Hash(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
			String h1 = md5(password);
			return md5(h1 + SALT);
		
	}
	
	private String md5(String s) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		logger.info("s:" + s);
		byte[] bytesOfMessage = s.getBytes("UTF-8");
		logger.info("bytesOfMessage:" + bytesOfMessage);

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		
		return new String(thedigest);
	}

	@Override
	public int addUser(String username, String password, String email){
		try {
			String hsw = md5Hash(password);
		    userDAO.addUser(email, hsw, username);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	public static void main(String[] args) {
		String s = "ddd";
		byte[] bytesOfMessage=null;
		try {
			bytesOfMessage = s.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] thedigest = md.digest(bytesOfMessage);
		System.out.println(thedigest.toString());
	}

	@Override
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
		
	}

}
