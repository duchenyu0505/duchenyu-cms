package com.duchenyu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcy.Md5Util;
import com.dcy.RandomUtil;
import com.duchenyu.dao.UserDao;
import com.duchenyu.pojo.User;
import com.duchenyu.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean register(User user) {
		/** 设置密码 **/
		user.setPassword(Md5Util.string2MD5(user.getPassword()));
		user.setLocked(0);
		user.setScore(0);
		user.setRole("0");
		user.setCreate_time(new Date());
		user.setUpdate_time(new Date());
		return userDao.insert(user)>0;
	}

	@Override
	public User getByUsername(String userName) {
		// TODO Auto-generated method stub
		return userDao.selectByUsername(userName);
	}

	@Override
	public boolean locked(String userName) {
		User userInfo = userDao.selectByUsername(userName);
		return userInfo.getLocked()==1;
	}

	@Override
	public boolean set(User user) {
		User userInfo = userDao.selectById(user.getId());
		userInfo.setHeadimg(user.getHeadimg());
		userInfo.setNickname(userInfo.getNickname());
		return userDao.update(userInfo)>0;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.selectById(id);
	}

	@Override
	public PageInfo<User> getPageInfo(User user, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> userList = userDao.select(user);
		return new PageInfo<>(userList);
	}

	@Override
	public boolean updateLocked(Integer id) {
		User user = userDao.selectById(id);
		if(user.getLocked()==0) {
			user.setLocked(1);
		}else {
			user.setLocked(0);
		}
		return userDao.update(user)>0;
	}

	@Override
	public Integer getRandomUserId() {
		List<Integer> userIdList = userDao.selectIdList();
		int random = RandomUtil.random(0,userIdList.size()-1);
		return userIdList.get(random);
	}

}
