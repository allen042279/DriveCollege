package com.softcreate.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.softcreate.model.User;
import com.softcreate.service.IMenuService;
import com.softcreate.service.IUserService;

@Controller
@RequestMapping("/user")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	/*
	 * @Resource注解告诉spring，这个字段是需要自动注入的
	 */
	@Resource
	private IUserService userService;
	@Resource
	private IMenuService menuService;
	
//	@Resource
//	private AuthenticationManager myAuthenticationManager;
	
	@RequestMapping("login")
	public ModelAndView login(User user, HttpServletRequest request) {
		
		logger.debug("用户登录--> 用户名称:" + user.getUsername() + "  密码：" + user.getPassword());
		
//		if (!checkValidateCode(request)) {
//			return new ModelAndView("redirect:/login.jsp");  //new LoginInfo().failed().msg("验证码错误！");
//		}
//		String username = user.getUsername().trim();
//		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, user.getPassword());
		/*
		 * DetachedCriteria detachedCriteria =
		 * DetachedCriteria.forClass(CwSysUser.class,"cwSysUser");
		 * detachedCriteria.add(Restrictions.eq("userNo", username));
		 * if(cwSysUserService.countUser(detachedCriteria)==0){ return new
		 * LoginInfo().failed().msg("用户名: "+username+" 不存在."); }
		 */
//		try {
//			
//			Authentication authentication = myAuthenticationManager.authenticate(authRequest); // 调用loadUserByUsername
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			HttpSession session = request.getSession();
//			session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
//			return new ModelAndView("subscribeMenu", "User", user); //new LoginInfo().success().msg(authentication.getName());
//			
//		} catch (AuthenticationException ex) {
//			return new ModelAndView("redirect:/login.jsp"); //new LoginInfo().failed().msg("用户名或密码错误");
//		}
		
		User loginUser = userService.login(user);
		
		if (loginUser!=null) {

			request.getSession().setAttribute("user", user);
			
			return new ModelAndView("/admin/views/subscribeMenu", "User", user);
			
		} else{
			return new ModelAndView("redirect:/login.jsp");
		}
	}

	private boolean checkValidateCode(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String result_verifyCode = request.getSession().getAttribute("verifyResult").toString(); // 获取存于session的验证值
		// request.getSession().setAttribute("verifyResult", null);
		String user_verifyCode = request.getParameter("verifyCode");// 获取用户输入验证码
		
		if (null == user_verifyCode	|| !result_verifyCode.equalsIgnoreCase(user_verifyCode)) {
			return false;
		}
		
		return true;
	}

	@RequestMapping("logout")
	public ModelAndView loginOut(HttpSession httpSession) {
		
		User user = (User) httpSession.getAttribute("user");
		
//		logger.debug("用户退出--> 用户名称:" + user.getUsername() + "  密码：" + user.getPassword());
		
		httpSession.removeAttribute("user");
		httpSession.invalidate();             // session
		
		return new ModelAndView("redirect:/login.jsp"); // 使用redirect关键字，则重定向到指定的页面
//		return new ModelAndView("forward:login.jsp");  // 使用forward关键字，则转发到指定的页面
	}
	
//*****************************************************************************************************
	
	@RequestMapping("getPage")
	@ResponseBody            //直接返回json格式的数据
	public List<User> getPage(HttpServletRequest request) {
		
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		List<User> userList = userService.getPage(pageNo, pageSize);
		return userList;
	}
	
	@RequestMapping("get")
	@ResponseBody            //直接返回json格式的数据
	public User get(int id) {
		return userService.getUser(id);
	}
	
	@RequestMapping(value="add", produces = "text/html; charset=UTF-8")
	@ResponseBody            //直接返回String的数据
	public String add(User user, HttpServletRequest request) {
		
		String resultString;
//		User user = new User();
//		
//		user.setUsername(request.getParameter("name"));
//		user.setRole(request.getParameter("role"));
//		user.setRole(request.getParameter(""));
		
		if (userService.addUser(user)) {
			
			resultString = "<script>alert('增加用户成功！'); window.location.href='" + request.getContextPath() + "/admin/views/user.jsp' </script>";
			
		}else {
			resultString = "<script>alert('增加用户失败！'); history.go(-1); </script>";
		}
			
		return resultString;
		
	}
	
	@RequestMapping(value="delete/{id}", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		
		String resultString;
		
		User user = userService.getUser(id);
		if (userService.deleteUser(user)) {
			
			resultString = "<script>alert('删除用户成功！'); window.location.href='" + request.getContextPath() + "/admin/views/user.jsp' </script>";
			
		}else {
			
			resultString = "<script>alert('删除用户失败！'); window.location.href='" + request.getContextPath() + "/admin/views/user.jsp' </script>";
		}
		
		return resultString;
		
	}
	
	@RequestMapping(value="update", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String update(User user, HttpServletRequest request) {
		
		String resultString;
		
		if (userService.updateUser(user)) {
			
			resultString = "<script>alert('修改用户成功！'); window.location.href='" + request.getContextPath() + "/admin/views/user.jsp' </script>";
			
		}else {
			resultString = "<script>alert('修改用户失败！'); history.go(-1); </script>";
		}
			
		return resultString;
		
	}
	
}
