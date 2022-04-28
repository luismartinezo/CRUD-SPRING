/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Security.Controller;

import java.util.*;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.covinoc.crud.Security.Dto.JwtDto;
import com.covinoc.crud.Security.Dto.LoginDto;
import com.covinoc.crud.Security.Dto.Mensaje;
import com.covinoc.crud.Security.Dto.NewUserDto;
import com.covinoc.crud.Security.Entity.Role;
import com.covinoc.crud.Security.Entity.User;
import com.covinoc.crud.Security.Enums.RoleName;
import com.covinoc.crud.Security.Jwt.JwtProvider;
import com.covinoc.crud.Security.Service.RoleService;
import com.covinoc.crud.Security.Service.UserService;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService rolService;

	@Autowired
	JwtProvider jwtProvider;
	
	@GetMapping("/list")
	public ResponseEntity<List<User>> list() {
		List<User> list = userService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") int id) {
		if (!userService.existsById(id))
			return new ResponseEntity(new Mensaje("Does not exist"), HttpStatus.NOT_FOUND);
		User user = userService.getOne(id).get();
		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NewUserDto newUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("wrong fields or invalid email"), HttpStatus.BAD_REQUEST);
		if (userService.existsByUserName(newUser.getUserName()))
			return new ResponseEntity(new Mensaje("that name already exists"), HttpStatus.BAD_REQUEST);
		if (userService.existsByEmail(newUser.getEmail()))
			return new ResponseEntity(new Mensaje("that email already exists"), HttpStatus.BAD_REQUEST);
		User user = new User(newUser.getName(), newUser.getUserName(),
				newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(rolService.getByRoleName(RoleName.ROLE_USER).get());
		if (newUser.getRoles().contains("admin"))
			roles.add(rolService.getByRoleName(RoleName.ROLE_ADMIN).get());
		user.setRoles(roles);
		userService.save(user);
		return new ResponseEntity(new Mensaje("user saved"), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody NewUserDto userDto) {
		if (!userService.existsById(id))
			return new ResponseEntity(new Mensaje("Does not exist"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(userDto.getName()))
			return new ResponseEntity(new Mensaje("User is required"), HttpStatus.BAD_REQUEST);

		User user = userService.getOne(id).get();
		userService.save(user);
		return new ResponseEntity(new Mensaje("User is updated"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!userService.existsById(id))
			return new ResponseEntity(new Mensaje("Does not exist"), HttpStatus.NOT_FOUND);
		userService.delete(id);
		return new ResponseEntity(new Mensaje("User is deleted"), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginDto loginUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}
}
