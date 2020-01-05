package com.big.fly.server;

import com.big.fly.domain.MenuParamDTO;
import com.big.fly.domain.MenuResultDTO;
import com.big.fly.domain.Pagination;
import com.big.fly.mapper.entity.SysMenu;
import com.big.fly.permission.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description
 * Author ayt  on
 */
@Controller
@RequestMapping("/menus")
public class MenuController {

	@Resource
	MenuService menuService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<MenuResultDTO> getMenuList(MenuParamDTO menuParamDTO) {
		return menuService.getMenuList(menuParamDTO);
	}

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> getMenuTree() {
		List<MenuResultDTO> list = new ArrayList<>();
		return menuService.getMenuTree(list);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Boolean add(@RequestBody MenuParamDTO menuParamDTO) {
		return menuService.add(menuParamDTO);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Boolean edit(@RequestBody MenuParamDTO menuParamDTO) {
		return menuService.update(menuParamDTO);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Boolean delete(@RequestParam int id) {
		return menuService.delete(id);
	}
}
