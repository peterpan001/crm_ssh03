--------------------------------CRM-SSH整合(Struts+Spring+Hibernate)------------------------------------------------
说明：这个配置是applicationContext.xml包含了hibernate的核心配置文件，也就是说没有了hibernate的核心配置文件。
1：CRM整合前奏
	1：创建数据库，crm
	2：创建表，base_dict,cst_customer,cst_linkman,sale_visit,sys_user;
	3：创建包结构com.crm_ssh02.domain,com.crm_ssh02.dao,com.crm_ssh02.service,com.crm_ssh02.web.action,
	  com.crm_ssh02.web.action.interceptor;
	4：在com.crm_ssh02.domain中编写持久化类并配置资源映射文件。其中持久化类中基本类型都需要用包装类型。在配置资源映射文件时，遵守一个规则，
	       一方放弃外键的维护，由多方去维护外键（客户-联系人）；一方放弃外键的配置，由多方去配置（客户-字典表）。
	5：导入UI界面
2：CRM整合前戏
	1：导入Jar包；
	2：创建config资源包，开始配置配置文件
		Web层：（Action层，Struts框架）
			struts.xml  config目录下创建struts的核心配置文件
			web.xml     配置struts的前端控制器，核心过滤器
		业务层：（Service层，Spring框架）
		持久层：（Dao层,Hibernate框架）	
			db.properties           配置数据库必须的4大参数文件
			applicationContext.xml  config目录下创建applicationContext.xml文件；因为我们这次的配置是把hibernate.cfg.xml
			                                                               的文件放入spring的配置文件中
			           				1：配置c3p0连接池；2：配置会话工厂（映射dataSource,配置数据库可选参数,加载资源映射文件）
									3：配置事务管理器平台；4：开启事务注解
			web.xml                 加载spring的核心配置文件，配置监听器，配置Spring的Session过滤器
------------------------------------用户的功能模块----------------------------------------------------------------
用户注册功能：在用户名，密码，用户姓名三个域实现失去焦点验证的Ajax方法，用户名需要去后台验证是否有重复，如果有重复禁止提交，其中用到
          response.contentType("text/html;charset=utf-8");response.getWriter()两个方法;前台用到了：
          $("#user_code").val();获取用户名,$("#codeId").addClass("error");$("#codeId").html("用户名不能为空");
                          添加颜色和给Span标签设置值;$(".error").size();获取.error的个数来判断是否可以提交;
          onsubmit="return checkForm()";表单提交验证方法；在注册时还对用户的密码进行了MD5的加密，注意在Service层自己设置用户的
                          状态。
          
用户登录功能：用户登录功能，其中在用户名和密码框两个域实现失去焦点验证的Ajax方法，如果为空，提示并禁止提交.否则提交，如果验证成功，转入登录页面，
                         并把用户放入session中供后面的拦截使用（对于没有登录的用户进行拦截，跳转到登录页面登录），如果用户名密码错误，放入request域中，
                         并在前台页面显示，提示用户用户名或密码错误。注意要把用户密码转为MD5加密。
------------------------------------客户的功能模块----------------------------------------------------------------                  
客户添加模块：首先对于客户的来源，客户级别，客户行业这三个要选择下拉列表框实现（Ajax异步请求实现），在查询到字典表对应的集合时，需要把集合转化为json格式的
                         字符串让前台解析，然后对于保存，还有一个特色就是实现附加文件的上传功能，这个需要在form表单中用到一个enctype="multipart/form-data";
                         声明name为upload,在Action中声明3个属性，File upload;String uploadFileName;String uploadContentType;先把文件
                         保存了，然后把文件的路径封装到客户属性中，然后把客户保存到数据库。注意在struts.xml中可以限制文件的大小，文件的类型

客户分页查询模块：首先先完成不带查询条件的分页查询，在Action中声明PageBean<Customer> page= customerService.findByPage(pageSize, pageCode, criteria);
			 故需要属性注入的声明pageCode,pageSize两个属性,,然后在持久层时,用带分页查询的查询方法,把pageBean中的4个属性(pageSize,pageCide
			totalPage, beanList)全部完成返回。在Action中把page压入值栈中。带条件的客户查询，首先把客户来源，客户行业，客户级3个下拉框完成，然后在后台接收时
			//添加客户资源查询的条件
			Dict source = customer.getSource();
			if(source!=null && !source.getDict_id().trim().isEmpty()){
				criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
			}
			注意：上面的pageCode和pageSize在注入时声明属性需要赋初值。
客户修改模块：客户修改同样需要完成3个下拉框的功能，然后一个注意点就是文件的修改，先要判断是否修改了文件，如果修改了，先把以前的文件删除，再上传修改过的文件，同样
		    把文件放到tomcat服务器下，把路径放入数据库。
	
客户删除模块：客户删除模块，需要根据客户id查询出需要删除的客户，把客户删除，把文件也删除。
-----------------------------------联系人的功能模块------------------------------------------------------------------
联系人添加模块：和上面类似

联系人分页查询模块：和上面类似

联系人修改模块：和上面类似

联系人删除模块：：和上面类似
----------------------------------------性能优化-----------------------------------------------------------------
1：拦截器设置，如果用户没有登录进行拦截，并跳转到登录页面	
<!-- 拦截器设置 -->
<interceptors>
	<interceptor name="userInteceptor" class="com.crm_ssh02.web.action.interceptor.UserInterceptor">
	</interceptor>
	<interceptor-stack name="strutsInterceptor">
		<interceptor-ref name="defaultStack"/>
	</interceptor-stack>
</interceptors>    	
用户中添加的参数：
<param name="excludeMethods">login,regist,checkCode</param>		 
2：将持久层dao层的一些常用的方法抽出来，写成模板类，其他持久层类继承这个类，达到方法的复用。
3：在web的表现层把Action的一些方法抽取出来，写成模板类，供其他类继承使用。
                        
                       
					
	