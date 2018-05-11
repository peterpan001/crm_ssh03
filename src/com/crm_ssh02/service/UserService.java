package com.crm_ssh02.service;

import com.crm_ssh02.domain.User;

public interface UserService {

	User checkCode(String user_code);

	void regist(User user);

	User login(User user);


}
