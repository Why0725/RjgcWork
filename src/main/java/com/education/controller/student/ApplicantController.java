package com.education.controller.student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
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
import com.education.entity.Tcomment;
import com.education.service.comment.CommentService;
import com.education.service.common.CommonService;

/**
 * 应聘者 controller ，学生做家教
 * @author asus
 *
 */
@Controller
@RequestMapping("/applicant")
public class ApplicantController {
	@Autowired(required = false)
	private CommonService commonService;
	@Autowired(required = false)
	private CommentService commentService;
	@RequestMapping(value="/searchTeacher",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> searchTeacher(@RequestParam("place")String place,@RequestParam("kemu")String kemu,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		session.setAttribute("place", place);
		session.setAttribute("kemu", kemu);
		map.put("result", 1);
		return map;
	}
	
	// 获取所有的求职者的简历信息
	@RequestMapping(value="/listAllResume",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> listApplicants(@RequestParam("stage")String stage,@RequestParam("place")String place,HttpSession session){
		System.out.println("科目"+stage + "========== 地点+"+place);
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray array = new JSONArray();
		if(session.getAttribute("place")!=null){
			stage = (String)session.getAttribute("kemu");
			place = (String) session.getAttribute("place");
			session.removeAttribute("place");	
			session.removeAttribute("kemu");
		}
		
		// 搜索条件 为不限 + 不限
		if("不限".equals(stage) && "不限".equals(place)){
			List<Resume> resumes = commonService.getAllResumes();
			if(!resumes.isEmpty()){
				
				for (Resume resume : resumes) {
					//必须已经审核了的
					if (resume.getStatue() == 1 ) {
						JSONObject object = new JSONObject();
						object.put("id", resume.getId());
						object.put("name", resume.getName());
						object.put("schoolname", resume.getSchoolname());
						object.put("experience", resume.getExperience());
						object.put("sex", resume.getSex());
						String[] spilts = resume.getJobintension().split("-");
						String pString = spilts[0];
						String kemu = "";
						for(int i=1;i<spilts.length-1;i++)
						{
							kemu +=spilts[i]+",";
						}
						kemu +=spilts[spilts.length-1];
						object.put("place", pString);
						object.put("kemu", kemu);
						
						array.put(object);
					}
					
				}
				
			}
			map.put("allResumes", array.toString());
			// 搜索条件 为有条件 + 不限
		}else if(!"不限".equals(stage) && "不限".equals(place)) {
			List<Resume> resumes = commonService.getAllResumes();
			if(resumes != null){
				for (Resume resume : resumes) {
					if (resume.getStatue() == 1) {
						// 简历的意愿包含了什么课程，和地点
						if((resume.getJobintension()).contains(stage)){
							JSONObject object = new JSONObject();
							object.put("id", resume.getId());
							object.put("name", resume.getName());
							object.put("schoolname", resume.getSchoolname());
							object.put("experience", resume.getExperience());
							object.put("sex", resume.getSex());
							String[] spilts = resume.getJobintension().split("-");
							String pString = spilts[0];
							String kemu = "";
							for(int i=1;i<spilts.length-1;i++)
							{
								kemu +=spilts[i]+",";
							}
							kemu +=spilts[spilts.length-1];
							object.put("place", pString);
							object.put("kemu", kemu);
							
							array.put(object);
						}
						
					}
				}
			}
			map.put("allResumes", array.toString());
			// 搜索条件 为不限 +地点要求 
		}else if("不限".equals(stage) && !"不限".equals(place)) {
			List<Resume> resumes = commonService.getAllResumes();
			if(resumes != null){
				for (Resume resume : resumes) {
					if (resume.getStatue() == 1) {
						// 简历的意愿包含了什么课程，和地点
						if((resume.getJobintension()).contains(place)){
							JSONObject object = new JSONObject();
							object.put("id", resume.getId());
							object.put("name", resume.getName());
							object.put("schoolname", resume.getSchoolname());
							object.put("experience", resume.getExperience());
							object.put("sex", resume.getSex());
							String[] spilts = resume.getJobintension().split("-");
							String pString = spilts[0];
							String kemu = "";
							for(int i=1;i<spilts.length-1;i++)
							{
								kemu +=spilts[i]+",";
							}
							kemu +=spilts[spilts.length-1];
							object.put("place", pString);
							object.put("kemu", kemu);
							
							array.put(object);
						}
						
					}
				}
			}
			map.put("allResumes", array.toString());
			
		}else{
			// 两个条件都有
			List<Resume> resumes = commonService.getAllResumes();
			if(resumes != null){
				for (Resume resume : resumes) {
					if (resume.getStatue() == 1) {
						// 简历的意愿包含了什么课程，和地点
						if((resume.getJobintension()).contains(place)&&(resume.getJobintension()).contains(stage)){
							JSONObject object = new JSONObject();
							object.put("id", resume.getId());
							object.put("name", resume.getName());
							object.put("sex", resume.getSex());
							object.put("schoolname", resume.getSchoolname());
							object.put("experience", resume.getExperience());
							
							String[] spilts = resume.getJobintension().split("-");
							String pString = spilts[0];
							String kemu = "";
							for(int i=1;i<spilts.length-1;i++)
							{
								kemu +=spilts[i]+",";
							}
							kemu +=spilts[spilts.length-1];
							object.put("place", pString);
							object.put("kemu", kemu);
							
							array.put(object);
						}
						
					}
				}
			}
			map.put("allResumes", array.toString());
		}
		System.out.println(array.toString());
		return map;
	}
	
	
	@RequestMapping(value="/getResumeByid",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getResumeByid(@RequestParam("id")int id,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray array = new JSONArray();
		session.setAttribute("commentByResumeID", id);
		Resume resume = commonService.getResumeByid(id);
		if(resume != null){
			//必须已经审核了的
			if (resume.getStatue() == 1 ) {
				JSONObject object = new JSONObject();
				object.put("id", resume.getId());
				object.put("name", resume.getName());
				object.put("schoolname", resume.getSchoolname());
				object.put("education", resume.getEducation());
				object.put("major", resume.getMajor());
				object.put("createtime", resume.getCreatetime());
				object.put("phonenum", resume.getPhonenum()+","+resume.getEmail());
				object.put("experience", resume.getExperience());
				object.put("sex", resume.getSex());
				String[] spilts = resume.getJobintension().split("-");
				String pString = spilts[0];
				String kemu = "";
				for(int i=1;i<spilts.length-1;i++)
				{
					kemu +=spilts[i]+",";
				}
				kemu +=spilts[spilts.length-1];
				object.put("place", pString);
				object.put("kemu", kemu);
				
				array.put(object);
			}
		}
		map.put("resume", array.toString());
		System.err.println(array.toString());
		return map;
	}
	
	@RequestMapping(value="/getPositionByid",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPositionByid(@RequestParam("id")int id,HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray array = new JSONArray();
		session.setAttribute("commentByPositionID", id);
		Position position = commonService.getPositionByid(id);
		if(position != null){
			//必须已经审核了的
			if (position.getStatue() == 1 ) {
				JSONObject object = new JSONObject();
				object.put("id", position.getId());
				object.put("name", position.getName());
				object.put("place", position.getPlace());
				object.put("salary", position.getSalary());
				object.put("createtime", position.getCreatetime());
				object.put("phonenum", position.getContact());
				object.put("num", commonService.getNumPositioned(id));
				object.put("mainInfo", "教授"+position.getInformation().replaceAll("-", ","));
				array.put(object);
			}
		}
		map.put("position", array.toString());
		System.out.println("-----"+array.toString());
		return map;
	}
	
		//获取 根据简历ID 获取获取该老师的评论
		@RequestMapping(value="/listCommentsByResumeID",method=RequestMethod.POST)
		@ResponseBody 
		public Map<String, Object> listCommentsByResumeID(HttpSession session){
			Map<String, Object> map = new HashMap<String, Object>();
			int commentByResumeID = (Integer) session.getAttribute("commentByResumeID");
			System.out.println(commentByResumeID);
			List<Tcomment> lists = commentService.findTcommentsByResumtID(commentByResumeID);
			JSONArray array = new JSONArray();
			if(lists!=null){
				JSONObject object;
				for (Tcomment list : lists) {
					object = new JSONObject();
					object.put("name", list.getName());
					object.put("content", list.getContent());
					object.put("createtime", list.getCreatetime());		
					array.put(object);
				}
			}
			map.put("comments", array.toString());
			System.out.println("------"+array.toString());
			return map;
		}
	
		// 通过 职位id 获取职位评价
		@RequestMapping(value="/listCommentsByPositionID",method=RequestMethod.POST)
		@ResponseBody 
		public Map<String, Object> listCommentsByPositionID(HttpSession session){
			Map<String, Object> map = new HashMap<String, Object>();
			int commentByPositionID = (Integer) session.getAttribute("commentByPositionID");
			System.out.println(commentByPositionID);
			List<Tcomment> lists = commentService.findTcommentByPositionID(commentByPositionID);
			JSONArray array = new JSONArray();
			if(lists!=null){
				JSONObject object;
				for (Tcomment list : lists) {
					object = new JSONObject();
					object.put("name", list.getName());
					object.put("content", list.getContent());
					object.put("createtime", list.getCreatetime());		
					array.put(object);
				}
			}
			map.put("comments", array.toString());
			System.out.println("------"+array.toString());
			return map;
		}
		//增加评论
		@RequestMapping(value="/addComment",method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> addComment(@RequestParam("resumeid")int resumeid,@RequestParam("positionid")int positionid,@RequestParam("content")String content,HttpSession session){
			Map<String, Object> map = new HashMap<String, Object>();
			String username = (String) session.getAttribute("username");
			Integer userid = (Integer) session.getAttribute("userid");
			if(username !=null&&userid!=null){
				commentService.addTcomment(positionid, resumeid, username, content);
				// 增加成功
				map.put("result", 1);
			}else{
				// 没有登录
				map.put("result", 0);
			}
			
			return map;
		}
		
		
		// 获取所有的求职者的简历信息
		@RequestMapping(value="/listAllPositions",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> listAllPositions(@RequestParam("stage")String stage,@RequestParam("place")String place){
			System.out.println("科目"+stage + "========== 地点+"+place);
			Map<String, Object> map = new HashMap<String, Object>();
			JSONArray array = new JSONArray();
			// 搜索条件 为不限 + 不限
			if("不限".equals(stage) && "不限".equals(place)){
				List<Position> positions = commonService.getAllPositions();
				if(!positions.isEmpty()){
					
					for (Position position : positions) {
						//必须已经审核了的
						if (position.getStatue() == 1 ) {
							JSONObject object = new JSONObject();
							object.put("id", position.getId());
							object.put("name", position.getName());
							object.put("place", position.getPlace());
							object.put("salary", position.getSalary());		
							array.put(object);
						}
						
					}
					
				}
				map.put("allPositions", array.toString());
				// 搜索条件 为有条件 + 不限
			}else if(!"不限".equals(stage) && "不限".equals(place)) {
				List<Position> positions = commonService.getAllPositions();
				if(positions != null){
					for (Position position : positions) {
						if (position.getStatue() == 1) {
							// 职位的行包含了什么课程
							if((position.getInformation()).contains(stage)){
								JSONObject object = new JSONObject();
								object.put("id", position.getId());
								object.put("name", position.getName());
								object.put("place", position.getPlace());
								object.put("salary", position.getSalary());	
								
								array.put(object);
								
							}
							
						}
					}
				}
				map.put("allPositions", array.toString());
				// 搜索条件 为不限 +地点要求 
			}else if("不限".equals(stage) && !"不限".equals(place)) {
				List<Position> positions = commonService.getAllPositions();
				if(positions != null){
					for (Position position : positions) {
						if (position.getStatue() == 1) {
							// 指定的位置
							if(place.equals(position.getPlace())){
								JSONObject object = new JSONObject();
								object.put("id", position.getId());
								object.put("name", position.getName());
								object.put("place", position.getPlace());
								object.put("salary", position.getSalary());	
								
								array.put(object);
							}
							
						}
					}
				}
				map.put("allPositions", array.toString());
				
			}else{
				// 两个条件都有
				List<Position> positions = commonService.getAllPositions();
				if(positions != null){
					for (Position position : positions) {
						if (position.getStatue() == 1) {
							// 指定的位置和阶段
							if(place.equals(position.getPlace())&&(position.getInformation()).contains(stage)){
								JSONObject object = new JSONObject();
								object.put("id", position.getId());
								object.put("name", position.getName());
								object.put("place", position.getPlace());
								object.put("salary", position.getSalary());		
								array.put(object);
							}
							
						}
					}
				}
				map.put("allPositions", array.toString());
			}
			System.out.println(array.toString());
			return map;
		}
	
	
}
