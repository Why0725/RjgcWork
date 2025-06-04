package com.education.controller.student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Userposition;
import com.education.entity.Users;
import com.education.service.admin.AdminManageResume;
import com.education.service.common.CommonService;
import com.education.service.student.StudentService;

@Controller
@RequestMapping("/teacher")
public class StudentController {
	@Autowired(required = false) StudentService studentService;
	@Autowired(required = false)
	private CommonService commonService;
	@Autowired(required = false)
	private AdminManageResume adminManageResume;
	@RequestMapping("/index")
	public String index(){
		return "system/applicant/teacher_PersonCenter.html";
	}
	
	
	@RequestMapping(value="/getTeacherInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTeacherInfo(HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		int userid = (Integer) session.getAttribute("userid");//session 中获取到的username
		
		
		Resume resume = studentService.getResumeByuserid(userid);
		if(resume!=null){
			map.put("id", resume.getId());
			map.put("userid", userid);
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
			String[] strings = resume.getJobintension().split("-");
			map.put("place", strings[0]);
			map.put("kemu", strings[1]);
		}else{
			map.put("userid", userid);
		}
			
		return map;
	}

	@RequestMapping(value="/updateResume",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateResume(@RequestParam("id")int id,@RequestParam("userid")int userid,@RequestParam("name")String name,
			@RequestParam("birthday")String birthday,@RequestParam("sex")int sex,@RequestParam("phonenum")String phonenum,
			@RequestParam("email")String email,@RequestParam("schoolname")String schoolname,@RequestParam("time")String time,@RequestParam("education")String education,
			@RequestParam("major")String major,@RequestParam("experience")String experience,@RequestParam("jobintension")String jobintension){
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		Resume resume = adminManageResume.getResumeByResumeId(id);
		if(resume==null){
			Resume resumes = new Resume();
			resumes.setName(name);
			resumes.setBirthday(birthday);
			resumes.setEducation(education);
			resumes.setEmail(email);
			resumes.setExperience(experience);
			resumes.setJobintension(jobintension);
			resumes.setMajor(major);
			resumes.setSchoolname(schoolname);
			resumes.setPhonenum(phonenum);
			resumes.setTime(time);
			resumes.setSex(sex);
			resumes.setCreatetime(date);
			resumes.setUser(userid);
			studentService.addResume(resumes);
		}else{
			resume.setName(name);
			resume.setBirthday(birthday);
			resume.setEducation(education);
			resume.setEmail(email);
			resume.setExperience(experience);
			resume.setJobintension(jobintension);
			resume.setMajor(major);
			resume.setSchoolname(schoolname);
			resume.setPhonenum(phonenum);
			resume.setTime(time);
			resume.setSex(sex);
			resume.setCreatetime(date);
			studentService.updateResume(resume);
		}
		
		return map;
	}
	
	@RequestMapping(value="/addResume",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addResume(@RequestParam("name")String name,
			@RequestParam("birthday")String birthday,@RequestParam("sex")int sex,@RequestParam("phonenum")String phonenum,
			@RequestParam("email")String email,@RequestParam("schoolname")String schoolname,@RequestParam("time")String time,@RequestParam("education")String education,
			@RequestParam("major")String major,@RequestParam("experience")String experience,@RequestParam("jobintension")String jobintension,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String username = (String)session.getAttribute("registername");
		Users users = commonService.getUsersByName(username);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		Resume resume = new Resume();
		resume.setName(name);
		resume.setBirthday(birthday);
		resume.setEducation(education);
		resume.setEmail(email);
		resume.setExperience(experience);
		resume.setJobintension(jobintension);
		resume.setMajor(major);
		resume.setSchoolname(schoolname);
		resume.setPhonenum(phonenum);
		resume.setTime(time);
		resume.setSex(sex);
		resume.setCreatetime(date);
		resume.setUser(users.getId());
		studentService.addResume(resume);
		
		map.put("result", 1);
		return map;
	}
	
	@RequestMapping(value="/getPositionsyuyue",method=RequestMethod.POST)
	@ResponseBody
	public String getPositionsyuyue(HttpSession session){
		int id = (Integer) session.getAttribute("userid");//session 中获取到的username
		
		
		
		List<Position> positions = studentService.getPositions(id);
		JSONArray json = new JSONArray();
		if(!positions.isEmpty()){
			
			for (Position po : positions) {
				JSONObject jo = new JSONObject();
				jo.put("id", po.getId());
				jo.put("title", po.getName());
				jo.put("place", po.getPlace());
				jo.put("salary", po.getSalary());
				jo.put("contact", po.getContact());
				jo.put("createtime", po.getCreatetime());
				
				json.put(jo);
			}
			
		}
		System.out.println(json.toString());
		return json.toString();
		
	}
	
	@RequestMapping(value="/deleteRecodeByuseridAndPositionid")
	public String deleteRecodeByuseridAndPositionid(@RequestParam("id")int id,HttpSession session){
		int userid = (Integer) session.getAttribute("userid");//session 中获取到的username
		
		studentService.romoveUserPosition(userid, id);
		return "system/applicant/myposition_record.html";
	}
	@RequestMapping(value="/applyPosition",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> applyPosition(@RequestParam("positionid")int positionid,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		if(session.getAttribute("userid") == null){
			map.put("result", 2);
		}else{
			int id = (Integer) session.getAttribute("userid");//session 中获取到的userid
			String username = (String)session.getAttribute("username");
			Users users = commonService.getUsersByName(username);
			//学生家长自己申请自己的，这种操作是不能的
			if(users.getStatue() == 3){
				map.put("result", 3);
			}else{
				int i = studentService.findUserpositionByuseridAndpositonid(id, positionid);
				
				if(i==1){
					Userposition userposition = new Userposition();
					userposition.setUser(id);
					userposition.setPosition(positionid);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
					String date = df.format(new Date());// new Date()为获取当前系统时间
					userposition.setCreatetime(date);
					studentService.addUserPosition(userposition);
					map.put("result", 1);
				}else{
					//已经存在，就是已经申请了
					map.put("result", 0);
				}
				
				
			}
		}
		return map;
	}
	
}
