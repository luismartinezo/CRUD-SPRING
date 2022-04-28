/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Security.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covinoc.crud.Security.Entity.User;
import com.covinoc.crud.Security.Repository.UserRepository;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@Service
@Transactional
public class UserService {

	@Autowired
    UserRepository userRepository;

	public List<User> list() {
		return userRepository.findAll();
	}
	
	public Optional<User> getOne(int id){
        return userRepository.findById(id);
    }
	
	public void delete(int id){
		userRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return userRepository.existsById(id);
    }
    
    public Optional<User> getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public boolean existsByUserName(String nombreUsuario){
        return userRepository.existsByUserName(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user){
    	userRepository.save(user);
    }
}
