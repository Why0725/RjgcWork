package com.education.entity;
/**
 * 用户bean
 * @author asus
 *
 */
public class Users {
	//用户id
	private int id;
	//用户名称
	private String name;
	//用户密码
	private String password;
	//用户email
	private String email;
	//用户 身份
	private int statue;
	//标记
	private int flag;
	private String idcard;
	private String realname;
	//创建日期
	private String createtime;

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Users() {
		super();
	}


	public Users(int id, String name, String password, String email, int statue, int flag, String createtime) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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
		return "Users [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", statue="
				+ statue + ", flag=" + flag + ", createtime=" + createtime + "]";
	}
	
	
}
