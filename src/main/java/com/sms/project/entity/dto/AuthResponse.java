package com.sms.project.entity.dto;

import java.util.Set;

import com.sms.project.entity.Role;
import com.sms.project.entity.User;

public class AuthResponse {
	private long id;
	private String username;
    private String _token;
    private long expiresIn;
    private Set<Role> userRoles;

    public AuthResponse(){

    }

    public AuthResponse(String _token,
    					long _tokenExpDate,
    					User user){
        this._token = _token;
        this.expiresIn = _tokenExpDate;
        this.id = user.getId();
        this.username = user.getUsername();
        this.userRoles = user.getRoles();
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String get_token() {
		return _token;
	}

	public void set_token(String _token) {
		this._token = _token;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Set<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<Role> userRoles) {
		this.userRoles = userRoles;
	}

}
