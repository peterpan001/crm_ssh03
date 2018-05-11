package com.crm_ssh02.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.crm_ssh02.domain.User;
import com.crm_ssh02.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户的Action
 * @author Peter
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4227916369569362465L;
	private UserService userService;
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	/**
	 * 检查用户名是否为一样的
	 * @return
	 */
	public String checkCode(){
		User u1 = userService.checkCode(user.getUser_code());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
			if(u1!=null){
				writer.print("no");
			}else{
				writer.print("yes");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
	 * 用户注册
	 * @return
	 */
	public String regist(){
		userService.regist(user);
		return "login";
	}

	/**
	 * 用户登录
	 */
	public String login(){
		User existUser = userService.login(user);
		if(existUser==null){
			ServletActionContext.getRequest().setAttribute("errorMsg", "用户名或密码错误");
			return "login";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginOk";
		}
	}
	/**
	 * 用户退出
	 * @return
	 */
	public String loginOut(){
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");
		return "login";
	}
}
