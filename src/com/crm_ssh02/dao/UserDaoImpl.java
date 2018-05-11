package com.crm_ssh02.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm_ssh02.domain.User;

/**
 * 用户的持久层
 * @author Peter
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	/**
	 * 检查用户名是否有一样的
	 */
	@SuppressWarnings("unchecked")
	public User checkCode(String user_code) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code = ?", user_code);
		if(list!=null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 用户注册
	 */
	public void regist(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 用户登录功能
	 */
	@SuppressWarnings("unchecked")
	public User login(User user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password", user.getUser_password()));
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
}
