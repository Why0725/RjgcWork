package com.education.dao;

import java.util.List;

import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Userposition;
import com.education.entity.Users;

public interface CommonDAO {
	//通过用户名查找用户，登录判断，注册用户名是否存在
	public Users findUsersByName(String name);
	
	//通过邮箱查看是否已经有了
	
	public Users findUsersByEmail(String email);
	// 注册 用户
	public void insertUsers(Users users);
	// 修改 用户
	public void updateUsers(Users users);
	// 获取所有的简历信息
	public List<Resume> findAllResumes();
	public Resume findResumeByid(int id);
	public List<Resume> getResumeByuserid(int userid);
	// 获取所有的职位信息
	public List<Position> findAllPositions();
	public Position findPositionByid(int id);
	public List<Userposition> getNumPositioned(int id);
}
