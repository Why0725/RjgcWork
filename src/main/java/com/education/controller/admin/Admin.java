package com.education.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.education.entity.Users;
import com.education.service.admin.AdminManageUser;
import com.education.service.log.LoginLogService;
import com.education.util.IPUtil;

@Controller
@RequestMapping(value="admin")
public class Admin {
	@Autowired(required = false)
	private AdminManageUser adminManageUser;
	@Autowired(required = false)
	private LoginLogService logService;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String index(){
		return "system/admin/login.html";
	}
	@RequestMapping(value="/loginin",method = RequestMethod.GET)
	public String loginin(HttpSession session){
		
		return "system/admin/index.html";
	}
	
	
	@RequestMapping(value="/showPositionsByUser",method = RequestMethod.GET)
	public String showPositionsByUser(@RequestParam("userid") String userid,HttpSession session){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("++++++0"+userid);
		session.setAttribute("useridforposition", userid);
		session.setMaxInactiveInterval(3600*24);
		return "system/admin/recruit_fromuser.html";
	}
	
	@RequestMapping(value = "/modify",method=RequestMethod.GET)
	public String modifyPas(){
		return "system/admin/modify_psd.html";
	}
	
	@RequestMapping(value = "/loginout",method=RequestMethod.GET)
	public String loginln(HttpSession session){
		session.removeAttribute("loginid");
		return "system/admin/login.html";
	}
	
	@RequestMapping(value="/checklogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checklogin(@RequestParam("name") String name,@RequestParam("password")String password,HttpSession session,HttpServletRequest request) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(name+"---"+password);
		Users users = adminManageUser.getUsersByName(name);
		
		if(null !=users && password.equals(users.getPassword()) && users.getStatue()==1){
			System.out.println(users.toString());
			logService.addLoginLog(users.getId(), users.getName(), IPUtil.getIpAddr(request));
			session.setAttribute("loginid", String.valueOf(users.getId()));
			session.setMaxInactiveInterval(3600*24);
			map.put("status", "success");
		}else{
			
			map.put("status", "fail");
		}
		return map;
	}
	
}
