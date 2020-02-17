package com.duchenyu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duchenyu.dao.LinkDao;
import com.duchenyu.pojo.Link;
import com.duchenyu.service.LinkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class LinkServiceImpl implements LinkService{

	@Autowired
	private LinkDao linkDao;
	
	@Override
	public boolean save(Link link) {
		if(link.getId()==null) {
			link.setCreated(new Date());
			return linkDao.insert(link)>0;
		}
		/** 不修改创建时间 **/
		Link link2 = linkDao.selectById(link.getId());
		link.setCreated(link2.getCreated());
		return linkDao.update(link)>0;
	}

	@Override
	public Link getById(Integer id) {
		// TODO Auto-generated method stub
		return linkDao.selectById(id);
	}

	@Override
	public List<Link> getLinkListAll() {
		// TODO Auto-generated method stub
		return linkDao.select(null);
	}

	@Override
	public PageInfo<Link> getPageInfo(Link link, Integer pageNum) {
		PageHelper.startPage(pageNum, 6);
		List<Link> linkList=linkDao.select(link);
		
		return new PageInfo<>(linkList);
	}

}
