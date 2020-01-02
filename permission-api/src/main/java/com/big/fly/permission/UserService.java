package com.big.fly.permission;

import com.big.fly.domain.Pagination;
import com.big.fly.domain.UserParamDTO;
import com.big.fly.mapper.entity.SysUser;

/**
 * Created by ayt on ${DTAE}
 * just try
 */
public interface UserService {

	/**
	 * 获取列表
	 * @param userParamDTO
	 * @return
	 */
	Pagination<SysUser>	getUsetList(UserParamDTO userParamDTO);

	/**
	 * 添加用户
	 * @param userParamDTO
	 * @return
	 */
	Boolean add(UserParamDTO userParamDTO);

	/**
	 * 更新用户
	 * @param userParamDTO
	 * @return
	 */
	Boolean	update(UserParamDTO userParamDTO);

	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	Boolean delete(int  userId);

}
