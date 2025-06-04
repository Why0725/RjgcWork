package com.education.service.log;

import java.util.List;

import com.education.entity.LoginLog;

public interface LoginLogService {
	public void addLoginLog(long userid,String name,String ip );
	public List<LoginLog> findLogs();
}
