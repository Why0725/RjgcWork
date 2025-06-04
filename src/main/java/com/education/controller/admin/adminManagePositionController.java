package com.education.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.education.entity.Position;
import com.education.entity.Recruiter;
import com.education.entity.Resume;
import com.education.entity.Users;
import com.education.service.admin.AdminManagePosition;
import com.education.service.admin.AdminManageUser;
/**
 * 操作 简历的Controller
 */
@Controller
@RequestMapping(value="/admin")
public class adminManagePositionController {
	@Autowired(required = false)
	private AdminManagePosition adminManagePosition;
	@Autowired(required = false)
	private AdminManageUser adminManageUser;
	// 获取 未审核的招聘信息
	@RequestMapping(value="/listUnauditedPosition",method=RequestMethod.POST)
	@ResponseBody
	public String listUnauditedPosition(HttpSession session){
		List<Position> positions = adminManagePosition.getUnauditedPosition();
		JSONArray json = new JSONArray();
		for (Position po : positions) {
			JSONObject jo = new JSONObject();
			jo.put("id", po.getId());
			
			Users user = adminManageUser.getUsersByid(po.getUser());
			jo.put("username", user.getName());
			jo.put("title", po.getName());
			jo.put("place", po.getPlace());
			jo.put("information", po.getInformation());
			jo.put("salary", po.getSalary());
			jo.put("contact", po.getContact());
			jo.put("createtime", po.getCreatetime());
			json.put(jo);
		}
		System.out.println(json.toString());
		return json.toString();
	}
	
		// 获取 未审核的招聘信息
		@RequestMapping(value="/listAuditedPosition",method=RequestMethod.POST)
		@ResponseBody
		public String listAuditedPosition(){
			List<Position> positions = adminManagePosition.getAuditedPositions();
			JSONArray json = new JSONArray();
			for (Position po : positions) {
				JSONObject jo = new JSONObject();
				jo.put("id", po.getId());
				
				Users user = adminManageUser.getUsersByid(po.getUser());
				jo.put("username", user.getName());
				jo.put("title", po.getName());
				jo.put("place", po.getPlace());
				jo.put("information", po.getInformation());
				jo.put("salary", po.getSalary());
				jo.put("contact", po.getContact());
				jo.put("createtime", po.getCreatetime());
				json.put(jo);
			}
			System.out.println(json.toString());
			return json.toString();
		}
	
	
		// 获取 该用户的招聘信息
		@RequestMapping(value="/listPositionByUserid",method=RequestMethod.POST)
		@ResponseBody
		public String listPositionByUserid(HttpSession session){
			System.out.println((String)session.getAttribute("useridforposition"));
			int id = Integer.parseInt((String)session.getAttribute("useridforposition"));
			System.out.println("=====--==="+id);
			
			List<Position> positions = adminManagePosition.getPositionsByuserId(id);
			JSONArray json = new JSONArray();
			for (Position po : positions) {
				JSONObject jo = new JSONObject();
				jo.put("id", po.getId());
				jo.put("title", po.getName());
				jo.put("place", po.getPlace());
				jo.put("information", po.getInformation());
				jo.put("salary", po.getSalary());
				jo.put("contact", po.getContact());
				jo.put("createtime", po.getCreatetime());
				json.put(jo);
			}
			System.out.println(json.toString());
			return json.toString();
		}
		
		// 通过 删除 招聘信息审核 招聘信息id 删除招聘信息
		
		@RequestMapping(value="/deletePositionByid",method=RequestMethod.GET)
		public String deletePositionByid(@RequestParam("id")int id){
			System.out.println("要删除的职位id为："+id);
			adminManagePosition.deletePositionByid(id);
			
			return "system/admin/recruit_table.html";

		}
		// 删除招聘者管理中的 招聘信息
		@RequestMapping(value="/deletePositionFromUserByid",method=RequestMethod.GET)
		public String deletePositionFromUserByid(@RequestParam("id")int id){
			System.out.println("要删除的职位id为："+id);
			adminManagePosition.deletePositionByid(id);
			
			return "system/admin/recruit_fromuser.html";
		}
		
		//通过职位id 获取职位信息
		@RequestMapping(value="/showPositionImByid",method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> showPositionImByid(@RequestParam("id")int id){
			System.out.println("要获取职位信息的id" + id);
			Position position = adminManagePosition.getPositionByid(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", position.getId());
			map.put("name", position.getName());
			map.put("place", position.getPlace());
			map.put("information", position.getInformation());
			map.put("salary", position.getSalary());
			map.put("contact", position.getContact());
			map.put("statue", position.getStatue());
			map.put("createtime", position.getCreatetime());
			return map;
			
		}
		
		//通过招聘id 修改招聘信息的状态
		@RequestMapping(value="/changePositionStatue")
		@ResponseBody
		public Map<String, Object> changePositionStatue(@RequestParam("id")int id){
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println("要修改状态的简历id"+id);
			Position position = adminManagePosition.getPositionByid(id);
			position.setStatue(0);
			adminManagePosition.updatePosition(position);
			map.put("value", "1");
			return map;
		}	
		
		//通过招聘id 修改招聘信息的状态
		@RequestMapping(value="/changePositionStatueToUn")
		@ResponseBody
		public Map<String, Object> changePositionStatueToUn(@RequestParam("id")int id){
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println("要修改状态的简历id======"+id);
			Position position = adminManagePosition.getPositionByid(id);
			position.setStatue(1);
			adminManagePosition.updatePosition(position);
			map.put("value", "1");
			return map;
		}	
}
