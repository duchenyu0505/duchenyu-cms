package com.duchenyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duchenyu.dao.SlideDao;
import com.duchenyu.pojo.Slide;
import com.duchenyu.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService{

	@Autowired
	private SlideDao slideDao;
	
	@Override
	public List<Slide> getAll() {
		// TODO Auto-generated method stub
		return slideDao.select(null);
	}

}
