package com.vision.erp.service.user.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vision.erp.service.domain.User;
import com.vision.erp.service.user.UserDAO;
import com.vision.erp.service.user.UserService;
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("userDAOImpl")
	private UserDAO userDAO;
	
//	public void addUser1(User user) throws Exception{
//		userDAO.addUser(user);
//	}

	@Override
	public User selectUser(String userId) throws Exception {
		return userDAO.selectUser(userId);
		
	}

	@Override
	public void addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
