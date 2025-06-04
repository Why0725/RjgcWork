package com.education.service.notice.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.dao.TnoticeDAO;
import com.education.entity.Tnotice;
import com.education.service.notice.NoticeService;
@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {
	@Autowired(required = false)
	private TnoticeDAO tnoticeDAO;
	
	public void insertNotice(String title, String content) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Tnotice tnotice = new Tnotice();
		tnotice.setContent(content);
		tnotice.setTitle(title);
		tnotice.setCreatetime(simpleDateFormat.format(new Date()));
		tnoticeDAO.addNotice(tnotice);
	}

	public Tnotice findTnotice() {
		
		List<Tnotice> tnotices = tnoticeDAO.geTnotices();
		if(tnotices.isEmpty()){
			return null;
		}else {
			return tnotices.get(0);
		}
	}

	public void deleteTnoticeByID(int id) {
		tnoticeDAO.delNotice(id);
	}

	public List<Tnotice> findTnotices() {
		List<Tnotice> tnotices = tnoticeDAO.geTnotices();
		return tnotices;
	}

}
