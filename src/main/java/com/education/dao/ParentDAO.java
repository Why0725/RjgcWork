package com.education.dao;

import java.util.List;

import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Users;

public interface ParentDAO {
	// 增加招聘信息
	public void insertPosition(Position position);
	// 查看该用户的所有招聘信息
	public List<Position> findPositionByuserid(int userid);
	// 获取投了简历的简历信息
	public List<Resume> findResumesByPositionid(int positionid);
	//获取 简历信息 
	public Position findPositionBypositionid(int positionid);
	//查看 投的简历信息
	public Resume getResumeByResumeid(int resumeid);
	// 修改招聘信息
	public void updatePosition(Position position);
	
	//删除招聘信息 
	public void deletePosition(int id);
	//通过 职位id 删除该职位 对应的申请记录
	public void RemoveUserpositionByPositionID(int id);
}
