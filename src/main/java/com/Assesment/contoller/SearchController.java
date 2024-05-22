package com.Assesment.contoller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Assesment.entity.Users;
import com.Assesment.exception.ResourceNotFoundException;
import com.Assesment.repository.SearchRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/users")
public class SearchController {

    @Autowired
    private SearchRepository searchRepository;

    // Endpoint to search users by ID, Name, or Email
    @GetMapping("/byIdOrName")
    public ResponseEntity<?> searchUsersByIdOrName(@RequestParam(required = false) Long id,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String email) {

        try {
            List<Users> users;
            if (id != null) {
                Users user = searchRepository.findFirstById(id);
                return handleUserResponseByIdOrName(user, id);
            } else if (name != null) {
                users = searchRepository.findByName(name);
                return handleUserResponseByIdOrName(users);
            } else if (email != null) {
                users = searchRepository.findByEmail(email);
                return handleUserResponseByIdOrName(users);
            } else {
                throw new IllegalArgumentException("Please provide id, name, or email for searching.");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to search users by Email and Phone
    @GetMapping("/byEmailAndPhone")
    public ResponseEntity<?> searchUsersByEmailAndPhone(@RequestParam(required = false) Long id,
                                                        @RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String email) {

        try {
            List<Users> users;
            if (id != null) {
                Users user = searchRepository.findQualificationAndAddressById(id);
                return handleUserResponseByEmailAndPhone(user);
            } else if (name != null) {
                users = searchRepository.findQualificationAndAddressByName(name);
                return handleUserResponseByEmailAndPhone(users);
            } else if (email != null) {
                users = searchRepository.findQualificationAndAddressByEmail(email);
                return handleUserResponseByEmailAndPhone(users);
            } else {
                throw new IllegalArgumentException("Please provide id, name, or email for searching.");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to search users by Qualification and Address
    @GetMapping("/byQualificationAndAddress")
    public ResponseEntity<?> searchUsersByQualificationAndAddress(@RequestParam(required = false) Long id,
                                                                  @RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) String email) {

        try {
            List<Users> users;
            if (id != null) {
                Users user = searchRepository.findQualificationAndAddressById(id);
                return handleUserResponseByQualificationAndAddress(user);
            } else if (name != null) {
                users = searchRepository.findQualificationAndAddressByName(name);
                return handleUserResponseByQualificationAndAddress(users);
            } else if (email != null) {
                users = searchRepository.findQualificationAndAddressByEmail(email);
                return handleUserResponseByQualificationAndAddress(users);
            } else {
                throw new IllegalArgumentException("Please provide id, name, or email for searching.");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Handles response for searchUsersByIdOrName endpoint
    private ResponseEntity<?> handleUserResponseByIdOrName(Users user, Long id) {
        if (user != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user.getId());
            jsonObject.put("name", user.getName());
            return ResponseEntity.ok().body(jsonObject.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    // Handles response for searchUsersByIdOrName endpoint
    private ResponseEntity<?> handleUserResponseByIdOrName(List<Users> users) {
        if (!users.isEmpty()) {
            JSONArray jsonArray = new JSONArray();
            for (Users user : users) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", user.getName());
                jsonObject.put("id", user.getId());
                jsonArray.put(jsonObject);
            }
            return ResponseEntity.ok().body(jsonArray.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Handles response for searchUsersByEmailAndPhone endpoint
    private ResponseEntity<?> handleUserResponseByEmailAndPhone(Users user) {
        if (user != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", user.getEmail());
            jsonObject.put("phone", user.getPhone());
            return ResponseEntity.ok().body(jsonObject.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Handles response for searchUsersByEmailAndPhone endpoint
    private ResponseEntity<?> handleUserResponseByEmailAndPhone(List<Users> users) {
        if (!users.isEmpty()) {
            JSONArray jsonArray = new JSONArray();
            for (Users user : users) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", user.getEmail());
                jsonObject.put("phone", user.getPhone());
                jsonArray.put(jsonObject);
            }
            return ResponseEntity.ok().body(jsonArray.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Handles response for searchUsersByQualificationAndAddress endpoint
    private ResponseEntity<?> handleUserResponseByQualificationAndAddress(Users user) {
        if (user != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("qualification", user.getQualification());
            jsonObject.put("address", user.getAddress());
            return ResponseEntity.ok().body(jsonObject.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Handles response for searchUsersByQualificationAndAddress endpoint
    private ResponseEntity<?> handleUserResponseByQualificationAndAddress(List<Users> users) {
        if (!users.isEmpty()) {
            JSONArray jsonArray = new JSONArray();
            for (Users user : users) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("qualification", user.getQualification());
                jsonObject.put("address", user.getAddress());
                jsonArray.put(jsonObject);
            }
            return ResponseEntity.ok().body(jsonArray.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
