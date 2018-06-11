package com.rabbitshop.springactuatorsample.domain.services.impl;

import com.rabbitshop.springactuatorsample.domain.entities.Product;
import com.rabbitshop.springactuatorsample.domain.repos.ProductRepository;
import com.rabbitshop.springactuatorsample.domain.services.ProductService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {

		log.debug("Get all products");

		final Spliterator<Product> products = getProductRepository().findAll().spliterator();
		return StreamSupport.stream(products, false).collect(Collectors.toList());
	}

}
