package com.rabbit.samples.actuator.domain.repos;

import com.rabbit.samples.actuator.domain.data.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

	// no-op
}
