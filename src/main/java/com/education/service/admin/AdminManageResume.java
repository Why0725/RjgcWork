package com.education.service.admin;

import java.util.List;

import com.education.entity.Resume;

public interface AdminManageResume {
	public void deleteResume(int resumeid);
	public List<Resume> getResumeByuserId(int userid);
	// 获取所有未审核的简历列表
	public List<Resume> getUnauditedResumes();
	//通过 简历id 获取简历信息
	public Resume getResumeByResumeId(int resumeid);
	//修改简历
	public void updateResume(Resume resume);
}
