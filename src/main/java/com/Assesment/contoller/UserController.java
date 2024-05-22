package com.Assesment.contoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assesment.entity.Users;
import com.Assesment.exception.ResourceNotFoundException;
import com.Assesment.repository.UserRepository;




@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class UserController {
	
	@Autowired
	private  UserRepository userRepository;
	

	@PostMapping("/v1/users")
	public Users createPatient(@RequestBody Users  createUser) {
		System.out.println("this data is being posted");
		return userRepository.save(createUser);

	}

//	get all 
	@GetMapping("/v1/users")
	public List<Users> getAppointment() {
		return userRepository.findAll();
	}

	@DeleteMapping("/v1/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deletestudent(@PathVariable Long id) {
		Users member = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("student not exist with id :" + id));

		userRepository.delete(member);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	// get website by id rest api
	@GetMapping("/v1/users/{id}")
	public ResponseEntity<Users> getstudentById(@PathVariable Long id) {
		Users user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("student not exist with id :" + id));
		return ResponseEntity.ok(user);
	}

	// // update website rest api

	@PutMapping("/v1/users/{id}")
	public ResponseEntity<Users> updateuser(@PathVariable Long id, @RequestBody Users userdeatils) {

		Users user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("student not exist with id :" + id));
		user.setName(userdeatils.getName());
		user.setEmail(userdeatils.getEmail());
		user.setPhone(userdeatils.getPhone());
		user.setQualification(userdeatils.getQualification());
		user.setAddress(userdeatils.getAddress());
		



		Users updateuser = userRepository.save(user);
		return ResponseEntity.ok(updateuser);
	}


}
