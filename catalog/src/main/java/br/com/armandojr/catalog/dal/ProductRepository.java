package br.com.armandojr.catalog.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

//import java.util.List;

//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.CrudRepository;

import br.com.armandojr.catalog.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>, CrudRepository<Product, String>, QueryByExampleExecutor<Product>{

}
