package com.crud.rest.dao;

import java.util.List;


import com.crud.rest.beans.UserLogin;

/**
 * @author Archana
 *
 */
public interface UserDao {
	
	UserLogin findById(int id);

	UserLogin findByEmailId(String emailid);
	
	UserLogin findByName(String name);

	void saveUser(UserLogin user);

	void updateUser(UserLogin user);

	void deleteUserById(int id);

	List<UserLogin> findAllUser();

	void deleteAllUser();

	boolean isUserExist(UserLogin user);

}
