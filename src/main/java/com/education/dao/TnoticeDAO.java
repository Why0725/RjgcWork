package com.education.dao;
import java.util.List;

import com.education.entity.Tnotice;

public interface TnoticeDAO {
	public void addNotice(Tnotice tnotice);
	public List<Tnotice> geTnotices();
	public void delNotice(long id);
}
