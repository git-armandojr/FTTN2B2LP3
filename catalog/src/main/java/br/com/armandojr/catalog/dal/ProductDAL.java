package br.com.armandojr.catalog.dal;

import java.util.List;

import br.com.armandojr.catalog.model.Product;

public interface ProductDAL {
	
	List<Product> getAllUsers();
	
	Product getProductById(String productId);
	
	Product addNewProduct(Product product);
	
	Object getAllProductFeatures(String productId);
	
	String getProductFeature(String productId, String key);
	
	String addProductFeature(String productId, String key, String value);
	
	String delProductById(String product);
	
}
