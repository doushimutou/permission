package com.big.fly.impl;

import com.big.fly.domain.MenuResultDTO;
import com.big.fly.domain.Pagination;
import com.big.fly.domain.RoleParamDTO;
import com.big.fly.domain.SysResultRole;
import com.big.fly.mapper.dao.SysMenuMapper;
import com.big.fly.mapper.dao.SysRoleMapper;
import com.big.fly.mapper.entity.SysMenu;
import com.big.fly.mapper.entity.SysRole;
import com.big.fly.mapper.entity.SysRoleExample;
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
	MenuServiceImpl menuServiceimpl;
	@Resource
	SysMenuMapper sysMenuMapper;

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
		List<SysRole> list = sysRoleMapper.selectByExample(example);
		List<SysResultRole> endRoles = new ArrayList<>();
		//将查询到的list,按照name分组
		Map<String, List<SysRole>> groupRole = list.stream().collect(Collectors.groupingBy(SysRole::getName));
		//将分组后的数据整合，每组放进一个list
		groupRole.forEach((name, sysRoles) -> {
			SysResultRole sysResultRole = new SysResultRole();
			Set<Integer> menuIds = sysRoles.stream().map(sysRole -> sysRole.getMenuId()).collect(Collectors.toSet());
			sysResultRole.setMenuIds(menuIds);
			sysResultRole.setName(name);
			sysResultRole.setCreateTime(sysRoles.get(0).getCreateTime());
			sysResultRole.setDesc(sysRoles.get(0).getDesc());
			endRoles.add(sysResultRole);
		});
		//将最终的结果分页
		List pageList = PageUtils.startPage(endRoles, paramDTO.getPageNum() + 1, paramDTO.getPageSize());
		Integer conut = groupRole.size();
		Pagination<SysResultRole> pagination = new Pagination<>();
		pagination.setCurrentPage(paramDTO.getPageNum());
		pagination.setPageSize(paramDTO.getPageSize());
		pagination.setList(pageList);
		pagination.setTotalCount(Math.toIntExact(conut));
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
			SysRole sysRole = new SysRole();
			sysRole.setName(roleParamDTO.getName());
			sysRole.setDesc(roleParamDTO.getDesc());
			sysRole.setCreateTime(new Date());
			sysRole.setMenuId(menuId);
			isSuccess[0] = sysRoleMapper.insert(sysRole) > 0;
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
		delete(roleParamDTO.getName());
		return add(roleParamDTO);
	}

	/**
	 * 删除
	 *
	 * @param name
	 * @return
	 */
	@Override
	public Boolean delete(String name) {
		SysRoleExample sysRoleExample = new SysRoleExample();
		SysRoleExample.Criteria criteria = sysRoleExample.createCriteria();
		criteria.andNameEqualTo(name);
		List<SysRole> list = sysRoleMapper.selectByExample(sysRoleExample);
		Set<Integer> sets = list.stream().map(SysRole::getId).collect(Collectors.toSet());
		final boolean[] isSuccess = new boolean[1];
		sets.forEach(id ->
				isSuccess[0] = sysRoleMapper.deleteByPrimaryKey(id) > 0
		);
		return isSuccess[0];
	}
}
