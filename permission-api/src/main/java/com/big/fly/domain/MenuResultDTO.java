package com.big.fly.domain;

import com.big.fly.mapper.entity.SysMenu;

import java.util.List;

/**
 * Description
 * Author ayt  on
 */
public class MenuResultDTO extends SysMenu{
	/**
	 * 子菜单
	 */
	List<MenuResultDTO> children;

	public List<MenuResultDTO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuResultDTO> children) {
		this.children = children;
	}
}
