package com.crm_ssh02.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm_ssh02.service.TotalService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller(value="totalAction")
@Scope(value="prototype")
public class TotalAction extends ActionSupport{

	private static final long serialVersionUID = -3464934542534609395L;
	@Resource(name="totalService")
	private TotalService totalService;
	
	
	public String findSource(){
		List<Object[]> list = totalService.findSource();
		ActionContext.getContext().getValueStack().set("list", list);
		return "findSource";
	}
	
	public String findIndustry(){
		List<Object[]> list = totalService.findIndustry();
		ActionContext.getContext().getValueStack().set("list", list);
		return "findIndustry";
	}
}
