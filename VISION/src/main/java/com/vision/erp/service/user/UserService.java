package com.vision.erp.service.user;

import com.vision.erp.service.domain.User;

public interface UserService {
	
public void addUser(User user) throws Exception;
	
	public User selectUser(String userId) throws Exception;
	
}
