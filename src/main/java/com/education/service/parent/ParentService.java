package com.education.service.parent;

import java.util.List;

import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Userposition;

public interface ParentService {
		// 增加招聘信息
		public void addPosition(Position position);
		// 查看该用户的所有招聘信息
		public List<Position> getPositionByuserid(int userid);
		// 获取投了简历的简历信息
		public List<Resume> getResumesByPositionid(int positionid);
		//获取 简历信息 
		public Position getPositionBypositionid(int positionid);
		//查看 投的简历信息
		public Resume getResumeByResumeid(int resumeid);
		// 修改招聘信息
		public void updatePosition(Position position);
		
		//删除招聘信息 
		public void removePosition(int id);
		public void RemoveUserpositionByPositionID(int id);
}
