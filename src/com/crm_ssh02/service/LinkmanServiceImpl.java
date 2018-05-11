package com.crm_ssh02.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm_ssh02.dao.CustomerDao;
import com.crm_ssh02.dao.LinkmanDao;
import com.crm_ssh02.domain.Customer;
import com.crm_ssh02.domain.Linkman;
import com.crm_ssh02.utils.PageBean;

/**
 * 联系人的Service层：业务层
 * @author Peter
 */
@Transactional
public class LinkmanServiceImpl implements LinkmanService {

	private LinkmanDao linkmanDao;
	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * 添加联系人
	 */
	public void add(Linkman linkman) {
		Customer customer = customerDao.findById(linkman.getCustomer().getCust_id());
		linkman.setCustomer(customer);
		linkmanDao.save(linkman);
	}

	/**
	 * 带条件的分页查询联系人列表
	 */
	public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria) {
		return linkmanDao.findByPage(pageCode, pageSize, criteria);
	}

	/**
	 * 通过id查询联系人
	 */
	public Linkman findLinkmanById(Long lkm_id) {
		return linkmanDao.findById(lkm_id);
	}

	/**
	 * 修改联系人信息
	 */
	public void edit(Linkman linkman) {
		Customer c1 = customerDao.findById(linkman.getCustomer().getCust_id());
		linkman.setCustomer(c1);
		linkmanDao.update(linkman);
	}

	//删除联系人
	public void delete(Linkman linkman) {
		linkmanDao.delete(linkman);
	}
	
}
