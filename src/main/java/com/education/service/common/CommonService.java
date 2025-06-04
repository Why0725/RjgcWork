package com.education.service.common;

import java.util.List;

import com.education.entity.Position;
import com.education.entity.Resume;
import com.education.entity.Userposition;
import com.education.entity.Users;

public interface CommonService {
	public Users getUsersByName(String name);
	public Users getUsersByEmail(String email);
	public void addUsers(Users users);
	public void updateUsers(Users users);
	public List<Resume> getAllResumes();
	public List<Position> getAllPositions();
	public Resume getResumeByid(int id);
	public Position getPositionByid(int id);
	public int getNumPositioned(int id);
	public List<Resume> getResumeByuserid(int userid);
}
