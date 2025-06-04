package com.education.dao;

import java.util.List;

import com.education.entity.Tcomment;

public interface TcommentDAO {
	public void insertTcomment(Tcomment tcomment);
	public void deleteTcomment(long id);
	
	//评价教师的
	public List<Tcomment> geTcommentsForResume(long resumeid);
	// 评价职位的
	public List<Tcomment> geTcommentsForPosition(long positionid);
}
