package com.education.service.comment.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.dao.TcommentDAO;
import com.education.entity.Tcomment;
import com.education.service.comment.CommentService;
@Service("CommentService")
public class CommentServieImpl implements CommentService {
	@Autowired(required = false)
	private TcommentDAO tcommentDAO;
	public void addTcomment(long positionid, long resumeid, String name, String content) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Tcomment tcomment = new Tcomment();
		tcomment.setContent(content);
		tcomment.setName(name);
		tcomment.setPositionid(positionid);
		tcomment.setResumeid(resumeid);
		tcomment.setCreatetime(simpleDateFormat.format(new Date()));
		tcommentDAO.insertTcomment(tcomment);
	}

	public void deleteTcomment(long id) {
		tcommentDAO.deleteTcomment(id);
	}

	public List<Tcomment> findTcommentsByResumtID(long resumeid) {
		// TODO Auto-generated method stub
		return tcommentDAO.geTcommentsForResume(resumeid);
	}

	public List<Tcomment> findTcommentByPositionID(long positionid) {
		// TODO Auto-generated method stub
		return tcommentDAO.geTcommentsForPosition(positionid);
	}

}
