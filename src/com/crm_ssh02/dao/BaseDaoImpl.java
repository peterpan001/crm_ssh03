package com.crm_ssh02.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm_ssh02.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	//定义成员属性
	public Class clazz;
	public BaseDaoImpl(){
		//CustomerDaoImpl extends BaseDaoImpl<Customer>  map<k,v>
		//this表示子类，this.getClass表示CustomerDaoImpl的Class对象
		Class c = this.getClass();
		//第二步获取到BaseDao<Customer>
		Type type = c.getGenericSuperclass();
		//目的：把type接口转换为子接口
		if(type instanceof ParameterizedType){
			ParameterizedType ptype = (ParameterizedType) type;
			//获取到Customer
			Type[] types = ptype.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}
	}
	
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find(" from " + clazz.getSimpleName());
	}

	
	@SuppressWarnings("unchecked")
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria) {
		PageBean<T> pageBean = new PageBean<T>();
		pageBean.setPageCode(pageCode);
		pageBean.setPageSize(pageSize);
		
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list!=null && list.size() > 0){
			int totalCount = list.get(0).intValue();
			pageBean.setTotalCount(totalCount);
		}
		
		criteria.setProjection(null);
		List<T> list2 = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);
		pageBean.setBeanList(list2);
		return pageBean;
	}

	
}
