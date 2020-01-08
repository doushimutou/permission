package com.big.fly.domain;

import com.big.fly.mapper.entity.SysUser;

import java.util.Set;

/**
 * Description
 * Author ayt  on
 */
public class UserParamDTO extends SysUser {
	/**
	 * 角色ID
	 */
	Set<Integer> roleIds;
	/**
	 * 每页多少条
	 */
	private Integer pageSize;
	/**
	 * 第几页
	 */
	private Integer pageNum;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Set<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Integer> roleIds) {
		this.roleIds = roleIds;
	}
}
