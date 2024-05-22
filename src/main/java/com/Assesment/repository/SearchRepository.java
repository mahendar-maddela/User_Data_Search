package com.Assesment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Assesment.entity.Users;

public interface SearchRepository extends JpaRepository<Users, Long> {

//	Users findById(long id);
//
//	List<Users> findByName(String name);
//	
//	List<Users> findByEmail(String email);
	
	// Find user by id and return only id and name
    Users findFirstById(Long id);

    // Find user by name and return a list of users with id and name
    List<Users> findByName(String name);

    // Find user by email and return a list of users with id and name
    List<Users> findByEmail(String email);

    // Find user by id and return only email and phone
    Users findEmailAndPhoneById(Long id);

    // Find user by name and return a list of users with email and phone
    List<Users> findEmailAndPhoneByName(String name);

    // Find user by email and return a list of users with email and phone
    List<Users> findEmailAndPhoneByEmail(String email);

    // Find user by id and return only qualification and address
    Users findQualificationAndAddressById(Long id);

    // Find user by name and return a list of users with qualification and address
    List<Users> findQualificationAndAddressByName(String name);

    // Find user by email and return a list of users with qualification and address
    List<Users> findQualificationAndAddressByEmail(String email);


	


}
