/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Security.Dto;

import java.util.*;

import javax.validation.constraints.*;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
public class NewUserDto {

	@NotBlank
	private String name;
	@NotBlank
	private String userName;
	@Email
	private String email;
	@NotBlank
	private String password;
	private Set<String> roles = new HashSet<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
