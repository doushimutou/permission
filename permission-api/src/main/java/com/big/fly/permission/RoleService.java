package com.big.fly.permission;

import com.big.fly.domain.Pagination;
import com.big.fly.domain.RoleParamDTO;
import com.big.fly.domain.SysResultRole;

/**
 * Created by ayt on ${DTAE}
 * just try
 */
public interface RoleService {
	/**
	 * 获取列表
	 * @param paramDTO
	 * @return
	 */
	Pagination<SysResultRole> getRoleList(RoleParamDTO paramDTO);

	/**
	 * 添加角色
	 * @param roleParamDTO
	 * @return
	 */
	Boolean add(RoleParamDTO roleParamDTO);

	/**
	 * 更新角色
	 * @param roleParamDTO
	 * @return
	 */
	Boolean update(RoleParamDTO roleParamDTO);

	/**
	 * 删除
	 * @return id
	 */
	Boolean delete(Integer id );
}
