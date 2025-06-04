package com.education.entity;

public class Tcomment {
	private long id;
	private long positionid;
	private long resumeid;
	private String name;
	private String content;
	private int flag;
	private String createtime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPositionid() {
		return positionid;
	}
	public void setPositionid(long positionid) {
		this.positionid = positionid;
	}
	public long getResumeid() {
		return resumeid;
	}
	public void setResumeid(long resumeid) {
		this.resumeid = resumeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
