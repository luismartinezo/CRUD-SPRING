/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@Entity
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private float price;
    @NotNull
    private String description;
    
	public Product() {
	}

	/**
	 * @param name
	 * @param price
	 * @param description
	 */
	public Product(@NotNull String name, @NotNull float price, @NotNull String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
