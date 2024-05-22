package com.Assesment.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Assesment.entity.Users;

public interface UserRepository extends JpaRepository<Users,Long > {

	
	
}
