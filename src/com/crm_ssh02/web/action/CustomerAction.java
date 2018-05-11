package com.crm_ssh02.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm_ssh02.domain.Customer;
import com.crm_ssh02.domain.Dict;
import com.crm_ssh02.service.CustomerService;
import com.crm_ssh02.utils.FastJsonUtil;
import com.crm_ssh02.utils.PageBean;
import com.crm_ssh02.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 客户的Web层，Action模块
 */
public class CustomerAction extends BaseAction implements ModelDriven<Customer>{
	
	
	private static final long serialVersionUID = -5038598385000156872L;
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private Customer customer = new Customer();
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	/**
	 * 跳转到初始化添加页面
	 * @return
	 */
	public String initAddUI(){
		return "initAddUI";
	}
	
	/**
	 * 保存客户(有附件添加功能)
	 * @return
	 */
	private File upload;//表示要上传文件
	private String uploadFileName;//文件名称
	private String uploadContentType;//上传文件的类型
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String add() throws Exception{
		//表示有附件要上传
		if(uploadFileName!=null){
			//处理文件名称
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			//设置要传的路径
			String filePath = "D:\\program\\eclipse_kepler_project\\tomcat\\apache-tomcat-7.0.53\\webapps\\upload\\";
			File file = new File(filePath + uuidName);
			FileUtils.copyFile(upload, file);
			customer.setCust_file_path(filePath + uuidName);
		}
		customerService.add(customer);
		return "add";
	}
	/**
	 * 客户分页查询
	 * @return
	 */
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
	
		//添加通过用户名查询的条件
		String cust_name = customer.getCust_name();
		if(cust_name!=null && !cust_name.trim().isEmpty()){
			criteria.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		}
		
		//添加客户资源查询的条件
		Dict source = customer.getSource();
		if(source!=null && !source.getDict_id().trim().isEmpty()){
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		
		//添加客户级别的查询条件
		Dict level = customer.getLevel();
		if(level!=null && !level.getDict_id().trim().isEmpty()){
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}
		
		//添加客户行业的查询条件
		Dict industry = customer.getIndustry();
		if(industry!=null && !industry.getDict_id().trim().isEmpty()){
			criteria.add(Restrictions.eq("industry.dict_id", industry.getDict_id()));
		}
		
		PageBean<Customer> page = customerService.findByPage(this.getPageCode(), this.getPageSize(), criteria);
		
		//调用BaseAction的值栈的set方法
		this.setVs("page", page);

		return "page";
	}
	
	/**
	 * 根据id查询到客户信息跳转到修改客户页面
	 */
	public String initEdit(){
		customer = customerService.findCustomerById(customer.getCust_id());
		return "initEdit";
	}
	
	/**
	 * 编辑客户
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		//判断是否上传文件
		if(uploadFileName!=null){
			String cust_file_path = customer.getCust_file_path();
			File file = new File(cust_file_path);
			if(file.exists()){
				file.delete();
			}
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			String filePath = "D:\\program\\eclipse_kepler_project\\tomcat\\apache-tomcat-7.0.53\\webapps\\upload\\";
			File f = new File(filePath + uuidName);
			FileUtils.copyFile(upload, f);
			customer.setCust_file_path(filePath + uuidName);
		}
		customerService.edit(customer);
		return "edit";
	}
	
	/**
	 * 删除客户
	 * @return
	 */
	public String delete(){
		Customer c2 = customerService.findCustomerById(customer.getCust_id());
		String filePath = c2.getCust_file_path();
		customerService.delete(c2);
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
		return "delete";
	}
	
	/**
	 * 查询所有客户
	 * @return
	 */
	public String findAll(){
		List<Customer> list = customerService.findAll();
		String jsonStr = FastJsonUtil.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonStr);
		return NONE;
	}
}
