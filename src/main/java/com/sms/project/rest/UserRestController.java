package com.sms.project.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sms.project.config.security.TokenProvider;
import com.sms.project.entity.Role;
import com.sms.project.entity.User;
import com.sms.project.entity.dto.AuthResponse;
import com.sms.project.entity.dto.LoginUser;
import com.sms.project.entity.dto.StudentDto;
import com.sms.project.entity.dto.UserDto;
import com.sms.project.rest.error.IncorrectPasswordException;
import com.sms.project.rest.error.StudentNotFoundException;
import com.sms.project.rest.error.UserExistsException;
import com.sms.project.rest.error.UserNotFoundException;
import com.sms.project.service.StudentService;
import com.sms.project.service.UserService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserRestController {

    private AuthenticationManager authenticationManager;
    private TokenProvider jwtTokenUtil;
    private UserService userService;
    private BCryptPasswordEncoder encoder;
    private StudentService studentService;
    
    public UserRestController(AuthenticationManager authenticationManager, 
    						  TokenProvider jwtTokenUtil, 
    						  UserService userService,
    						  BCryptPasswordEncoder encoder,
    						  StudentService studentService) {
    	this.authenticationManager = authenticationManager;
    	this.jwtTokenUtil = jwtTokenUtil;
    	this.userService = userService;
    	this.encoder = encoder;
    	this.studentService = studentService;
    }
    
    @Value("${jwt.token.validity}")
    public long TOKEN_VALIDITY;
    
    @GetMapping()
    public List<User> getUsers(){
    	return this.userService.findAll();
    }
    
    @GetMapping("/{userId}/student")
	public StudentDto getStudentByUserId(@PathVariable long userId) {
		StudentDto student = studentService.getStudentByUserId(userId);
		if(student == null) {
			throw new StudentNotFoundException("Student id not found "+userId);
		}
		return student;
	}
    
    @GetMapping("/roles")
    public Iterable<Role> getRoles(){
    	return this.userService.getRoles();
    }
    
    @PostMapping("/changeRoles")
    public User changeUserRoles(@RequestBody User user){
    	return this.userService.changeRoles(user);
    }
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
    	
    	
    	User user = this.userService.findOne(loginUser.getUsername());
    	
        if(user==null){
    		throw new UserNotFoundException("USER_NOT_FOUND");
    	}
        
        if(falsePassword(loginUser.getPassword(),user.getPassword())) {
        	throw new IncorrectPasswordException("INCORRECT_PASSWORD");
        }

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        final String token = jwtTokenUtil.generateToken(authentication); 
		
        return ResponseEntity.ok(new AuthResponse(token,this.TOKEN_VALIDITY,user));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
    	User user_db = this.userService.findOne(user.getUsername());
    	if(user_db!=null) {
    		throw new UserExistsException("USER_ALREADY_EXISTS");
    	}
    	
        return userService.save(user);
    }

    private boolean falsePassword(CharSequence rawPassword, String encodedPassword){
    	return !this.encoder.matches(rawPassword, encodedPassword);
    }

}