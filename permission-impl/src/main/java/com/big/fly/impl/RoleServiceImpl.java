package com.big.fly.impl;

import com.big.fly.domain.MenuResultDTO;
import com.big.fly.domain.Pagination;
import com.big.fly.domain.RoleParamDTO;
import com.big.fly.domain.SysResultRole;
import com.big.fly.mapper.dao.SysMenuMapper;
import com.big.fly.mapper.dao.SysRoleMapper;
import com.big.fly.mapper.dao.SysRoleMenuMapper;
import com.big.fly.mapper.entity.*;
import com.big.fly.permission.MenuService;
import com.big.fly.permission.RoleService;
import com.big.fly.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.management.relation.RoleResult;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 * Author ayt  on
 */
@Component
public class RoleServiceImpl implements RoleService {

	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Resource
	SysRoleMapper sysRoleMapper;
	@Resource
	SysRoleMenuMapper roleMenuMapper;


	@Override
	public Pagination<SysResultRole> getRoleList(RoleParamDTO paramDTO) {
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
		if (paramDTO.getName() != null) {
			criteria.andNameLike("%" + paramDTO.getName() + "%");
		}
		if (paramDTO.getStartTime() != null && paramDTO.getEndTime() != null) {
			criteria.andCreateTimeBetween(paramDTO.getStartTime(), paramDTO.getEndTime());
		}
		if (paramDTO.getPageNum() != null && paramDTO.getPageSize() != null) {
			example.page(paramDTO.getPageNum(), paramDTO.getPageSize());
		}
		List<SysRole> list = sysRoleMapper.selectByExample(example);
		List<SysResultRole> endRoles = new ArrayList<>();
		list.forEach(sysRole -> {
			//获取menuIds
			SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
			sysRoleMenuExample.createCriteria().andRoleIdEqualTo(sysRole.getId());
			List<SysRoleMenu> sysRoleMenus = roleMenuMapper.selectByExample(sysRoleMenuExample);
			Set<Integer> menuIds = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toSet());
			//sysResultRole 构造
			SysResultRole sysResultRole = new SysResultRole();
			sysResultRole.setMenuIds(menuIds);
			sysResultRole.setName(sysRole.getName());
			sysResultRole.setDesc(sysRole.getDesc());
			sysResultRole.setId(sysRole.getId());
			sysResultRole.setCreateTime(sysRole.getCreateTime());
			endRoles.add(sysResultRole);
		});
		Pagination<SysResultRole> pagination = new Pagination<>();
		pagination.setList(endRoles);
		pagination.setTotalCount(Math.toIntExact(endRoles.size()));
		return pagination;
	}

	/**
	 * 添加角色
	 *
	 * @param roleParamDTO
	 * @return
	 */
	@Override
	public Boolean add(RoleParamDTO roleParamDTO) {
		final boolean[] isSuccess = new boolean[1];
		Set<Integer> menuIds = roleParamDTO.getMenuIds();
		menuIds.forEach(menuId -> {
			//更新role表
			SysRole sysRole = new SysRole();
			sysRole.setName(roleParamDTO.getName());
			sysRole.setDesc(roleParamDTO.getDesc());
			sysRole.setCreateTime(new Date());
			sysRoleMapper.insert(sysRole);
			//更新rokle_menu 关联表
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setRoleId(sysRole.getId());
			sysRoleMenu.setMenuId(menuId);
			isSuccess[0] = roleMenuMapper.insert(sysRoleMenu) > 0;
		});
		return isSuccess[0];
	}

	/**
	 * 更新角色
	 *
	 * @param roleParamDTO
	 * @return
	 */
	@Override
	public Boolean update(RoleParamDTO roleParamDTO) {
		//先把原来的数据删除掉
		delete(roleParamDTO.getId());
		return add(roleParamDTO);
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Boolean delete(Integer id) {
		sysRoleMapper.deleteByPrimaryKey(id);
		SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
		SysRoleMenuExample.Criteria criteria = sysRoleMenuExample.createCriteria();
		criteria.andRoleIdEqualTo(id);
		return roleMenuMapper.deleteByExample(sysRoleMenuExample) > 0;
	}
}
