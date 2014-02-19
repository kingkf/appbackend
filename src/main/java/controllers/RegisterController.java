package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import service.UserService;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Post;


@Path("regist")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@Post
	public String regist(Invocation inv) {
		HttpServletRequest request = inv.getRequest();
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		int ret = userService.addUser(username, password, email);
		
		
		//FIXME: 为了兼容客户端= =
		if (ret == 1) {
			return "@" + "0";
		} else {
			return "@" + "1";
			
		}
		
		
	}

}
