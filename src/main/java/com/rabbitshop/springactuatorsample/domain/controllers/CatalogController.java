package com.rabbitshop.springactuatorsample.domain.controllers;

import com.rabbitshop.springactuatorsample.domain.entities.Product;
import com.rabbitshop.springactuatorsample.domain.services.ProductService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@RestController
@RequestMapping(value = "/catalog")
public class CatalogController {

	@Autowired
	ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {

		log.debug("Catalog - get all products");

		return getProductService().getAllProducts();
	}

}
