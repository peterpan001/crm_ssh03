package com.crm_ssh02.service;

import org.springframework.transaction.annotation.Transactional;

import com.crm_ssh02.dao.UserDao;
import com.crm_ssh02.domain.User;
import com.crm_ssh02.utils.MD5Utils;

/**
 * 用户的业务层
 * @author Peter
 */
@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 检查用户名是否有一样的
	 */
	public User checkCode(String user_code) {
		return userDao.checkCode(user_code);
	}

	/**
	 * 用户注册
	 */
	public void regist(User user) {
		//给密码加密
		String password = user.getUser_password();
		user.setUser_password(MD5Utils.md5(password));
		user.setUser_state("1");
		//注册
		userDao.regist(user);
	}

	

	/**
	 * 用户登录
	 */
	public User login(User user) {
		//给密码加密
		String password = user.getUser_password();
		user.setUser_password(MD5Utils.md5(password));
		return userDao.login(user);
	}
	
}
