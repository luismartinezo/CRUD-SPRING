/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.covinoc.crud.Entity.Product;
import com.covinoc.crud.Repository.ProductRepository;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@Service
@Transactional
public class ProductService {

	@Autowired
    ProductRepository productRepository;

	public List<Product> list() {
		return productRepository.findAll();
	}
	
	public Optional<Product> getOne(int id){
        return productRepository.findById(id);
    }
	
	public void delete(int id){
		productRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return productRepository.existsById(id);
    }
    
    public Optional<Product> getByName(String userName){
        return productRepository.findByName(userName);
    }

    public boolean existsByName(String nombreUsuario){
        return productRepository.existsByName(nombreUsuario);
    }

    public void save(Product product){
    	productRepository.save(product);
    }
}
