package com.softcreate.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softcreate.model.AccessToken;
import com.softcreate.model.SimpleMenu;
import com.softcreate.model.SimpleMenu.MenuType;
import com.softcreate.pojo.WeixinUtil;
import com.softcreate.service.IMenuService;
import com.softcreate.service.IUserService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	/*
	 * @Autowired注解告诉spring，这个字段是需要自动注入的
	 */
	@Resource private IMenuService menuService;
	@Resource private IUserService userService;

	@RequestMapping("getAll")
	@ResponseBody            //直接返回json格式的数据
	public List<SimpleMenu> getAll() {
		List<SimpleMenu> lsitList = menuService.getAll();
		return lsitList;
		
	}
	
	@RequestMapping("get")
	@ResponseBody            //直接返回json格式的数据
	public SimpleMenu get(int id) {
		return menuService.getMenu(id);
	}
	
	@RequestMapping("getParentMenu")
	@ResponseBody            //直接返回json格式的数据
	public List<SimpleMenu> getParentMenu() {
		return menuService.getParentMenu();
	}
	
	@RequestMapping(value="add", produces = "text/html; charset=UTF-8")
	@ResponseBody            //直接返回String的数据
	public String add(HttpServletRequest request) {
		
		String resultString;
		if (!userService.isLogin(request.getSession())) {
			resultString = "<script>alert('请先登录系统！'); window.location.href='" + request.getContextPath() + "/login.jsp' </script>";
			return resultString;
		}
		
		SimpleMenu simpleMenu = new SimpleMenu();
		
		simpleMenu.setMenuKey(request.getParameter("menuKey"));
		simpleMenu.setName(request.getParameter("name"));
		simpleMenu.setType(MenuType.valueOf(request.getParameter("type")));
		simpleMenu.setShowOrder(Integer.parseInt(request.getParameter("showOrder")));
		simpleMenu.setParentId(Integer.parseInt(request.getParameter("parentId")));
		
		if (menuService.addMenu(simpleMenu)) {
			
			resultString = "<script>window.location.href='" + request.getContextPath() + "/admin/views/menu.jsp' </script>";
			
		}else {
			resultString = "<script>alert('增加菜单失败！'); history.go(-1); </script>";
		}
			
		return resultString;
		
	}
	
	@RequestMapping(value="delete/{id}", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		
		String resultString;
		
		if (!userService.isLogin(request.getSession())) {
			resultString = "<script>alert('请先登录系统！'); window.location.href='" + request.getContextPath() + "/login.jsp' </script>";
			return resultString;
		}
		
		SimpleMenu simpleMenu = menuService.getMenu(id);
		if (menuService.deleteMenu(simpleMenu)) {
			
			resultString = "<script>window.location.href='" + request.getContextPath() + "/admin/views/menu.jsp' </script>";
			
		}else {
			
			resultString = "<script>alert('删除菜单失败！'); window.location.href='" + request.getContextPath() + "/admin/views/menu.jsp' </script>";
		}
		
		return resultString;
		
	}
	
	@RequestMapping(value="update", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String update(SimpleMenu simpleMenu, HttpServletRequest request) {
		
		String resultString;
		
		if (!userService.isLogin(request.getSession())) {
			resultString = "<script>alert('请先登录系统！'); window.location.href='" + request.getContextPath() + "/login.jsp' </script>";
			return resultString;
		}
		
		if (menuService.updateMenu(simpleMenu)) {
			
			resultString = "<script>window.location.href='" + request.getContextPath() + "/admin/views/menu.jsp' </script>";
			
		}else {
			resultString = "<script>alert('修改菜单失败！'); history.go(-1); </script>";
		}
			
		return resultString;
		
	}
	
	@RequestMapping(value="create", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String create(HttpServletRequest request) {
		
		String resultString;
		
		if (!userService.isLogin(request.getSession())) {
			resultString = "<script>alert('请先登录系统！'); window.location.href='" + request.getContextPath() + "/login.jsp' </script>";
			return resultString;
		}
		
		AccessToken accessToken = menuService.fetchSubAccessToken();
		
		if (null != accessToken) {
			// 调用接口创建菜单
			int result = WeixinUtil.createSubMenu(menuService.getMenu(), accessToken);

			// 判断菜单创建结果
			if (0 == result) {
				resultString = "<script>alert('菜单创建成功！');";
			} else {
				resultString = "<script>alert('菜单创建失败，错误码：" + result + "');";
			}
			
		}else{

			resultString = "<script>alert('菜单创建失败，无法获取有效的访问证书！');";
		}
		
		return resultString + "  window.location.href = '" + request.getContextPath()	+ "/admin/views/menu.jsp' </script> ";
	}
	
}
