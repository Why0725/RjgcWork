package com.education.service.news.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.education.dao.TnewsDAO;
import com.education.entity.Tnews;
import com.education.service.news.TnewsService;
@Service("TnewsService")
public class TnewsServiceImpl implements TnewsService {
	@Autowired(required = false)
	private TnewsDAO tnewsDAO;
	public void addNews(String title, String content) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Tnews tnews = new Tnews();
		tnews.setTitle(title);
		tnews.setContent(content);
		tnews.setCreatetime(simpleDateFormat.format(new Date()));
		tnewsDAO.insertNews(tnews);
	}

	public void dropNews(long id) {
		tnewsDAO.deleteNews(id);
	}

	public List<Tnews> findNews() {
		return tnewsDAO.geTnews();
	}

	public void updateClickNum(long id) {
		tnewsDAO.updateClicknum(id);
	}

	public Tnews getNewInfoByID(long id) {
		Tnews tnews = tnewsDAO.getTnewByID(id);
		return tnews;
	}

}
