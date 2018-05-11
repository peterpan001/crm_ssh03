package com.crm_ssh02.web.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm_ssh02.domain.User;
import com.crm_ssh02.domain.Visit;
import com.crm_ssh02.service.VisitService;
import com.crm_ssh02.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 客户拜访的Action
 * @author Peter
 */
@Controller(value="visitAction")
@Scope(value="prototype")
public class VisitAction extends BaseAction implements ModelDriven<Visit>{

	private static final long serialVersionUID = 8174004412615043549L;
	@Resource(name="visitService")
	private VisitService visitService;
	private Visit visit = new Visit();
	public Visit getModel() {
		return visit;
	}
	
	/**
	 * 跳转到初始话的添加页面
	 * @return
	 */
	public String initAddUI(){
		return "initAddUI";
	}
	
	/**
	 * 保存拜访客户记录
	 * @return
	 */
	public String save(){
		//获取当前用户
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		visit.setUser(user);
		visitService.save(visit);
		return "save";
	}
	
	/**
	 * 按条件分页查询
	 */
	//按时间查询需要注入的属性
	private String beginDate;
	private String endDate;
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		
		String visit_interviewee = visit.getVisit_interviewee();
		if(visit_interviewee!=null && !visit_interviewee.trim().isEmpty()){
			criteria.add(Restrictions.like("visit_interviewee", "%"+visit_interviewee+"%"));
		}
		if(beginDate!=null && !beginDate.trim().isEmpty()){
			criteria.add(Restrictions.ge("visit_time", beginDate));
		}
		if(endDate!=null && !endDate.trim().isEmpty()){
			criteria.add(Restrictions.le("visit_time", endDate));
		}
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		criteria.add(Restrictions.eq("user.user_id", user.getUser_id()));
		PageBean<Visit> page = visitService.findByPage(this.getPageCode(), this.getPageSize(), criteria);
		
		//调用BaseAction的值栈的set方法
		this.setVs("page", page);
		
		ServletActionContext.getRequest().setAttribute("visit_interviewee", visit_interviewee);
		ServletActionContext.getRequest().setAttribute("beginDate", beginDate);
		ServletActionContext.getRequest().setAttribute("endDate", endDate);
		return "findByPage";
	}
}
