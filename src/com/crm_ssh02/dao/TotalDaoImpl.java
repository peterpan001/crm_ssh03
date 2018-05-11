package com.crm_ssh02.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository(value="totalDao")
public class TotalDaoImpl extends HibernateDaoSupport implements TotalDao {

	@Resource(name="sessionFactory")
	public void set2SessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> findSource() {
		String hql = "select c.source.dict_item_name, count(*) from Customer c inner join c.source group by c.source";
		List<Object []> list = (List<Object[]>) this.getHibernateTemplate().find(hql);
		return list;
	}

	
	@SuppressWarnings("unchecked")
	public List<Object[]> findIndustry() {
		String hql = "select c.industry.dict_item_name, count(*) from Customer c inner join c.industry group by c.industry";
		List<Object []> list = (List<Object[]>) this.getHibernateTemplate().find(hql);
		return list;
	}

}
