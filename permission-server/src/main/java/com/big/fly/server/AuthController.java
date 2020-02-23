package com.big.fly.server;

import com.big.fly.domain.MenuParamDTO;
import com.big.fly.domain.MenuResultDTO;
import com.big.fly.domain.Pagination;
import com.big.fly.permission.AuthService;
import com.big.fly.permission.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * Description
 * Author ayt  on
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

	@Resource
	AuthService authService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String username,  String password) {
		return authService.login(username, password);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Boolean logout(@RequestParam(required = false) String token) {
		return true;
	}


}
