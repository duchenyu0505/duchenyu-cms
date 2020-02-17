package com.duchenyu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.duchenyu.pojo.Article;

public interface ArticleDao extends BaseDao<Article>{
	@Select("select id from cms_article")	
	List<Integer> selectIdList();
	
	
}
