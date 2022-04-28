/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Security.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.covinoc.crud.Security.Enums.RoleName;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleName roleName;
	
	
	/**
	 * 
	 */
	public Role() {
	}
	
	
	/**
	 * @param roleName
	 */
	public Role(@NotNull RoleName roleName) {
		super();
		this.roleName = roleName;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RoleName getRoleName() {
		return roleName;
	}
	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}
}
