/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Controller;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.covinoc.crud.Dto.ProductDto;
import com.covinoc.crud.Entity.Product;
import com.covinoc.crud.Security.Dto.Mensaje;
import com.covinoc.crud.Service.ProductService;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "*")
public class ProductController {

	   @Autowired
	    ProductService productService;

	    @GetMapping("/list")
	    public ResponseEntity<List<Product>> list(){
	        List<Product> list = productService.list();
	        return new ResponseEntity(list, HttpStatus.OK);
	    }

	    @GetMapping("/detail/{id}")
	    public ResponseEntity<Product> getById(@PathVariable("id") int id){
	        if(!productService.existsById(id))
	            return new ResponseEntity(new Mensaje("not exist"), HttpStatus.NOT_FOUND);
	        Product product = productService.getOne(id).get();
	        return new ResponseEntity(product, HttpStatus.OK);
	    }

	    @GetMapping("/detailname/{name}")
	    public ResponseEntity<Product> getByName(@PathVariable("name") String name){
	        if(!productService.existsByName(name))
	            return new ResponseEntity(new Mensaje("not exist"), HttpStatus.NOT_FOUND);
	        Product product = productService.getByName(name).get();
	        return new ResponseEntity(product, HttpStatus.OK);
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @PostMapping("/create")
	    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
	        if(StringUtils.isBlank(productDto.getName()))
	            return new ResponseEntity(new Mensaje("The name is required"), HttpStatus.BAD_REQUEST);
	        if(productDto.getPrice()<=0 )
	            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
	        if(productService.existsByName(productDto.getName()))
	            return new ResponseEntity(new Mensaje("That name is already exists"), HttpStatus.BAD_REQUEST);
	        Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getDescription());
	        productService.save(product);
	        return new ResponseEntity(new Mensaje("Product Created"), HttpStatus.OK);
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductDto productDto){
	        if(!productService.existsById(id))
	            return new ResponseEntity(new Mensaje("not exist"), HttpStatus.NOT_FOUND);
	        if(productService.existsByName(productDto.getName()) && productService.getByName(productDto.getName()).get().getId() != id)
	            return new ResponseEntity(new Mensaje("That name is already exists"), HttpStatus.BAD_REQUEST);
	        if(StringUtils.isBlank(productDto.getName()))
	            return new ResponseEntity(new Mensaje("The name is required"), HttpStatus.BAD_REQUEST);
	        if(productDto.getPrice()<=0 )
	            return new ResponseEntity(new Mensaje("The price must be greater than 0"), HttpStatus.BAD_REQUEST);

	        Product product = productService.getOne(id).get();
	        product.setName(productDto.getName());
	        product.setPrice(productDto.getPrice());
	        productService.save(product);
	        return new ResponseEntity(new Mensaje("Product Updated"), HttpStatus.OK);
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> delete(@PathVariable("id")int id){
	        if(!productService.existsById(id))
	            return new ResponseEntity(new Mensaje("not exist"), HttpStatus.NOT_FOUND);
	        productService.delete(id);
	        return new ResponseEntity(new Mensaje("Product Deleted"), HttpStatus.OK);
	    }
}
