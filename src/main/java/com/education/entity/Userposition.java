package com.education.entity;
/**
 * 用户和 职位信息 （学生收藏职位信息）
 * @author asus
 *
 */
public class Userposition {
	//id
	private int id;
	//用户id
	private int user;
	//职位id
	private int position;
	//标记
	private int flag;
	//创建时间
	private String createtime;
	public Userposition(int id, int user, int position, int flag, String createtime) {
		super();
		this.id = id;
		this.user = user;
		this.position = position;
		this.flag = flag;
		this.createtime = createtime;
	}
	public Userposition() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
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
