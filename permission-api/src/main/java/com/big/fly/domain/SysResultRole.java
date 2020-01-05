package com.big.fly.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description
 * Author ayt  on
 */
@Data
public class SysResultRole {
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
	 * 菜单ID menu_id  list  List<Map<String,Object>>
	 */
	private Set<Integer> menuIds;

	/**
	 * 创建时间 create_time
	 */

	private Date createTime;
}
