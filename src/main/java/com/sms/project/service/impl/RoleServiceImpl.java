package com.sms.project.service.impl;

import org.springframework.stereotype.Service;

import com.sms.project.entity.Role;
import com.sms.project.repository.RoleRepository;
import com.sms.project.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    
    public RoleServiceImpl(
    		RoleRepository roleRepository) {
    	this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        Role role = roleRepository.findRoleByName(name);
        return role;
    }

	@Override
	public Iterable<Role> getRoles() {
		return this.roleRepository.findAll();
	}
}
