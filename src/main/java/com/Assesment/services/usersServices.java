package com.Assesment.services;

import java.util.List;

import com.Assesment.entity.Users;



public interface usersServices {
	
	public Users saveUser(Users user);
	
	public Users updateUser(Users user);
	
	public void deleteUser(Users user);
	
	public Users findById(long id);
	
	public List<Users>findAll();
}
