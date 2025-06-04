package com.education.service.admin;

import java.util.List;

import com.education.entity.Position;

public interface AdminManagePosition {
	//通过 用户身份为家长的用户id 获取该用户的所有的招聘信息
	public List<Position> getPositionsByuserId(int userid);
	//通过简历信息id删除招聘信息
	public void deletePositionByid(int positionid);
	
	//通过 招聘信息的id 获取该招聘信息的详细信息
	public Position getPositionByid(int positionid);
	// 获取所有的未审核的招聘信息
	public List<Position> getUnauditedPosition();
	//获取所有已经审核的招聘信息 
	public List<Position> getAuditedPositions();
	public void updatePosition(Position position);
}
