package com.big.fly.permission;

import com.big.fly.domain.MenuParamDTO;
import com.big.fly.domain.Pagination;
import com.big.fly.mapper.entity.SysMenu;

/**
 * Created by ayt on ${DTAE}
 * just try
 */
public interface MenuService {

	/**
	 * 获取列表
	 * @param menuParamDTO
	 * @return
	 */
	Pagination<SysMenu> getMenuList(MenuParamDTO menuParamDTO);

	/**
	 *添加菜单
	 * @param menuParamDTO
	 * @return
	 */
	Boolean add(MenuParamDTO menuParamDTO) ;

	/**
	 * 更新菜单
	 * @param menuParamDTO
	 * @return
	 */
	Boolean update(MenuParamDTO menuParamDTO);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Boolean delete(Integer id);
}
