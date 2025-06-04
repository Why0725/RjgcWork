package com.education.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Userposition;

public interface StudentDAO {
	//插入简历
	public void insertResume(Resume resume);
	//修改简历
	public void updateResume(Resume resume);
	//通过用户id 获取 简历信息
	public Resume findResumeByuserid(int userid);
	//删除简历
	public void deleteResumeByresumeid(int resumeid);
	
	// 添加申请列表 ,学生申请职位 ，记录表
	public void insertUserPosition(Userposition userposition);
	//通过 学生的用户名 和 简历id 删除 学生申请职位的记录
	public void deleteUserPosition(@Param("userid")int userid,@Param("positionid")int positionid);
	// 学生用户 已经申请的职位信息 展示
	public List<Position> findPosition(int userid);
	
	// 通过 id 获取职位信息
	public Position findPositionByid(int positionid);
	//根据教员id 和职位的id 判断该教员是否已经申请了这个职位
	public List<Userposition> findUserpositionByuseridAndpositonid(@Param("userid")int userid,@Param("positionid")int positionid);
	
}
