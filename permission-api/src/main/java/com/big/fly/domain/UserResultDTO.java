package com.big.fly.domain;

import com.big.fly.mapper.entity.SysRole;
import com.big.fly.mapper.entity.SysUser;

import java.util.Set;

/**
 * Description
 * Author ayt  on
 */
public class UserResultDTO extends SysUser {

	/**
	 * 账户关联的角色
	 */
	private Set<SysRole> sets;

	public Set<SysRole> getSets() {
		return sets;
	}

	public void setSets(Set<SysRole> sets) {
		this.sets = sets;
	}
}
