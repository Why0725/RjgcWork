package com.education.service.notice;

import java.util.List;

import com.education.entity.Tnotice;

public interface NoticeService {
	public void insertNotice(String title,String content);
	public List<Tnotice> findTnotices();
	public Tnotice findTnotice();
	public void deleteTnoticeByID(int id);
}
