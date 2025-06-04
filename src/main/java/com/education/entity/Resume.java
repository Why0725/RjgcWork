package com.education.entity;
/**
 * 学生简历
 * @author asus
 *
 */
public class Resume {
	//简历id
	private int id;
	//简历人
	private String name;
	//学生 性别
	private int sex;
	//学生 出生年月
	private String birthday;
	//学生手机num
	private String phonenum;
	//学生电子邮件
	private String email;
	//学生学校名称
	private String schoolname;
	//入手时间到毕业时间
	private String time;
	//学历
	private String education;
	//专业
	private String major;
	//工作经验
	private String experience;
	//工作意向
	private String jobintension;
	//关联的用户
	private int user;
	// 简历状态
	private int statue;
	//简历标记
	private int flag;
	private String createtime;
	public Resume() {
		super();
	}
	public Resume(int id, String name, int sex, String birthday, String phonenum, String email, String schoolname,
			String time, String education, String major, String experience, String jobintension, int user, int statue,
			int flag, String createtime) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.phonenum = phonenum;
		this.email = email;
		this.schoolname = schoolname;
		this.time = time;
		this.education = education;
		this.major = major;
		this.experience = experience;
		this.jobintension = jobintension;
		this.user = user;
		this.statue = statue;
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getJobintension() {
		return jobintension;
	}
	public void setJobintension(String jobintension) {
		this.jobintension = jobintension;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getStatue() {
		return statue;
	}
	public void setStatue(int statue) {
		this.statue = statue;
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
		return "Resume [id=" + id + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", phonenum="
				+ phonenum + ", email=" + email + ", schoolname=" + schoolname + ", time=" + time + ", education="
				+ education + ", major=" + major + ", experience=" + experience + ", jobintension=" + jobintension
				+ ", user=" + user + ", statue=" + statue + ", flag=" + flag + ", createtime=" + createtime + "]";
	}
	
	
}
