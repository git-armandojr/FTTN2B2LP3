package br.com.armandojr.catalog.model;

import java.util.HashMap;
import java.util.Map;

//import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
	
	@Id
	String productId;
	String name;
	String description;
	Map<String, String> productFeatures = new HashMap<>();

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}			
	
	public Product(String productId, String name, String description, HashMap<String, String> productFeatures) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.productFeatures = productFeatures;
	}
		
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Map<String, String> getProductFeatures() {
		return productFeatures;
	}

	public void setProductFeatures(Map<String, String> productFeatures) {
		this.productFeatures = productFeatures;
	}
}
