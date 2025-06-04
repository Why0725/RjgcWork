package com.education.service.news;

import java.util.List;

import com.education.entity.Tnews;

public interface TnewsService {
	public void addNews(String title,String content);
	public void dropNews(long id);
	public List<Tnews> findNews();
	public Tnews getNewInfoByID(long id);
	public void updateClickNum(long id);
}	
