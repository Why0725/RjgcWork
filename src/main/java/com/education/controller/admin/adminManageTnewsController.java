package com.education.controller.admin;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.education.entity.Tnews;
import com.education.service.news.TnewsService;
/**
 * 操作 简历的Controller
 */
@Controller
@RequestMapping(value="/admin")
public class adminManageTnewsController {
	@Autowired(required = false)
	private  TnewsService tnewsService;
	
	@RequestMapping(value="/listTnews",method=RequestMethod.POST)
	@ResponseBody
	public String listNotices(){
		List<Tnews> news = tnewsService.findNews();
		JSONArray json = new JSONArray();
		for (Tnews notice : news) {
			JSONObject jo = new JSONObject();
			jo.put("id", notice.getId());
			jo.put("title", notice.getTitle());
			jo.put("content", notice.getContent());
			jo.put("clicknum", notice.getClicknum());
			jo.put("createtime", notice.getCreatetime());
			json.put(jo);
		}
		System.out.println(json.toString());
		return json.toString();
	}
	@RequestMapping(value="/deleteNews",method=RequestMethod.GET)
	public String deleteNotice(@RequestParam("id")int id){
		tnewsService.dropNews(id);
		return "system/admin/news_table.html";
	}
	
	@RequestMapping(value="/toAddNews",method=RequestMethod.GET)
	public String toAddNews(){
		
		return "system/admin/news_add.html";
	}
	
	
	
	@RequestMapping(value="/addNews",method=RequestMethod.GET)
	public String addNotice(HttpServletRequest request,@RequestParam("title")String title,@RequestParam("content")String content) throws UnsupportedEncodingException{
		tnewsService.addNews(title, content);
		
		return "system/admin/news_table.html";
	}
//	
//	@RequestMapping(value="/getNotice",method=RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> getNotice(){
//		Map<String, Object> map = new HashMap<String, Object>();
//		Tnotice findTnotice = noticeService.findTnotice();
//		if(findTnotice!=null){
//			map.put("notice", "标题："+findTnotice.getTitle()+"内容："+findTnotice.getContent()+"创建时间："+findTnotice.getCreatetime());
//		}else{
//			map.put("notice", "无");
//		}
//		System.out.println(map.toString());
//		return map;
//	}
}
