package br.com.armandojr.catalog.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.armandojr.catalog.model.Product;

@Repository
public class ProductDALImpl implements ProductDAL{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Product> getAllUsers() {
		return mongoTemplate.findAll(Product.class);
	}

	@Override
	public Product getProductById(String productId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(productId));
		return mongoTemplate.findOne(query, Product.class);
	}

	@Override
	public Product addNewProduct(Product product) {
		mongoTemplate.save(product);
		return product;
	}

	@Override
	public Object getAllProductFeatures(String productId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(productId));
		Product product = mongoTemplate.findOne(query, Product.class);
		return product != null ? product.getProductFeatures() : "Product not found.";
	}

	@Override
	public String getProductFeature(String productId, String key) {
		Query query = new Query();
		query.fields().include("productFeatures");
		query.addCriteria(Criteria.where("productId").is(productId).andOperator(Criteria.where("productFeatures." + key).exists(true)));
		Product product = mongoTemplate.findOne(query, Product.class);	
		return product != null ? product.getProductFeatures().get(key) : "Not found."; 
	}

	@Override
	public String addProductFeature(String productId, String key, String value) {
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(productId));
		Product product = mongoTemplate.findOne(query,  Product.class);
		if (product != null) {
			product.getProductFeatures().put(key, value);
			mongoTemplate.save(product);
			return "Key added.";
		} else {
			return "Product not found.";
		}
	}

	@Override
	public String delProductById(String productId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(productId));
		Product product = mongoTemplate.findOne(query, Product.class);
		
		if (product != null) {
			mongoTemplate.remove(product);
			return "Product removed.";
		} else {
			return "Product not found.";
		}
	}
}
