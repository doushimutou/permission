package com.big.fly.impl;

import com.big.fly.mapper.dao.SysUserMapper;
import com.big.fly.mapper.entity.SysUser;
import com.big.fly.mapper.entity.SysUserExample;
import com.big.fly.permission.AuthService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Description
 * Author ayt  on
 */
@Component
public class AuthServiceImpl implements AuthService {

	@Resource
	SysUserMapper sysUserMapper;

	@Override
	public String login(String userName, String password) {
		String token = null;
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andAccountEqualTo(userName);
		List<SysUser> userList = sysUserMapper.selectByExample(sysUserExample);
		if (userList.size()>0){
			if(userList.get(0).getPassword().equals(password)){
				//随机数生成token令牌
				token = UUID.randomUUID().toString();
			}
		}
		return token;
	}

	/**
	 * 退出
	 * @param token
	 * @return token
	 */
	@Override
	public String logout(String token) {
		return null;
	}
}
