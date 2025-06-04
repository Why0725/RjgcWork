package com.education.service.common.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.dao.CommonDAO;
import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Userposition;
import com.education.entity.Users;
import com.education.service.common.CommonService;
@Service("CommonService")
public class CommonServiceImpl implements CommonService{
	
	@Autowired(required = false)
	private CommonDAO commonDAO;
	//修改用户
	public void updateUsers(Users users) {
		commonDAO.updateUsers(users);	
	}
	// 通过用户名获取用户
	public Users getUsersByName(String name) {
		
		return commonDAO.findUsersByName(name);
	}
	//通过email 获取用户
	public Users getUsersByEmail(String email) {
		// TODO Auto-generated method stub
		return commonDAO.findUsersByEmail(email);
	}
	//插入用户信息
	public void addUsers(Users users) {
		commonDAO.insertUsers(users);
		
	}
	//获取所有的简历信息
	public List<Resume> getAllResumes() {
		return commonDAO.findAllResumes();
	}
	//获取所有的职位信息
	public List<Position> getAllPositions() {
		return commonDAO.findAllPositions();
	}
	public Resume getResumeByid(int id) {
		// TODO Auto-generated method stub
		return commonDAO.findResumeByid(id);
	}
	public Position getPositionByid(int id) {
		// TODO Auto-generated method stub
		return commonDAO.findPositionByid(id);
	}
	public int getNumPositioned(int id) {
		List<Userposition> positioned = commonDAO.getNumPositioned(id);
		if(positioned.isEmpty()){
			return 0;
		}else{
			return positioned.size();
		}
	}
	public List<Resume> getResumeByuserid(int userid) {
		// TODO Auto-generated method stub
		return commonDAO.getResumeByuserid(userid);
	}

}
