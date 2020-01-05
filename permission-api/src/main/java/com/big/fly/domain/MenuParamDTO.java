package com.big.fly.domain;

import com.big.fly.mapper.entity.SysMenu;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description
 * Author ayt  on
 */
@Data
public class MenuParamDTO extends SysMenu {

	/**
	 * 搜索条件：开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 * 搜索条件：结束时间
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

}
