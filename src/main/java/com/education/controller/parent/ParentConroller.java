package com.education.controller.parent;

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
import com.education.entity.Users;
import com.education.service.common.CommonService;
import com.education.service.parent.ParentService;
import com.education.service.student.StudentService;

@Controller
@RequestMapping("/parent")
public class ParentConroller {
	private Map<String, Object> map = new HashMap<String, Object>();
	@Autowired(required = false)
	private CommonService commonService;
	@Autowired(required = false)
	private ParentService parentService;
	@Autowired(required = false)
	private StudentService studentService;
	@RequestMapping("/index")
	public String index(){
		return "system/applicant/student_PersonCenter.html";
	}
	@RequestMapping(value="/getUserInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUserInfo(HttpSession session){
		String name = (String) session.getAttribute("username");
		System.out.println("登录的用户名"+name);
		Users users = commonService.getUsersByName(name);
		map.put("id", users.getId());
		map.put("name", users.getName());
		map.put("password", users.getPassword());
		map.put("email", users.getEmail());
		map.put("status", users.getStatue());
		map.put("idcard", users.getIdcard());
		map.put("realname", users.getRealname());
		map.put("createtime", users.getCreatetime());
		if(users.getStatue() == 2){
			List<Resume> byuserid = commonService.getResumeByuserid(users.getId());
			if(byuserid.isEmpty()){
				map.put("statue", "未填写简历");
			}else if(byuserid.get(0).getStatue()==0){
				map.put("statue", "简历未审核");
			}else {
				map.put("statue", "简历有效");
			}
		}
		
		return map;
	}
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUser(@RequestParam("password")String password,@RequestParam("email")String email,@RequestParam("idcard")String idcard,@RequestParam("realname")String realname,HttpSession session){
		String name = (String) session.getAttribute("username");
		System.out.println("登录的用户民"+name);
		Users users = commonService.getUsersByName(name);
		users.setPassword(password);
		users.setEmail(email);
		users.setIdcard(idcard);
		users.setRealname(realname);
		commonService.updateUsers(users);
		
		map.put("result", 1);
		return map;
	}
	@RequestMapping(value="/deletePosition",method=RequestMethod.GET)
	
	public String deletePosition(@RequestParam("id")int id){
		parentService.removePosition(id);
		return "system/applicant/student_collect.html";
	}
	// 获取用户 已经填写的简历信息
	@RequestMapping(value="/getPositionsOfWrited",method=RequestMethod.POST)
	@ResponseBody
	public String getPositionsOfWrited(HttpSession session){
		String name = (String) session.getAttribute("username");
		System.out.println("登录的用户民"+name);
		Users users = commonService.getUsersByName(name);
		
		List<Position> positions = parentService.getPositionByuserid(users.getId());
		JSONArray json = new JSONArray();
		if(!positions.isEmpty()){
			
			for (Position po : positions) {
				JSONObject jo = new JSONObject();
				jo.put("id", po.getId());
				jo.put("title", po.getName());
				jo.put("place", po.getPlace());
				String[] strings = po.getInformation().split("-");
				jo.put("kemu", strings[0]);
				jo.put("information", strings[1]);
				jo.put("salary", po.getSalary());
				jo.put("contact", po.getContact());
				jo.put("createtime", po.getCreatetime());
				json.put(jo);
			}
			
		}
		System.out.println(json.toString());
		return json.toString();
		
	}
	@RequestMapping(value="/getPositionImfoByid",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPositionImfoByid(@RequestParam("id")int id){
		Position position = parentService.getPositionBypositionid(id);
		
		map.put("name", position.getName());
		map.put("place", position.getPlace());
		map.put("salary", position.getSalary());
		map.put("phonenum", position.getContact());
		String[] strings = position.getInformation().split("-");
		
		map.put("jieduan", strings[0]);
		map.put("information", strings[1]);
		
		map.put("status", position.getStatue());
		return map;
	}
	@RequestMapping(value="/addPosition",method=RequestMethod.GET)
	public String addTposition(@RequestParam("name")String name,@RequestParam("place")String place,@RequestParam("salary")String salary,@RequestParam("phonenum")String phonenum,@RequestParam("infomation")String infomation,HttpSession session){
		//获取用户的id
		int user = (Integer) session.getAttribute("userid");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		Position Tposition = new Position();
		Tposition.setName(name);
		Tposition.setContact(phonenum);
		Tposition.setCreatetime(date);
		Tposition.setInformation(infomation);
		Tposition.setSalary(salary);
		Tposition.setUser(user);
		Tposition.setPlace(place);
		parentService.addPosition(Tposition);
		
		return "system/applicant/student_collect.html";
	}
	
	@RequestMapping(value="/updatePositionImfoByid",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePositionImfoByid(@RequestParam("id")int id,@RequestParam("name")String name,@RequestParam("place")String place,@RequestParam("salary")String salary,@RequestParam("phonenum")String phonenum,@RequestParam("information")String infomation){
		//获取用户的id
		Position Tposition = parentService.getPositionBypositionid(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间
		
		Tposition.setName(name);
		Tposition.setContact(phonenum);
		Tposition.setCreatetime(date);
		Tposition.setInformation(infomation);
		Tposition.setSalary(salary);
		
		Tposition.setPlace(place);
		parentService.updatePosition(Tposition);
		map.put("result", 1);
		return map;
	}
	@RequestMapping(value="/getPositionsyuyue",method=RequestMethod.POST)
	@ResponseBody
	public String getPositionsyuyue(HttpSession session){
		String name = (String) session.getAttribute("username");
		System.out.println("登录的用户民"+name);
		Users users = commonService.getUsersByName(name);
		
		List<Position> positions = parentService.getPositionByuserid(users.getId());
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
				List<Resume> positionid = parentService.getResumesByPositionid(po.getId());
				if(positionid.isEmpty()){
					jo.put("number", 0);
					
				}else{
					jo.put("number", positionid.size());
				}
				json.put(jo);
			}
			
		}
		System.out.println(json.toString());
		return json.toString();
		
	}
	@RequestMapping(value="/getResumedByPositionid",method=RequestMethod.POST)
	@ResponseBody
	public String getResumedByPositionid(@RequestParam("id")int id){
		
		
		
		List<Resume> resumes = parentService.getResumesByPositionid(id);
		JSONArray json = new JSONArray();
		if(!resumes.isEmpty()){
			
			for (Resume resume : resumes) {
				JSONObject jo = new JSONObject();
				jo.put("id", resume.getId());
				jo.put("name", resume.getName());
				
				jo.put("phonenum", resume.getPhonenum());
				jo.put("status", resume.getStatue());
				jo.put("createtime", resume.getCreatetime());
				
				json.put(jo);
			}
			
		}
		System.out.println(json.toString());
		return json.toString();
		
	}
	//删除 职位对应的申请记录
	@RequestMapping(value="/deleteRecord",method=RequestMethod.GET)
	public String deleteRecord(@RequestParam("id")int id){
		parentService.RemoveUserpositionByPositionID(id);
		return "system/applicant/reservation_record.html";
	}
	
	
}
