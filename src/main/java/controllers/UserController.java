package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import service.UserService;

import models.User;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

@Path("")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Get("GetPersonalInfo")
	public String getPersonalInfo(Invocation inv) {
		HttpServletRequest request = inv.getRequest();
		Long userId = Long.parseLong(request.getParameter("userId"));
		User user = userService.getUserById(userId);
		return "@" + new Gson().toJson(user);
	}

}
