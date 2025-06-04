package com.education.entity;
/**
 *  职位信息 
 * @author asus
 *
 */
public class Position {
	private int id; //职位id	
	private String name; //职位名称	
	private String place;	//工作地点
	private String information;	//工作信息
	private String salary; 		//薪资
	private String contact;	//联系方式
	private int statue;    //职位信息状态
	private int user;	//关联的用户
	private int flag;	// 标记
	private String createtime; //创建时间
	
	public Position() {
		super();
	}
	public Position(int id, String name, String place, String information, String salary, String contact, int statue,
			int user, int flag, String createtime) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.information = information;
		this.salary = salary;
		this.contact = contact;
		this.statue = statue;
		this.user = user;
		this.flag = flag;
		this.createtime = createtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getStatue() {
		return statue;
	}
	public void setStatue(int statue) {
		this.statue = statue;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
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
	@Override
	public String toString() {
		return "Position [id=" + id + ", name=" + name + ", place=" + place + ", information=" + information
				+ ", salary=" + salary + ", contact=" + contact + ", statue=" + statue + ", user=" + user + ", flag="
				+ flag + ", createtime=" + createtime + "]";
	}
}
