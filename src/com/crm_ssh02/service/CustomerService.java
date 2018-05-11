package com.crm_ssh02.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm_ssh02.domain.Customer;
import com.crm_ssh02.utils.PageBean;

public interface CustomerService {

	void add(Customer customer);

	PageBean<Customer> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria);

	Customer findCustomerById(Long cust_id);

	void edit(Customer customer);

	void delete(Customer customer);

	List<Customer> findAll();

}
