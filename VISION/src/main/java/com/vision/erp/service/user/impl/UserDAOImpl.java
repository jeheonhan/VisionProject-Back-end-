package com.vision.erp.service.user.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.vision.erp.common.Search;
import com.vision.erp.service.domain.Branch;
import com.vision.erp.service.domain.HumanResourceCard;
import com.vision.erp.service.domain.User;
import com.vision.erp.service.user.UserDAO;
@Repository("userDAOImpl")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	@Qualifier("sqlSession")
	private SqlSession sqlSession;
	
	public void addUser(User user) throws Exception{
		sqlSession.insert("UserMapper.insertUser",user);
	}
	
	
	
	@Override
	public void updatePassword(User user) throws Exception {
		sqlSession.update("UserMapper.modifyPassword",user);	
	}

	@Override
	public User proofMySelfForId1(HumanResourceCard hrcInfo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("UserMapper.proofMySelfForId1",hrcInfo);
	}

	@Override
	public User proofMySelfForPassword1(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("UserMapper.proofMySelfForPassword1",map);
	}
	
	@Override
	public User proofMySelfForId2(Branch branch) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("UserMapper.proofMySelfForId2",branch);
	}

	@Override
	public User proofMySelfForPassword2(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("UserMapper.proofMySelfForPassword2",map);
	}
	
	

	@Override
	public List<User> selectUserList(Search search) throws Exception {
		return sqlSession.selectList("UserMapper.selectUserList");
		
	}

	
	@Override
	public User selectUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("UserMapper.selectUser", user);
	}
}
