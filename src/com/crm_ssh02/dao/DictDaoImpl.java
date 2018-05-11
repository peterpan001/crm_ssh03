package com.crm_ssh02.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm_ssh02.domain.Dict;

/**
 * 字典的持久层：Dao层
 * @author Peter
 */
public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

	/**
	 * 通过dict_type_code查找字典表中的dict_item_name
	 */
	@SuppressWarnings("unchecked")
	public List<Dict> findDictByCode(String dict_type_code) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Dict.class);
		criteria.add(Restrictions.eq("dict_type_code", dict_type_code));
		List<Dict> list = (List<Dict>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

}
