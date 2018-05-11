package com.crm_ssh02.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm_ssh02.domain.Customer;
import com.crm_ssh02.domain.Linkman;
import com.crm_ssh02.service.LinkmanService;
import com.crm_ssh02.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkmanAction extends BaseAction implements ModelDriven<Linkman>{

	private static final long serialVersionUID = 378144427379921073L;
	private LinkmanService linkmanService;
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}
	private Linkman linkman = new Linkman();
	public Linkman getModel() {
		return linkman;
	}

	/**
	 * 跳转到初始化添加页面
	 */
	public String initAddUI(){

		return "initAddUI";
	}
	
	/**
	 * 添加联系人
	 * @return
	 */
	public String add(){
		linkmanService.add(linkman);
		return "add";
	}
	
	/**
	 * 带条件的分页查询
	 */
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		String lkm_name = linkman.getLkm_name();
		if(lkm_name!=null && !lkm_name.trim().isEmpty()){
			criteria.add(Restrictions.like("lkm_name", "%"+lkm_name+"%"));
		}
		Customer c1 = linkman.getCustomer();
		if(c1!=null && c1.getCust_id()!=null){
			criteria.add(Restrictions.eq("customer.cust_id", c1.getCust_id()));
		}
		PageBean<Linkman> page = linkmanService.findByPage(this.getPageCode(), this.getPageSize(), criteria);
		
		//调用BaseAction的值栈的set方法
		this.setVs("page", page);
		
		return "page";
	}
	
	/**
	 * 跳转到初始化修改页面
	 */
	public String initEdit(){
		linkman = linkmanService.findLinkmanById(linkman.getLkm_id());
		return "initEdit";
	}
	
	/**
	 * 修改联系人信息
	 */
	public String edit(){
		linkmanService.edit(linkman);
		return "edit";
	}
	
	/**
	 * 删除联系人信息
	 */
	public String delete(){
		linkmanService.delete(linkman);
		return "delete";
	}
	
}
