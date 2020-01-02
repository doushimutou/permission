package com.big.fly.domain;

import com.big.fly.mapper.entity.SysRole;

/**
 * Description
 * Author ayt  on
 */
public class RoleParamDTO extends SysRole {
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
}
