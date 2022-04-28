/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Security.Dto;

import javax.validation.constraints.NotBlank;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
public class LoginDto {
	@NotBlank
	private String userName;
	@NotBlank
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
