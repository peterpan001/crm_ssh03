package com.crm_ssh02.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm_ssh02.utils.PageBean;

public interface BaseDao<T> {

	//保存
	public void save(T t);
	
	//更新
	public void update(T t);
	
	//删除
	public void delete(T t);
	
	//根据Id查找
	public T findById(Long id);
	
	//查询所有
	public List<T> findAll();
	
	//分页查询
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
