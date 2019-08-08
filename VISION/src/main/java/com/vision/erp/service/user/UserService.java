package com.vision.erp.service.user;

import java.util.List;
import java.util.Map;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Branch;
import com.vision.erp.service.domain.HumanResourceCard;
import com.vision.erp.service.domain.User;

public interface UserService {

	public void addUser(User user) throws Exception;

	public List<User> getUserList(Search search) throws Exception;
	
	public User getUser(User user) throws Exception;

	public Map<String, Object> getInfo(String find) throws Exception;

	public void modifyPassword(User user) throws Exception; 

	public User getProofMySelfForId1(HumanResourceCard hrcInfo) throws Exception;

	public User getProofMySelfForPassword1(Map<String, String> map) throws Exception;

	public User getProofMySelfForId2(Branch branch) throws Exception;

	public User getProofMySelfForPassword2(Map<String, String> map) throws Exception;

}
