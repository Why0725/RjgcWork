package com.education.service.parent.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.dao.ParentDAO;
import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.service.parent.ParentService;
@Service("ParentService")
public class ParentServiceImpl implements ParentService{
	@Autowired(required = false)
	private ParentDAO parentDAO;
	public void addPosition(Position position) {
		parentDAO.insertPosition(position);
		
	}
	// 获取该用户所有的招聘信息
	public List<Position> getPositionByuserid(int userid) {
		// TODO Auto-generated method stub
		return parentDAO.findPositionByuserid(userid);
	}
	//获取该 招聘信息的所有投的简历信息
	public List<Resume> getResumesByPositionid(int positionid) {
		// TODO Auto-generated method stub
		return parentDAO.findResumesByPositionid(positionid);
	}
	
	//通过 招聘信息id 获取招聘信息
	public Position getPositionBypositionid(int positionid) {
		// TODO Auto-generated method stub
		return parentDAO.findPositionBypositionid(positionid);
	}
	
	// 通过简历id 获取简历
	public Resume getResumeByResumeid(int resumeid) {
		// TODO Auto-generated method stub
		return parentDAO.getResumeByResumeid(resumeid);
	}
	//修改简历
	public void updatePosition(Position position) {
		parentDAO.updatePosition(position);
	}

	//删除简历
	public void removePosition(int id) {
		parentDAO.deletePosition(id);
	}
	public void RemoveUserpositionByPositionID(int id) {
		parentDAO.RemoveUserpositionByPositionID(id);
	}

}
