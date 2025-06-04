package com.education.service.student.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.dao.StudentDAO;
import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Userposition;
import com.education.service.student.StudentService;
@Service("StudentService")
public class StudentServiceImpl implements StudentService {
	@Autowired(required = false)
	private StudentDAO studentDAO;
	// 添加 简历
	public void addResume(Resume resume) {
		studentDAO.insertResume(resume);
	}
	//修改简历
	public void updateResume(Resume resume) {
		
		studentDAO.updateResume(resume);
	}
	
	// 通过 用户id 获取 简历信息
	public Resume getResumeByuserid(int userid) {
		// TODO Auto-generated method stub
		return studentDAO.findResumeByuserid(userid);
	}
	// 通过简历 id 删除简历
	public void romoveResumeByresumeid(int resumeid) {
		studentDAO.deleteResumeByresumeid(resumeid);
	}
	// 添加 申请记录
	public void addUserPosition(Userposition userposition) {
		studentDAO.insertUserPosition(userposition);
	}
	// 删除 申请记录
	public void romoveUserPosition(int userid, int positionid) {
		studentDAO.deleteUserPosition(userid, positionid);
	}
	// 获取所有申请的职位信息
	public List<Position> getPositions(int userid) {
		return studentDAO.findPosition(userid);
	}

	// 通过  职位 id 获取职位详细信息
	public Position getPositionByid(int positionid) {
		return studentDAO.findPositionByid(positionid);
	}
	public int findUserpositionByuseridAndpositonid(int userid, int positionid) {
		List<Userposition> list = studentDAO.findUserpositionByuseridAndpositonid(userid, positionid);
		//如果不存在那么返回1 如果存在那么返回2
		if(list.isEmpty()){
			return 1;
		}else{
			return 2;	
		}
		
	}

}
