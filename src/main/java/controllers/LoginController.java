package controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import exception.NoSuchUserOrPasswordErrorException;
import service.UserService;
import models.User;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Post;

/**
 * 登录模块
 * @author kaifengjin
 *
 */

@Path("login")
public class LoginController {
	private static int STATUS_CODE_SUCCESS = 1;

    private static int STATUS_CODE_NOT_EXIST = 0;

    private static int STATUS_CODE_PASSWORD_FORMAT_ERROR = -1;

    private static int STATUS_CODE_WRONG_PASSWORD = -2;

    private static int STATUS_CODE_FOBIDDEN = -3;

    private static int STATUS_CODE_RENREN_ACCOUNT_UNACTIVED = -4;

    private static int STATUS_CODE_WRONG_VALID_CODE = -5;

    private static int STATUS_CODE_UNKNOWN = -100;

    private static Map<Integer, String> STATUS_MAP;
    
    static {
        STATUS_MAP = new HashMap<Integer, String>();
        STATUS_MAP.put(STATUS_CODE_SUCCESS, "登录成功");
        STATUS_MAP.put(STATUS_CODE_NOT_EXIST, "用户不存在");
        STATUS_MAP.put(STATUS_CODE_PASSWORD_FORMAT_ERROR, "账户密码为空等各种格式问题");
        STATUS_MAP.put(STATUS_CODE_WRONG_PASSWORD, "账户密码错误");
        STATUS_MAP.put(STATUS_CODE_FOBIDDEN, "您的账号存在异常，已暂时锁定");
        STATUS_MAP.put(STATUS_CODE_RENREN_ACCOUNT_UNACTIVED, "人人账户未激活");
        STATUS_MAP.put(STATUS_CODE_WRONG_VALID_CODE, "验证码错误");
        STATUS_MAP.put(STATUS_CODE_UNKNOWN, "其他未知异常");
    }

    private static final int MAX_LOGIN_TIMES = 3;//日后添加验证码用
    
    @Autowired
    private UserService userService;
	
	@Post
	public String index(Invocation inv,
			@Param("userId") String emailOrUsername,
			@Param("password") String password) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isBlank(emailOrUsername) || StringUtils.isBlank(password)) {
            /*map.put("succ", STATUS_CODE_PASSWORD_FORMAT_ERROR);
            map.put("msg", STATUS_MAP.get(STATUS_CODE_PASSWORD_FORMAT_ERROR));*/
            return "@" + new Gson().toJson(null);
        }
		
		/*try {
			User user = userService.login(emailOrUsername, password);
			if (user == null) {
                map.put("succ", STATUS_CODE_NOT_EXIST);
                map.put("msg", STATUS_MAP.get(STATUS_CODE_NOT_EXIST));
                return "@" + new JSONObject(map);
            }
			map.put("succ", STATUS_CODE_SUCCESS);
			map.put("msg", STATUS_MAP.get(STATUS_CODE_SUCCESS));
		} catch (NoSuchUserOrPasswordErrorException e) {
			map.put("succ", STATUS_CODE_WRONG_PASSWORD);
			map.put("msg", STATUS_MAP.get(STATUS_CODE_WRONG_PASSWORD));
			return "@" + new JSONObject(map);
		} catch (Exception e) {
			map.put("succ", STATUS_CODE_UNKNOWN);
			map.put("msg", STATUS_MAP.get(STATUS_CODE_UNKNOWN));
			return "@" + new JSONObject(map);
		}*/
		User user = null;
		try {
			user = userService.login(emailOrUsername, password);
			
			return "@" + new Gson().toJson(user);
			
		} catch (Exception e) {
			return "@" + new Gson().toJson(user);
		}

	}

}
