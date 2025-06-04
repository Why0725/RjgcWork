package com.education.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Users;

public interface AdminDAO {
	// 获取所有的学生
	public List<Users> findStudents();
	//获取所有的家长
	public List<Users> findParents();
	//通过 学生的id 获取学生的 简历信息
	public List<Resume> findResume(int userid);
	//通过 用户id 删除用户
	public void removeUser(int userid);
	//修改用户
	public void updateUser(Users user);
	// 修改简历信息
	public void updateResume(Resume resume);
	//通过简历id 获取该简历信息
	public Resume findResumeByid(@Param("id")int id);
	//通过 简历id 删除简历
	public void romoveResume(@Param("resumeid")int resumeid);
	//通过用户id 获取用户信息
	public Users findUserById(int userid);
	
	// 如果用户是家长，通过用户id获取该家长的所有招聘信息
	public List<Position> findPositionsByUserid(int userid);
	//删除招聘信息
	public void removePosition(@Param("id")int id);
	// 修改招聘信息
	public void updatePosition(Position position);
	
	//通过 招聘信息id 获取招聘信息的详细信息
	public Position findPositionByid(int id);
	
	// 获取未审核的招聘信息
	public List<Position> findUnauditedPosition();
	// 获取所有已经审核的招聘信息
	public List<Position> findAuditedPosition();
	// 获取所有未审核的学生简历信息
	public List<Resume> findUnauditedResumes();
	//通过用户name 获取用户
	public Users getUsersByName(String name);
	// 判断email 是否存在
	public Users getUsersByEmail(String email);
	
}
