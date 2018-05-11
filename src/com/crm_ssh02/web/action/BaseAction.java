package com.crm_ssh02.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	private static final long serialVersionUID = 7542330864309020814L;
	
	private Integer pageSize=5;
	
	private Integer pageCode=1;
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize = 5;
		}
		this.pageSize = pageSize;
	}
	
	public Integer getPageCode() {
		return pageCode;
	}
	public void setPageCode(Integer pageCode) {
		if(pageCode == null){
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	
	//调用值栈的set方法
	public void setVs(String key,Object obj){
		ActionContext.getContext().getValueStack().set(key, obj);
	}
	
	//调用值栈的push方法
	public void pushVs(Object obj){
		ActionContext.getContext().getValueStack().push(obj);
	}
}
