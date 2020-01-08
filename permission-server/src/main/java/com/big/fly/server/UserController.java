package com.big.fly.server;

import com.big.fly.domain.Pagination;
import com.big.fly.domain.UserParamDTO;
import com.big.fly.domain.UserResultDTO;
import com.big.fly.mapper.entity.SysUser;
import com.big.fly.permission.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description
 * Author ayt  on
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	UserService userService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<UserResultDTO> getlist(UserParamDTO userParamDTO) {
		return userService.getUserList(userParamDTO);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Boolean add(@RequestBody UserParamDTO userParamDTO) {
		return userService.add(userParamDTO);
	}


	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Boolean edit(@RequestBody UserParamDTO userParamDTO) {
		return userService.update(userParamDTO);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Boolean delete(@RequestParam int id) {
		return userService.delete(id);
	}

	@RequestMapping(value = "/isactive", method = RequestMethod.POST)
	@ResponseBody
	public Boolean isActive(@RequestParam int userId,Boolean status) {
		return userService.isActive(status,userId);
	}

}
