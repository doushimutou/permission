package com.big.fly.permission;

import com.big.fly.domain.MenuParamDTO;
import com.big.fly.domain.MenuResultDTO;
import com.big.fly.domain.Pagination;
import com.big.fly.mapper.entity.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * Created by ayt on ${DTAE}
 * just try
 */
public interface MenuService {

	/**
	 * 获取列表
	 *
	 * @param menuParamDTO
	 * @return
	 */
	Pagination<MenuResultDTO> getMenuList(MenuParamDTO menuParamDTO);

	/**
	 * 添加菜单
	 *
	 * @param menuParamDTO
	 * @return
	 */
	Boolean add(MenuParamDTO menuParamDTO);

	/**
	 * 更新菜单
	 *
	 * @param menuParamDTO
	 * @return
	 */
	Boolean update(MenuParamDTO menuParamDTO);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	Boolean delete(Integer id);

	/**
	 * menuselect供新增时候父级下拉使用
	 *
	 * @return
	 */
	List<Map<String, Object>> getMenuTree(List<MenuResultDTO> list);
}
