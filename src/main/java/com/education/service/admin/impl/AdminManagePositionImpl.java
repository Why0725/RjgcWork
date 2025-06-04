package com.education.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.dao.AdminDAO;
import com.education.entity.Position;
import com.education.service.admin.AdminManagePosition;
@Service("AdminManagePosition")
public class AdminManagePositionImpl implements AdminManagePosition {
	@Autowired(required = false)
	private AdminDAO adminDAO;
	//通过 身份为家长的用户 的id 获取该用户的招聘信息
	public List<Position> getPositionsByuserId(int userid) {
		return adminDAO.findPositionsByUserid(userid);
	}
	//通过招聘信息的id 删除招聘信息
	public void deletePositionByid(int positionid) {
		System.out.println("要删除的id"+positionid);
		adminDAO.removePosition(positionid);

	}
	//通过 招聘信息的id 获取详细的招聘信息
	public Position getPositionByid(int positionid) {
		
		return adminDAO.findPositionByid(positionid);
	}
	//获取 未审核的招聘信息列表
	public List<Position> getUnauditedPosition() {
		
		return adminDAO.findUnauditedPosition();
	}
	
	//修改招聘
	public void updatePosition(Position position){
		adminDAO.updatePosition(position);
	}
	public List<Position> getAuditedPositions() {
		// TODO Auto-generated method stub
		return adminDAO.findAuditedPosition();
	}
}
