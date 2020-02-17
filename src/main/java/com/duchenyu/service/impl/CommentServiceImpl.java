package com.duchenyu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcy.DateUtil;
import com.dcy.RandomUtil;
import com.dcy.StringUtil;
import com.duchenyu.dao.CommentDao;
import com.duchenyu.dao.UserDao;
import com.duchenyu.pojo.Comment;
import com.duchenyu.pojo.User;
import com.duchenyu.service.ArticleService;
import com.duchenyu.service.CommentService;
import com.duchenyu.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public boolean add(Comment comment) {
		comment.setCreated(new Date());
		boolean reault=commentDao.insert(comment)>0;
		/** 修改文章的评论数 **/
		boolean result2 =articleService.updateCommentCnt(comment.getArticleId());
		return reault&&result2;
	}

	@Override
	public PageInfo<Comment> getPageInfo(Integer articleId, Integer pageNo, Integer pageSize) {
		Comment comment = new Comment();
		comment.setArticleId(articleId);
		PageHelper.startPage(pageNo, pageSize);
		List<Comment> commentList = commentDao.select(comment);
		commentList.forEach(c->{
				User user = userDao.selectById(c.getUserId());
				c.setNickname(user.getNickname());
				c.setHeadimg(user.getHeadimg());
		});
		return new PageInfo<>(commentList);
	}

	@Override
	public Comment getRandomComment() {
		Comment comment = new Comment();
		/** 文章Id **/
		Integer randomArticleId = articleService.getRandomArticleId();
		comment.setArticleId(randomArticleId);
		/** 用户Id **/
		Integer randomUserId = userService.getRandomUserId();
		comment.setUserId(randomUserId);
		/** 评论内容 **/
		int random = RandomUtil.random(10,200);
		String randomChineseString = StringUtil.randomChineseString(random);
		comment.setContent(randomChineseString);
		/** 评论时间 **/
		System.out.println(randomChineseString);
		Date date1 = DateUtil.parse("2020-01-01 00:00:00","yyyy-MM-dd HH:mm:ss");
		Date date2 = new Date();
		Date randomDate = DateUtil.getRandomDate(date1, date2);
		comment.setCreated(randomDate);
		return comment;
	}

}
