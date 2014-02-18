package service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;





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
	
	private static final String SALT = "amtmYnVwdEBnbWFpbC5jb20=";
	
	@Override
	public User login(String emailOrUsername, String password) throws NoSuchUserOrPasswordErrorException, UnsupportedEncodingException, NoSuchAlgorithmException {
		User user = getUserByEmailOrUsername(emailOrUsername);
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
			throw new NoSuchUserOrPasswordErrorException();
		}
		
		String hashPw = md5Hash(password);
		if(!StringUtils.equals(hashPw, user.getPassword())) {
			throw new NoSuchUserOrPasswordErrorException();
		}
		

	}
	
	private String md5Hash(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
			String h1 = md5(password);
			return md5(h1 + SALT);
		
	}
	
	private String md5(String s) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] bytesOfMessage = s.getBytes("UTF-8");

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		
		return thedigest.toString();
	}
	
	/*public static void main(String[] args) {
		String s = "123";
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
	}*/

}
