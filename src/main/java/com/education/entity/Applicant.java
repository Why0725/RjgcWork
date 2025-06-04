package com.education.entity;

public class Applicant {
	private int aid;
	private String aname;
	private String email;
	private String idcard;
	private long resumeid;
	private String realname;

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

	public long getResumeid() {
		return resumeid;
	}
	public void setResumeid(long resumeid) {
		this.resumeid = resumeid;
	}
	private String statue;
	public Applicant(int aid, String aname, String email, String statue) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.email = email;
		this.statue = statue;
	}
	public Applicant() {
		super();
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatue() {
		return statue;
	}
	public void setStatue(String statue) {
		this.statue = statue;
	}
	
}
