package com.duchenyu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.duchenyu.common.CmsConst;
import com.duchenyu.common.JsonResult;
import com.duchenyu.pojo.Article;
import com.duchenyu.pojo.Category;
import com.duchenyu.pojo.Channel;
import com.duchenyu.pojo.User;
import com.duchenyu.service.ArticleService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/article/")
public class ArticleController {

		@Autowired
		private ArticleService articleService;
		
		/**
		 * @Title: add   
		 * @Description: 跳转到文章编辑页面
		 * @param: @return      
		 * @return: String      
		 * @throws
		 */
		@GetMapping("/add")
		public String toAdd(Integer id,Model model) {
			List<Channel> channelList = articleService.getChannelAll();
			model.addAttribute("channelList", channelList);
			if(id!=null) {
				Article article = articleService.getById(id);
				List<Category> cateList = articleService.getCateListByChannelId(article.getChannel_id());
				model.addAttribute("article", article);
				model.addAttribute("cateList", cateList);
			}
			return "article/add";
		}
		
		
		/**
		 * @Title: save   
		 * @Description: 保存文章   
		 * @param: @param article
		 * @param: @return      
		 * @return: String      
		 * @throws
		 */
		@PostMapping("/save")
		public @ResponseBody JsonResult save(Article article,HttpSession session) {
			User userInfo=(User) session.getAttribute(CmsConst.UserSessionKey);
			
			article.setUser_id(userInfo.getId());
			
			articleService.save(article);
			
			return JsonResult.success();
			
		}
		
		/**
		 * @Title: getCateListByChannelId   
		 * @Description: 根据频道Id查询分类列表  
		 * @param: @param channelId
		 * @param: @return      
		 * @return: JsonResult      
		 * @throws
		 */
		@GetMapping("/getCateListByChannelId")
		public @ResponseBody JsonResult getCateListByChannelId(Integer channelId) {
			
			List<Category> cateList = articleService.getCateListByChannelId(channelId);
			
			return JsonResult.success(cateList);
		}
		
		/**
		 * @Title: articles   
		 * @Description: 文章管理（我的文章）   
		 * @param: @return      
		 * @return: String      
		 * @throws
		 */
		@RequestMapping("/articles")
		public String articles(Article article,Model m,HttpSession session,
				@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
				@RequestParam(value = "pageSize",defaultValue = "1")Integer pageSize) {
			
			User userInfo=(User) session.getAttribute(CmsConst.UserSessionKey);
			
			article.setUser_id(userInfo.getId());
			
			PageInfo<Article> pageInfo = articleService.getPageInfo(article, pageNum, pageSize);
			
			m.addAttribute("pageInfo",pageInfo);
			
			return "article/articles";
		}
		
		/**
		 * @Title: deleteByIds   
		 * @Description: 根据Ids批量删除文章   
		 * @param: @param ids
		 * @param: @return      
		 * @return: JsonResult      
		 * @throws
		 */
		@PostMapping("deleteByIds")
		public @ResponseBody JsonResult deleteByIds(String ids) {
			
			articleService.deleteByIds(ids);
			
			return JsonResult.success();
		}
}
