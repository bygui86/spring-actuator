package com.rabbitshop.springactuatorsample.domain.repos;

import com.rabbitshop.springactuatorsample.domain.entities.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, String> {

	// no-op

}
