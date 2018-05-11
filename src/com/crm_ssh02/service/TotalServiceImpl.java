package com.crm_ssh02.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm_ssh02.dao.TotalDao;

@Service(value="totalService")
@Transactional
public class TotalServiceImpl implements TotalService {
	
	@Resource(name="totalDao")
	private TotalDao totalDao;

	public List<Object[]> findSource() {
		return totalDao.findSource();
	}

	
	public List<Object[]> findIndustry() {
		return totalDao.findIndustry();
	}
}
