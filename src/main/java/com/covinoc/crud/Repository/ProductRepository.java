/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covinoc.crud.Entity.Product;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	Optional<Product> findByName(String userName);
    boolean existsByName(String userName);
}
