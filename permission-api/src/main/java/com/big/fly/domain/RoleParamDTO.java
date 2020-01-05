package com.big.fly.domain;

import com.big.fly.mapper.entity.SysRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * Description
 * Author ayt  on
 */
public class RoleParamDTO{
	/**
	 *  id
	 */
	private Integer id;

	/**
	 * 名称 name
	 */
	private String name;

	/**
	 * 描述 desc
	 */
	private String desc;

	/**
	 * 菜单IDS
	 */
	private Set<Integer> menuIds;
	/**
	 * 创建时间的开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	/**
	 * 创建时间的结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<Integer> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(Set<Integer> menuIds) {
		this.menuIds = menuIds;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
