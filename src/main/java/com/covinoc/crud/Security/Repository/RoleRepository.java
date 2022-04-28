/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covinoc.crud.Security.Entity.Role;
import com.covinoc.crud.Security.Enums.RoleName;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByRoleName(RoleName roleName);
}
