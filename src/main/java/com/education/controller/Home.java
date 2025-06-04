package com.education.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.education.entity.Tnews;
import com.education.entity.Users;
import com.education.service.common.CommonService;
import com.education.service.log.LoginLogService;
import com.education.service.news.TnewsService;
import com.education.util.IPUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
@RequestMapping("/home")
public class Home {
	@Autowired(required = false)
	private CommonService commonService;
	@Autowired(required = false)
	private  TnewsService tnewsService;
	@Autowired(required = false)
	private LoginLogService logService;
	@RequestMapping("/index")
	public String index(){
		
		return "system/applicant/index.html";
	}
	@RequestMapping("/register")
	public String register(){
		
		return "system/applicant/register.html";
	}
	
	@RequestMapping("/toCreateNote")
	public String toCreateNote(){
		
		return "system/applicant/create_note.html";
	}
	@RequestMapping("/addNotice")
	public String addNotice(){
		return "system/admin/notice_add.html";
	}
	@RequestMapping(value="/checkFlag",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkFlag(HttpSession session){
		 Map<String, Object> map = new HashMap<String, Object>();
		 if (session.getAttribute("flag")!=null) {
			map.put("flag", session.getAttribute("flag"));
		}else{
			session.setAttribute("flag", 0);
		}
		 //是否登录标记（登录教员为1 登录 学生家长为2）
		 
		 return map;
				 
	}
	@RequestMapping(value="/loginIn",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginIn(@RequestParam("username")String username,@RequestParam("password")String password,HttpSession session,HttpServletRequest request){
		 Map<String, Object> map = new HashMap<String, Object>();
		 Users users = commonService.getUsersByName(username);
		 if(users == null){
			 map.put("flag", 0);
		 }else{
			 if(password.equals(users.getPassword())&&users.getStatue()==2){
				 session.setAttribute("userid", users.getId());
				 session.setAttribute("username", users.getName());
				 session.setAttribute("flag", 1);
				 map.put("flag", 1);
			 }else if(password.equals(users.getPassword())&&users.getStatue()==3){
				 session.setAttribute("userid", users.getId());
				 session.setAttribute("username", users.getName());
				 session.setAttribute("flag", 2);
				 map.put("flag", 2);
			 }else{
				 map.put("flag", 0);
			 }
			 logService.addLoginLog(users.getId(), users.getName(), IPUtil.getIpAddr(request));
		 }
		 return map;
				 
	}
	
	@RequestMapping(value="/loginout",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginout(HttpSession session){
		 Map<String, Object> map = new HashMap<String, Object>();
		 session.removeAttribute("username");
		 session.removeAttribute("userid");
		 //是否登录标记（登录教员为1 登录 学生家长为2）
		 session.setAttribute("flag", 0);
		 map.put("flag", 0);
		 return map;
				 
	}
	
	@RequestMapping(value="/checkName",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkName(@RequestParam("username")String username){
		 Map<String, Object> map = new HashMap<String, Object>();
		 Users users = commonService.getUsersByName(username);
		 if(users == null){
			 //用户名可以使用
			 map.put("result", 1);
		 }else{
			 map.put("result", 0);
		 }
		 return map;
				 
	}
	@RequestMapping(value="/checkEmail",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkEmail(@RequestParam("email")String email){
		 Map<String, Object> map = new HashMap<String, Object>();
		 Users users = commonService.getUsersByEmail(email);
		 if(users == null){
			 //用户名可以使用
			 map.put("result", 1);
		 }else{
			 map.put("result", 0);
		 }
		 return map;
				 
	}
	
	@RequestMapping(value="/registerUser",method=RequestMethod.GET)
	public String registerUser(@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("email")String email,@RequestParam("status")int status,@RequestParam("idcard")String idcard,@RequestParam("realname")String realname,HttpSession session){
		Users users = new Users();
		users.setName(username);
		users.setPassword(password);
		users.setEmail(email);
		users.setFlag(1);
		users.setStatue(status);
		users.setIdcard(idcard);
		users.setRealname(realname);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		users.setCreatetime(dateFormat.format(new Date()));
		commonService.addUsers(users);
		session.setAttribute("registername", username);
		if(status ==2){
			return "redirect:toCreateNote";
		}
		else{
			return "redirect:index";
		}
	}
	@RequestMapping(value="/listTnews",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listNotices(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Tnews> news = tnewsService.findNews();
		map.put("news", news);
		System.out.println(map.toString());
		return map;
	}
	@RequestMapping(value="/getTnewsById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTnewsById(@RequestParam("id")int id){
		Map<String, Object> map = new HashMap<String, Object>();
		tnewsService.updateClickNum(id);
		Tnews tnews = tnewsService.getNewInfoByID(id);
		map.put("title", tnews.getTitle());
		map.put("content", tnews.getContent());
		map.put("clicknum", tnews.getClicknum());
		map.put("createtime", tnews.getCreatetime());
		System.out.println(map.toString());
		return map;
	}
	
	
	
}
