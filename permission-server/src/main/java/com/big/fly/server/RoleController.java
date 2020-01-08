package com.big.fly.server;

import com.big.fly.domain.Pagination;
import com.big.fly.domain.RoleParamDTO;
import com.big.fly.domain.SysResultRole;
import com.big.fly.permission.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description
 * Author ayt  on
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	RoleService roleService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<SysResultRole> getlist(RoleParamDTO roleParamDTO) {
		return roleService.getRoleList(roleParamDTO);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Boolean add(@RequestBody RoleParamDTO roleParamDTO) {
		return roleService.add(roleParamDTO);
	}


	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Boolean edit(@RequestBody RoleParamDTO roleParamDTO) {
		return roleService.update(roleParamDTO);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Boolean delete(@RequestParam Integer id) {
		return roleService.delete(id);
	}


}
