package com.crm_ssh02.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm_ssh02.domain.Linkman;
import com.crm_ssh02.utils.PageBean;

public interface LinkmanService {

	void add(Linkman linkman);

	PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria);

	Linkman findLinkmanById(Long lkm_id);

	void edit(Linkman linkman);

	void delete(Linkman linkman);

}
