package com.education.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.education.entity.Applicant;
import com.education.entity.Position;
import com.education.entity.Recruiter;
import com.education.entity.Resume;
import com.education.entity.Users;
import com.education.service.admin.AdminManagePosition;
import com.education.service.admin.AdminManageResume;
import com.education.service.admin.AdminManageUser;


@Controller
@RequestMapping(value="/admin")
public class adminManageUserController {
	@Autowired(required = false)
	AdminManageUser adminManageUser;
	@Autowired(required = false)
	AdminManageResume adminManageResume;
	@Autowired(required = false)
	AdminManagePosition adminManagePosition;
	
	//查找所有的学生应聘者，并查看是否有简历
	@RequestMapping(value="/getApplicant",method=RequestMethod.POST)
	@ResponseBody
	public String getStudents(){
		List<Users> allStudents = adminManageUser.getAllStudents();
		List<Applicant> applicants = new ArrayList<Applicant>();
		Applicant applicant;
		for (Users users : allStudents) {
			applicant = new Applicant();
			applicant.setAid(users.getId());
			applicant.setAname(users.getName());
			applicant.setEmail(users.getEmail());
			applicant.setIdcard(users.getIdcard());
			applicant.setRealname(users.getRealname());
			List<Resume> resumes = adminManageResume.getResumeByuserId(users.getId());
			if(resumes.isEmpty()){
				System.out.println("简历列表为空的");
				applicant.setStatue("0");
				applicant.setResumeid(0);
			}
			else{
				applicant.setStatue("1");
				applicant.setResumeid(resumes.get(0).getId());
			}
			applicants.add(applicant);
		}
		
		JSONArray json = new JSONArray();
		for (Applicant a : applicants) {
			JSONObject jo = new JSONObject();
			jo.put("aid", a.getAid());
			jo.put("aname", a.getAname());
			jo.put("email", a.getEmail());
			jo.put("idcard", a.getIdcard());
			jo.put("realname", a.getRealname());
			jo.put("resumeid", a.getResumeid());
			jo.put("status", a.getStatue());
			json.put(jo);
		}
		System.out.println("========");
		System.out.println(json.toString());
		return json.toString();
	} 
	//获取 所有的家长信息，并显示该家长有多少个招聘信息
	@RequestMapping(value="/getParents",method=RequestMethod.POST)
	@ResponseBody
	public String getParents(){
		List<Users> allParents = adminManageUser.getAllParents();
		List<Recruiter> recruiters = new ArrayList<Recruiter>();
		Recruiter r ;
		for (Users parent : allParents) {
			r = new Recruiter();
			r.setRid(parent.getId());
			r.setRname(parent.getName());
			r.setEmail(parent.getEmail());
			List<Position> positionsByuserId = adminManagePosition.getPositionsByuserId(parent.getId());
			if(positionsByuserId == null) {
				r.setNum(0);
			} else {
				r.setNum(positionsByuserId.size());
			}
			
			recruiters.add(r);
		}
		JSONArray json = new JSONArray();
		for (Recruiter re : recruiters) {
			JSONObject jo = new JSONObject();
			jo.put("rid", re.getRid());
			jo.put("rname", re.getRname());
			jo.put("email", re.getEmail());
			jo.put("number", re.getNum());
			json.put(jo);
		}
		System.out.println(json.toString());
		return json.toString();
	}
	@RequestMapping(value="/getUserByid",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserById(HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		String userid = (String)session.getAttribute("loginid");
		int id = Integer.parseInt(userid);
		
		Users users = adminManageUser.getUsersByid(id);
		map.put("name", users.getName());
		map.put("password", users.getPassword());
		map.put("email", users.getEmail());
		
		return map;
	}
	@RequestMapping(value="/checkEmail",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkEmail(@RequestParam("email")String email){
		Map<String, Object> map = new HashMap<String, Object>();
		Users users = adminManageUser.getUsersByEmail(email);
		if(users == null){
			map.put("result", "success");
		}else{
			map.put("result", "fail");
		}
		return map;
	}
	
	@RequestMapping(value="/updateUserIm",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserIm(@RequestParam("password")String password,@RequestParam("email")String email,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		String userid = (String)session.getAttribute("loginid");
		int id = Integer.parseInt(userid);
		
		Users users = adminManageUser.getUsersByid(id);
		if(users != null){
			users.setPassword(password);
			users.setEmail(email);
			adminManageUser.updateUser(users);
			
			map.put("result", "success");
		}else{
			map.put("result", "fail");
		}
		return map;
	}
	@RequestMapping(value="/removeStudent",method=RequestMethod.GET)
	public String removeUser(@RequestParam("userid")int userid){
		System.out.println("删除的id"+userid);
		adminManageUser.deleteUser(userid);	
		return "system/admin/app_table.html";
	}
	
	@RequestMapping(value="/removeParent",method=RequestMethod.GET)
	public String removeParent(@RequestParam("userid")int userid){	
		System.out.println("====删除家长招聘者");
		adminManageUser.deleteUser(userid);
		return "system/admin/rec_table.html";
	}
}
