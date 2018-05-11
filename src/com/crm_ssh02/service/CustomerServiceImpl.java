package com.crm_ssh02.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm_ssh02.dao.CustomerDao;
import com.crm_ssh02.domain.Customer;
import com.crm_ssh02.utils.PageBean;

/**
 * 客户的业务层，Service层
 * @author Peter
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	/**
	 * 添加客户
	 */
	public void add(Customer customer) {
		customerDao.save(customer);
	}

	/**
	 * 分页查询客户
	 */
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria) {
		return customerDao.findByPage(pageCode, pageSize, criteria);
	}

	/**
	 * 根据客户id查询客户
	 */
	public Customer findCustomerById(Long cust_id) {
		
		return customerDao.findById(cust_id);
	}

	/**
	 * 编辑客户
	 */
	public void edit(Customer customer) {

		customerDao.update(customer);
	}

	/**
	 * 删除客户
	 */
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	/**
	 * 查询所有客户
	 */
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	
	
}
