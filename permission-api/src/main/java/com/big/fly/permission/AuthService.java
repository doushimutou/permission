package com.big.fly.permission;



/**
 * Created by ayt on ${DTAE}
 * just try
 */
public interface AuthService {

	/**
	 * 登陆
	 * @param userName  用户名
	 * @param password	 密码
	 * @return token
	 */
	String login(String userName ,String password);

	/**
	 * 退出
	 * @return token
	 */
	String logout(String token);
}
