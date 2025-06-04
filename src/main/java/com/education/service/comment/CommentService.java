package com.education.service.comment;

import java.util.List;

import com.education.entity.Tcomment;

/**
 * 对教师 和 职位评论的处理
 * @author asus
 *
 */
public interface CommentService {
	/**
	 * 增加评论（职位和 老师）
	 * @param positionid 职位ID
	 * @param resumeid 老师简历ID
	 * @param name 用户名
	 * @param content 评论内容
	 */
	public void addTcomment(long positionid,long resumeid,String name,String content);
	
	public void deleteTcomment(long id);
	
	/***
	 * 通过老师的简历ID 获取对老师的评论
	 * @param resumeid
	 * @return
	 */
	public List<Tcomment> findTcommentsByResumtID(long resumeid);
	
	/**
	 * 获取某个职位的评论信息
	 * @param positionid
	 * @return
	 */
	public List<Tcomment> findTcommentByPositionID(long positionid);
}
