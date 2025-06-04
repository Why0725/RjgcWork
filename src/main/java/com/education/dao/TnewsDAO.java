package com.education.dao;

import java.util.List;

import com.education.entity.Tnews;

public interface TnewsDAO {
	// 关于 tnews 操作
	// 添加
	public void insertNews(Tnews tnews);
	//删除
	public void deleteNews(long id);
	
	//获取列表
	public List<Tnews> geTnews();
	
	public void updateClicknum(long id);
	
	public Tnews getTnewByID(long id);
}
