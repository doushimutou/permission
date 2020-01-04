package com.big.fly.impl;

import com.big.fly.domain.Pagination;
import com.big.fly.domain.RoleParamDTO;
import com.big.fly.mapper.entity.SysRole;
import com.big.fly.mapper.entity.SysRoleExample;
import com.big.fly.permission.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description
 * Author ayt  on
 */
@Component
public class RoleServiceImpl implements RoleService {

	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Override
	public Pagination<SysRole> getRoleList(RoleParamDTO paramDTO) {
		SysRoleExample example = new SysRoleExample();

		return null;
	}

	/**
	 * 添加角色
	 * @param roleParamDTO
	 * @return
	 */
	@Override
	public Boolean add(RoleParamDTO roleParamDTO) {
		return null;
	}

	/**
	 * 更新角色
	 *
	 * @param roleParamDTO
	 * @return
	 */
	@Override
	public Boolean update(RoleParamDTO roleParamDTO) {
		return null;
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Boolean delete(Integer id) {
		return null;
	}
}
