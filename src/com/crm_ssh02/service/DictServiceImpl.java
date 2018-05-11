package com.crm_ssh02.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.crm_ssh02.dao.DictDao;
import com.crm_ssh02.domain.Dict;

/**
 * 字典的Service层：业务层
 * @author Peter
 */
@Transactional
public class DictServiceImpl implements DictService {

	private DictDao dictDao;
	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}
	
	/**
	 * 通过dict_type_code查找字典表中的dict_item_name
	 */
	public List<Dict> findDictByCode(String dict_type_code) {
		return dictDao.findDictByCode(dict_type_code);
	}
	
}
