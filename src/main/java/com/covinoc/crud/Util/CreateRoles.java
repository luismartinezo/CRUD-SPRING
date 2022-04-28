/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.covinoc.crud.Security.Entity.Role;
import com.covinoc.crud.Security.Enums.RoleName;
import com.covinoc.crud.Security.Service.RoleService;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 * 
 *       MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS
 *       ROLES. UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 */
public class CreateRoles implements CommandLineRunner{

	@Autowired
	RoleService roleService;

	@Override
	public void run(String... args) throws Exception {
		
		 Role roleAdmin = new Role(RoleName.ROLE_ADMIN); Role roleUser = new
		 Role(RoleName.ROLE_USER); roleService.save(roleAdmin);
		 roleService.save(roleUser);
		 

	}
}
