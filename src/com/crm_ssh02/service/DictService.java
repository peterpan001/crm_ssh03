package com.crm_ssh02.service;

import java.util.List;

import com.crm_ssh02.domain.Dict;

public interface DictService {

	List<Dict> findDictByCode(String dict_type_code);

}
