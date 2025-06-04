package com.education.service.log.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.dao.LoginLogDAO;
import com.education.entity.LoginLog;
import com.education.service.log.LoginLogService;
@Service("LoginLogService")
public class LoginLogServiceImpl implements LoginLogService {
	@Autowired(required = false)
	private LoginLogDAO logDAO;
	public void addLoginLog(long userid,String name,String ip ) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LoginLog log = new LoginLog();
		log.setName(name);
		log.setCreatetime(simpleDateFormat.format( new Date()));
		log.setUserid(userid);
		log.setIp(ip);
		logDAO.addLoginLog(log);
	}

	public List<LoginLog> findLogs() {	
		return logDAO.getLoginLogs();
	}

}
