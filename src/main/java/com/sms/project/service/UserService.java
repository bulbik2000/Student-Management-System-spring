package com.sms.project.service;



import java.util.List;

import com.sms.project.entity.Role;
import com.sms.project.entity.User;
import com.sms.project.entity.dto.UserDto;

public interface UserService {
	//UserDTO
    User save(UserDto user);
    User changeRoles(User user);
    List<User> findAll();
    User findOne(String username);
    public Iterable<Role> getRoles();
}
