/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Dto;

import javax.validation.constraints.*;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
public class ProductDto {

	@NotBlank
	private String name;
	@NotBlank
	private float price;
	
	private String description;
	
	
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
