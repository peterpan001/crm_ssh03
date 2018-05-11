package com.crm_ssh02.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.crm_ssh02.domain.Dict;
import com.crm_ssh02.service.DictService;
import com.crm_ssh02.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 字典的Action
 * @author Peter
 */
public class DictAction extends ActionSupport implements ModelDriven<Dict>{
	

	private static final long serialVersionUID = 531489084942056761L;
	private DictService dictService;
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	private Dict dict = new Dict();
	public Dict getModel() {
		// TODO Auto-generated method stub
		return dict;
	}

	/**
	 * 通过dict_type_code查找dict_item_name
	 * @return
	 */
	public String findDictByCode(){
		List<Dict> lists = dictService.findDictByCode(dict.getDict_type_code());
		String jsonStr = FastJsonUtil.toJSONString(lists);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonStr);
		return NONE;
	}
}
