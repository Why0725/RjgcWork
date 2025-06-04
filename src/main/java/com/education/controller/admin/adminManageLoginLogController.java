package com.education.controller.admin;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.education.entity.LoginLog;
import com.education.service.log.LoginLogService;
/**
 * 操作 简历的Controller
 */
@Controller
@RequestMapping(value="/admin")
public class adminManageLoginLogController {
	@Autowired(required = false)
	private LoginLogService logService;
	
	@RequestMapping(value="/listLogs",method=RequestMethod.POST)
	@ResponseBody
	public String listLogs(){
		List<LoginLog> findLogs = logService.findLogs();
		JSONArray json = new JSONArray();
		for (LoginLog notice : findLogs) {
			JSONObject jo = new JSONObject();
			jo.put("userid", notice.getUserid());
			jo.put("name", notice.getName());
			jo.put("ip", notice.getIp());
			jo.put("createtime", notice.getCreatetime());
			json.put(jo);
		}
		System.out.println(json.toString());
		return json.toString();
	}
}
