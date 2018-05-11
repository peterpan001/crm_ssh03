package com.crm_ssh02.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.crm_ssh02.domain.Visit;
import com.crm_ssh02.utils.PageBean;
/**
 * 客户拜访的持久层：Dao
 * @author Peter
 */
@Repository(value="visitDao")
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao {

	@Resource(name="sessionFactory")
	public void set2SessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
}
