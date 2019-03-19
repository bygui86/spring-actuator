package com.rabbit.samples.actuator.domain.services.impl;

import com.google.common.collect.Lists;
import com.rabbit.samples.actuator.domain.data.Product;
import com.rabbit.samples.actuator.domain.repos.ProductRepository;
import com.rabbit.samples.actuator.domain.services.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Service
public class ProductServiceImpl implements ProductService {

	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {

		return Lists.newArrayList(getProductRepository().findAll());
	}

}
