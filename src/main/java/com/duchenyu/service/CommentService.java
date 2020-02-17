package com.duchenyu.service;

import com.duchenyu.pojo.Comment;
import com.github.pagehelper.PageInfo;

public interface CommentService {

	/**
	 * @Title: add   
	 * @Description: 添加评论   
	 * @param: @param comment
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	boolean add(Comment comment);
	/**
	 * @Title: getPageInfo   
	 * @Description: 查询评论列表   
	 * @param: @param articleId
	 * @param: @param pageNo
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: PageInfo<Comment>      
	 * @throws
	 */
	PageInfo<Comment> getPageInfo(Integer articleId,Integer pageNo,Integer pageSize);
	/**
	 * @Title: getRandomComment   
	 * @Description: 获取随机的评论对象   
	 * @param: @return      
	 * @return: Comment      
	 * @throws
	 */
	Comment getRandomComment();
}
