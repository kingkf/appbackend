package service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import exception.NoSuchUserOrPasswordErrorException;
import models.User;

/**
 * @author kaifengjin
 *
 */
public interface UserService {
	
	public User login(String emailOrUsername, String password)
			throws NoSuchUserOrPasswordErrorException,
			UnsupportedEncodingException, NoSuchAlgorithmException;
    
	public User getUserById(Long id);
	
}
