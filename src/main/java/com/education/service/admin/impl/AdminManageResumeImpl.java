package com.education.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.dao.AdminDAO;
import com.education.entity.Resume;
import com.education.service.admin.AdminManageResume;
@Service("AdminManageResume")
public class AdminManageResumeImpl implements AdminManageResume {
	@Autowired(required = false)
	private AdminDAO adminDAO;
	// 通过 简历id 删除简历
	public void deleteResume(int resumeid) {
		
		adminDAO.romoveResume(resumeid);
	}
	// 通过用户id 获取 学生用户的简历 
	public List<Resume> getResumeByuserId(int userid) {
		return adminDAO.findResume(userid);
	}
	
	// 获取所有没有审核的简历列表
	public List<Resume> getUnauditedResumes() {
		
		return adminDAO.findUnauditedResumes();
	}
	public Resume getResumeByResumeId(int resumeid) {
		return adminDAO.findResumeByid(resumeid);
	}
	//修改简历
	public void updateResume(Resume resume) {
		adminDAO.updateResume(resume);
	}

}
