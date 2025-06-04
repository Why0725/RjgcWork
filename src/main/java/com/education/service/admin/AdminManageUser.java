package com.education.service.admin;

import java.util.List;

import com.education.entity.Users;

public interface AdminManageUser {
	public List<Users> getAllStudents();
	public List<Users> getAllParents();
	public Users getUsersByid(int userid);
	public void deleteUser(int userid);
	public void updateUser(Users users);
	public Users getUsersByName(String name);
	
	public Users getUsersByEmail(String email);
}
