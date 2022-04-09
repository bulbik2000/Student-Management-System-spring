package com.sms.project.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sms.project.entity.Role;
import com.sms.project.entity.User;
import com.sms.project.entity.dto.UserDto;
import com.sms.project.repository.UserRepository;
import com.sms.project.service.RoleService;
import com.sms.project.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private RoleService roleService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bcryptEncoder;
    
    public UserServiceImpl(
    		RoleService roleService,
    		UserRepository userRepository,
    		BCryptPasswordEncoder bcryptEncoder) {
		this.roleService = roleService;
		this.bcryptEncoder = bcryptEncoder;
		this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public Iterable<Role> getRoles(){
    	return this.roleService.getRoles();
    }
    
    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    
    @Override
    public User save(UserDto user) {

        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = null;
        Set<Role> roleSet = new HashSet<>();

        if(nUser.getEmail().split("@")[1].equals("admin.edu")){
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }else if(nUser.getEmail().split("@")[1].equals("lecturer.edu")) {
        	role = roleService.findByName("LECTURER");
            roleSet.add(role);
        }else {
        	role = roleService.findByName("STUDENT");
        	roleSet.add(role);
        }

        nUser.setRoles(roleSet);
        return userRepository.save(nUser);
    }

	@Override
	public User changeRoles(User user) {
		Optional<User> userOpt = this.userRepository.findById(user.getId());
		
		User userDB = userOpt.get();
		
		Set<Role> newRoleSet = new HashSet<>();
		Set<Role> newRoles = user.getRoles();
		
		for(Role role : newRoles) {
			Role newRole = this.roleService.findByName(role.getName());
			newRoleSet.add(newRole);
		}
		
		userDB.setRoles(newRoleSet);
		
		return this.userRepository.save(userDB);
	}
}