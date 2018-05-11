package com.crm_ssh02.service;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm_ssh02.dao.CustomerDao;
import com.crm_ssh02.dao.VisitDao;
import com.crm_ssh02.domain.Customer;
import com.crm_ssh02.domain.Visit;
import com.crm_ssh02.utils.PageBean;

/**
 * 客户拜访的业务层
 * @author Peter
 */
@Service(value="visitService")
@Transactional
public class VisitServiceImpl implements VisitService {
	
	@Resource(name="visitDao")
	private VisitDao visitDao;
	@Resource(name="customerDao")
	private CustomerDao customerDao;

	/**
	 * 保存拜访记录
	 */
	public void save(Visit visit) {
		Customer customer = customerDao.findById(visit.getCustomer().getCust_id());
		visit.setCustomer(customer);
		visitDao.save(visit);
	}

	/**
	 * 按条件分页查询
	 */
	public PageBean<Visit> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria) {
		return visitDao.findByPage(pageCode, pageSize, criteria);
	}
	
}
