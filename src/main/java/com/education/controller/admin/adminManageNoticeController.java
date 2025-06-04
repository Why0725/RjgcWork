package com.education.controller.admin;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.education.entity.Tnotice;
import com.education.service.notice.NoticeService;
/**
 * 操作 简历的Controller
 */
@Controller
@RequestMapping(value="/admin")
public class adminManageNoticeController {
	@Autowired(required = false)
	private NoticeService noticeService;
	
	@RequestMapping(value="/listNotices",method=RequestMethod.POST)
	@ResponseBody
	public String listNotices(){
		List<Tnotice> tnotices = noticeService.findTnotices();
		JSONArray json = new JSONArray();
		for (Tnotice notice : tnotices) {
			JSONObject jo = new JSONObject();
			jo.put("id", notice.getId());
			jo.put("title", notice.getTitle());
			jo.put("content", notice.getContent());
			jo.put("createtime", notice.getCreatetime());
			json.put(jo);
		}
		System.out.println(json.toString());
		return json.toString();
	}
	@RequestMapping(value="/deleteNotice",method=RequestMethod.GET)
	public String deleteNotice(@RequestParam("id")int id){
		noticeService.deleteTnoticeByID(id);
		return "system/admin/notice_table.html";
	}
	
	@RequestMapping(value="/addNotice",method=RequestMethod.GET)
	public String addNotice(@RequestParam("title")String title,@RequestParam("content")String content) throws UnsupportedEncodingException{
		/*String mytitle = new String(title.getBytes("ISO-8859-1"), "utf-8"); 
		String mycontent = new String(content.getBytes("ISO-8859-1"), "utf-8");*/
		noticeService.insertNotice(title, content);
		
		return "system/admin/notice_table.html";
	}
	
	@RequestMapping(value="/getNotice",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNotice(){
		Map<String, Object> map = new HashMap<String, Object>();
		Tnotice findTnotice = noticeService.findTnotice();
		if(findTnotice!=null){
			map.put("notice", "标题："+findTnotice.getTitle()+"，内容："+findTnotice.getContent()+"，创建时间："+findTnotice.getCreatetime());
		}else{
			map.put("notice", "无");
		}
		System.out.println(map.toString());
		return map;
	}
}
