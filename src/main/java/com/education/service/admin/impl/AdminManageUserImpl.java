package com.education.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.dao.AdminDAO;
import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Users;
import com.education.service.admin.AdminManageUser;
@Service("AdminManageUser")
public class AdminManageUserImpl implements AdminManageUser {
	@Autowired(required = false)
	private AdminDAO adminDAO; 
	
	//获取所有的学生信息
	public List<Users> getAllStudents() {
		System.out.println(adminDAO);
		List<Users> findStudents = adminDAO.findStudents();
		
		return findStudents;
	}
	//获取所有的家长信息
	public List<Users> getAllParents() {
		List<Users> findParents = adminDAO.findParents();
		return findParents;
	}
	//根据用户id 获取用户信息
	public Users getUsersByid(int userid) {
		Users findUserById = adminDAO.findUserById(userid);
		return findUserById;
	}
	//删除用户 在删除用户的同时删除用户对应的简历或者招聘信息
	public void deleteUser(int userid) {
		//获取要删除的用户信息
		Users users = adminDAO.findUserById(userid);
		//如果删除的用户是学生，那么删除他对应的简历
		if(users.getStatue() == 2){
			//查询该学生所有的简历
			List<Resume> findResume = adminDAO.findResume(userid);
			if(findResume != null){
				for (Resume resume : findResume) {
					System.out.println(resume.getId());
					adminDAO.romoveResume(resume.getId());
				}
			}
			
		}else if(users.getStatue() == 3){
			//获取该用户的所有简历
			List<Position> positionsByUserid = adminDAO.findPositionsByUserid(userid);
			//如果不为空
			if(positionsByUserid != null){
				for (Position position : positionsByUserid) {
					adminDAO.removePosition(position.getId());
				}
			}
			
		}
		adminDAO.removeUser(userid);
	}
	//修改用户
	public void updateUser(Users users) {
		adminDAO.updateUser(users);
	}
	//通过用户名获取用户
	public Users getUsersByName(String name) {
		
		return adminDAO.getUsersByName(name);
	}
	public Users getUsersByEmail(String email) {
		// TODO Auto-generated method stub
		return adminDAO.getUsersByEmail(email);
	}
}
