package com.education.service.student;

import java.util.List;

import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Userposition;

public interface StudentService {
	//插入简历
		public void addResume(Resume resume);
		//修改简历
		public void updateResume(Resume resume);
		//通过用户id 获取 简历信息
		public Resume getResumeByuserid(int userid);
		//删除简历
		public void romoveResumeByresumeid(int resumeid);
		
		// 添加申请列表 ,学生申请职位 ，记录表
		public void addUserPosition(Userposition userposition);
		//通过 学生的用户名 和 简历id 删除 学生申请职位的记录
		public void romoveUserPosition(int userid,int positionid);
		// 学生用户 已经申请的职位信息 展示
		public List<Position> getPositions(int userid);
		
		// 通过 id 获取职位信息
		public Position getPositionByid(int positionid);
		public int findUserpositionByuseridAndpositonid(int userid,int positionid);
}
