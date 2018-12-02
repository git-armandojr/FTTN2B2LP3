package br.com.armandojr.catalog.controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.armandojr.catalog.model.Product;
import br.com.armandojr.catalog.dal.ProductDAL;
import br.com.armandojr.catalog.dal.ProductRepository;

@RestController
@RequestMapping(value = "/")
public class ProductController {

	private final Logger LOG = org.slf4j.LoggerFactory.getLogger(getClass());
	
	private final ProductRepository productRepository;
	
	private final ProductDAL productDAL;
	
	public ProductController(ProductRepository productRepository, ProductDAL productDAL) {
		this.productRepository = productRepository;
		this.productDAL = productDAL;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Product addNewProduct(@RequestBody Product product) {
		LOG.info("Saving product.");
		return productRepository.save(product);		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Product> getAllProducts(){
		LOG.info("Getting all products");
		return productRepository.findAll();
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public Object getProduct(@PathVariable String productId) {
		LOG.info("Getting product with ID: {}.", productId);
		return productRepository.findById(productId);
	}
	
	/*
	@RequestMapping(value = "/features/{productId}", method = RequestMethod.GET)
	public Object getAllProductFeatures(@PathVariable String productId) {
		Product product = productRepository.findById(productId);
		
		if (product != null) {
			return productDAL.getProductFeatures(productId);
		} else {
			return "Product not found.";
		}
	}
	*/
		
	@RequestMapping(value = "/features/{productId}", method = RequestMethod.GET)
	public Object getAllProductFeatures(@PathVariable String productId) {
		Object product = productRepository.findById(productId);
		
		if (product != null) {
			return productDAL.getAllProductFeatures(productId);
		} else {
			return "Product not found.";
		}
	}
	
	/*
	@RequestMapping(value = "/features/{productId}/{key}", method = RequestMethod.GET)
	public String getProductFeature(@PathVariable String productId, @PathVariable String key) {
		Product product = productRepository.findOne(productId);
		
		if (product != null) {
			return product.getProductFeatures().get(key);
		} else {
			return "Product not found.";
		}
	}
	*/
	
	@RequestMapping(value = "/features/{productId}/{key}", method = RequestMethod.GET)
	public String getProductFeature(@PathVariable String productId, @PathVariable String key) {
		return productDAL.getProductFeature(productId, key);
	}
	
	
	@RequestMapping(value = "/features/{productId}/{key}/{value}", method = RequestMethod.PUT)
	public String addProductFeature(@PathVariable String productId, @PathVariable String key, @PathVariable String value) {
		Optional<Product> product = productRepository.findById(productId);
		
		if (product != null) {
			productDAL.addProductFeature(productId, key, value);
			return "Key added.";
		} else {
			return "Product not found.";
		}
	}
}
