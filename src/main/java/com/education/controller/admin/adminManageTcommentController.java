package com.education.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
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
import com.education.entity.Tcomment;
import com.education.entity.Users;
import com.education.service.admin.AdminManagePosition;
import com.education.service.admin.AdminManageResume;
import com.education.service.admin.AdminManageUser;
import com.education.service.comment.CommentService;


@Controller
@RequestMapping(value="/admin")
public class adminManageTcommentController {
	@Autowired(required = false)
	CommentService commentService;
	@RequestMapping(value="/toCommentList",method=RequestMethod.GET)
	public String toCommentList(@RequestParam("positionid") String positionid,@RequestParam("resumeid") String resumeid,HttpSession session){
		session.setAttribute("positionid", positionid);
		session.setAttribute("resumeid", resumeid);
		return "system/admin/comment.html";
	}
	
	
	//查找关于职位 或者 教师的评价
	@RequestMapping(value="/getCommentList",method=RequestMethod.POST)
	@ResponseBody
	public String getCommentList(HttpSession session){
		
		String resumeid = (String) session.getAttribute("resumeid");
		String positionid = (String) session.getAttribute("positionid");
		List<Tcomment> tcomments;
		if(resumeid !=null&&positionid!=null) {
			if("0".equals(resumeid)){
				// 代表： 不是教师评价，获取的是职位评价
				long id = Long.parseLong(positionid);
				tcomments = commentService.findTcommentByPositionID(id);
			} else{
				long id = Long.parseLong(resumeid);
				tcomments = commentService.findTcommentsByResumtID(id);
			}
			JSONArray json = new JSONArray();
			for (Tcomment a : tcomments) {
				JSONObject jo = new JSONObject();
				jo.put("id", a.getId());
				jo.put("name", a.getName());
				jo.put("content", a.getContent());
				jo.put("createtime", a.getCreatetime());
				json.put(jo);
			}
			System.out.println("========");
			System.out.println(json.toString());
			return json.toString();
		}else{
			return null;
		}
	} 
	@RequestMapping(value="/deleteCommentByID",method=RequestMethod.GET)
	public String deleteCommentByID(@RequestParam("id") String id){
		long commid = Long.parseLong(id);
		commentService.deleteTcomment(commid);
		return "system/admin/comment.html";
	}
	
	
	
}
