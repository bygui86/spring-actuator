package com.rabbit.samples.actuator.domain.services;

import com.rabbit.samples.actuator.domain.data.Product;

import java.util.List;


public interface ProductService {

	List<Product> getAllProducts();

}
