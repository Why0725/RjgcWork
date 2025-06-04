package com.education.controller.admin;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.education.entity.Resume;
import com.education.entity.Users;
import com.education.service.admin.AdminManageResume;
import com.education.service.admin.AdminManageUser;

@Controller
@RequestMapping(value="/admin")
public class adminManageResumeController {
	@Autowired(required = false)
	private AdminManageUser adminManageUser;
	@Autowired(required = false)
	private AdminManageResume adminManageResume;
	
	// 获取 未审核的学生简历信息列表
	@RequestMapping(value="/listUnauditedResumes",method=RequestMethod.POST)
	@ResponseBody
	public String listUnauditedResumes(){
		List<Resume> resumes = adminManageResume.getUnauditedResumes();
		JSONArray json = new JSONArray();
		for (Resume resume : resumes) {
			JSONObject jo = new JSONObject();
			jo.put("id", resume.getId());
			Users user = adminManageUser.getUsersByid(resume.getUser());
			jo.put("username", user.getName());
			jo.put("name", resume.getName());
			jo.put("sex", resume.getSex());
			jo.put("education", resume.getEducation());
			jo.put("phone", resume.getPhonenum());
			jo.put("major", resume.getMajor());
			jo.put("createtime", resume.getCreatetime());
			json.put(jo);
		}
		System.out.println(json.toString());
		return json.toString();
	}
	
	
	@RequestMapping(value="/deleteResumeByid",method=RequestMethod.GET)
	public String deleteResumeByid(@RequestParam("id")int id){
		System.out.println("要删除的简历信息id"+id);
		adminManageResume.deleteResume(id);
		
		return "system/admin/resume_table.html";
	}
	Map<String, Object> map;
	@RequestMapping(value="/showResumeByUserid",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showResumeByUserid(@RequestParam("id") int id){
		map = new HashMap<String,Object>();
		System.out.println(id);
		Resume resume = adminManageResume.getResumeByResumeId(id);
		//Resume resume = byuserId.get(0);
		map.put("id", resume.getId());
		map.put("name", resume.getName());
		map.put("sex", resume.getSex());
		map.put("birthday", resume.getBirthday());
		map.put("phonenum", resume.getPhonenum());
		map.put("email", resume.getEmail());
		map.put("schoolname", resume.getSchoolname());
		map.put("time", resume.getTime());
		map.put("education", resume.getEducation());
		map.put("major", resume.getMajor());
		map.put("statue", resume.getStatue());
		map.put("experience", resume.getExperience());
		map.put("jobintension", resume.getJobintension());
		map.put("createtime", resume.getCreatetime());	
		return map;
	}
	
	//通过简历id 修改简历的状态
	@RequestMapping(value="/changeResumeStatue")
	@ResponseBody
	public Map<String, Object> changeResumeStatue(@RequestParam("id")int id){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("要修改状态的简历id"+id);
		//通过 id 获取需要改变的简历
		Resume resume = adminManageResume.getResumeByResumeId(id);
		resume.setStatue(0);
		adminManageResume.updateResume(resume);
		map.put("value", "1");
		return map;
	}
	//通过简历id 修改简历的状态
	@RequestMapping(value="/changeResumeStatueForUn")
	@ResponseBody
	public Map<String, Object> changeResumeStatueForUn(@RequestParam("id")int id){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("要修改状态的简历id"+id);
		//通过 id 获取需要改变的简历
		Resume resume = adminManageResume.getResumeByResumeId(id);
		resume.setStatue(1);
		adminManageResume.updateResume(resume);
		map.put("value", "1");
		return map;
	}
}
