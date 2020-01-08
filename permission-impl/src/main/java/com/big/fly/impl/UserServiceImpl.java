package com.big.fly.impl;

import com.big.fly.domain.Pagination;
import com.big.fly.domain.UserParamDTO;
import com.big.fly.domain.UserResultDTO;
import com.big.fly.mapper.dao.SysRoleMapper;
import com.big.fly.mapper.dao.SysUserMapper;
import com.big.fly.mapper.dao.SysUserRoleMapper;
import com.big.fly.mapper.entity.*;
import com.big.fly.permission.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 * Author ayt  on
 */
@Component
public class UserServiceImpl implements UserService {

	@Resource
	SysUserMapper sysUserMapper;
	@Resource
	SysUserRoleMapper userRoleMapper;

	@Resource
	SysRoleMapper sysRoleMapper;

	@Override
	public Pagination<UserResultDTO> getUserList(UserParamDTO userParamDTO) {
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		if (userParamDTO.getName() != null) {
			criteria.andNameLike("%" + userParamDTO.getName() + "%");
		}
		if (userParamDTO.getStatus() != null) {
			criteria.andStatusEqualTo(userParamDTO.getStatus());
		}
		Long totalCount = sysUserMapper.countByExample(example);
		example.page(userParamDTO.getPageNum(), userParamDTO.getPageSize());
		List<SysUser> userList = sysUserMapper.selectByExample(example);
		List<UserResultDTO> endUsers = new ArrayList<>();
		userList.forEach(sysUser -> {
			//查询账户对应的角色ID
			SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
			SysUserRoleExample.Criteria criteria1 = sysUserRoleExample.createCriteria();
			criteria1.andUserIdEqualTo(sysUser.getId());
			List<SysUserRole> sysUserRoles = userRoleMapper.selectByExample(sysUserRoleExample);
			Set<Integer> roleIds = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toSet());
			//查询得到角色对应的role
			Set<SysRole> roles = roleIds.stream().map(roleId ->
					sysRoleMapper.selectByPrimaryKey(roleId)).collect(Collectors.toSet());
			UserResultDTO userResultDTO = new UserResultDTO();
			userResultDTO.setSets(roles);
			BeanUtils.copyProperties(sysUser, userResultDTO);
			endUsers.add(userResultDTO);
		});

		Pagination<UserResultDTO> pagination = new Pagination<>();
		pagination.setTotalCount(Math.toIntExact(totalCount));
		pagination.setCurrentPage(userParamDTO.getPageNum());
		pagination.setPageSize(userParamDTO.getPageSize());
		pagination.setList(endUsers);
		return pagination;
	}

	@Override
	public Boolean add(UserParamDTO userParamDTO) {
		if (userParamDTO.getStatus() == null) {
			userParamDTO.setStatus(0);
		}
		if (userParamDTO.getCreateTime() == null) {
			userParamDTO.setCreateTime(new Date());
		}
		sysUserMapper.insert(userParamDTO);
		boolean[] isSuccess = new boolean[1];
		Set<Integer> roleIds = userParamDTO.getRoleIds();
		roleIds.forEach(roleId -> {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setRoleId(roleId);
			sysUserRole.setUserId(userParamDTO.getId());
			isSuccess[0] = userRoleMapper.insert(sysUserRole) > 0;
		});
		return isSuccess[0];
	}

	@Override
	public Boolean update(UserParamDTO userParamDTO) {
		return null;
	}

	@Override
	public Boolean delete(int userId) {
		sysUserMapper.deleteByPrimaryKey(userId);
		SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = sysUserRoleExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userRoleMapper.deleteByExample(sysUserRoleExample) > 0;

	}

	@Override
	public Boolean isActive(Boolean isActive, int userId) {
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
		sysUser.setStatus(isActive ? 1 : 0);
		return sysUserMapper.updateByPrimaryKey(sysUser) > 0;
	}
}
