package com.duchenyu.controller.admin;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcy.StringUtil;
import com.duchenyu.common.CmsConst;
import com.duchenyu.common.CmsMd5Util;
import com.duchenyu.common.JsonResult;
import com.duchenyu.pojo.Article;
import com.duchenyu.pojo.User;
import com.duchenyu.service.ArticleService;
import com.duchenyu.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	/**
	 * @Title: tologin   
	 * @Description: 登录页面   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/")
	public String tologin() {
		return "admin/login";
	}
	
	/**
	 * @Title: login   
	 * @Description: 管理员登录   
	 * @param: @param user
	 * @param: @param session
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@PostMapping("/login")
	public @ResponseBody JsonResult login(User user,HttpSession session) {
		/** 用户名和密码不能为空 **/
		if(StringUtil.isBlank(user.getUsername())|| StringUtil.isBlank(user.getPassword())) {
			return JsonResult.fail(10000,"用户名或明码不鞥为空");
		}
		
		/** 判断用户是否存在 **/
		User userInfo = userService.getByUsername(user.getUsername());
		if(userInfo==null) {
			return JsonResult.fail(10000, "用户名或者密码不正确");
		}
		/** 判断用户是否被禁用 **/
		if(userService.locked(user.getUsername())) {
			return JsonResult.fail(10000,"用户以被禁用，请联系管理员");
		}
		
		/** 是否为管理员 **/
		if(!"1".equals(userInfo.getRole())) {
			return JsonResult.fail(10000,"只能管理员登录");
		}
		
		/** 判断密码是否正确 **/
		String password = user.getPassword();
		String md5Password = CmsMd5Util.md5(password);
		
		 if(!md5Password.equals(userInfo.getPassword())) { 
			 return JsonResult.fail(10000, "用户名或密码不正确"); 
		}
		 
		
		/** 设置session **/
		session.setAttribute(CmsConst.UserAdminSessionKey, userInfo);
		return JsonResult.success();
	}
	
	@GetMapping("/signOut")
	public String singOut(HttpServletResponse response,HttpSession session) {
		session.setAttribute(CmsConst.UserAdminSessionKey,null);
		return "redirect:/admin/";
	}
	
	@RequestMapping("/center")
	public String userCenter() {
		return "admin/center";
	}
	
	@RequestMapping("/user")
	public String user(User user,Model model,
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize) {
		
		PageInfo<User> pageInfo=userService.getPageInfo(user, pageNum, pageSize);
		model.addAttribute("pageInfo",pageInfo);
		return "admin/user";
	}
	
	@RequestMapping("/updateLocked")
	public @ResponseBody JsonResult updateLocked(User user) {
		userService.updateLocked(user.getId());
		return JsonResult.success();
	}
	
	@RequestMapping("/articles")
	public String articles(Article article,Model model,HttpSession session,
			@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize) {
		
		article.setStatusStr("0,1,-1,3");
		PageInfo<Article> pageInfo=articleService.getPageInfo(article, pageNum, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/articles";
	}
	
	@RequestMapping("/check")
	public @ResponseBody JsonResult check(Article article) {
		articleService.check(article);
		return JsonResult.success();
	}
		
}
