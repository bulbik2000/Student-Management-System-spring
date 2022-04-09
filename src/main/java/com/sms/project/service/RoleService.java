package com.sms.project.service;


import com.sms.project.entity.Role;

public interface RoleService {
    Role findByName(String name);
    Iterable<Role> getRoles();
}
