/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Security.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covinoc.crud.Security.Entity.Role;
import com.covinoc.crud.Security.Enums.RoleName;
import com.covinoc.crud.Security.Repository.RoleRepository;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@Service
@Transactional
public class RoleService {
	@Autowired
	RoleRepository roleRepository;

	public Optional<Role> getByRoleName(RoleName roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	public void save(Role role) {
		roleRepository.save(role);
	}
}
