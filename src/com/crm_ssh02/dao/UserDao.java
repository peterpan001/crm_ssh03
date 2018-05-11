package com.crm_ssh02.dao;

import com.crm_ssh02.domain.User;

public interface UserDao {

	User checkCode(String user_code);

	void regist(User user);

	User login(User user);

}
