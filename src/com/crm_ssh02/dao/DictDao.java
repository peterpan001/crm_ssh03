package com.crm_ssh02.dao;

import java.util.List;

import com.crm_ssh02.domain.Dict;

public interface DictDao {

	List<Dict> findDictByCode(String dict_type_code);

}
