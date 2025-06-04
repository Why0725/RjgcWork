package com.education.dao;
import java.util.List;
import com.education.entity.LoginLog;

public interface LoginLogDAO {
	public void addLoginLog(LoginLog log);
	public List<LoginLog> getLoginLogs();
}
