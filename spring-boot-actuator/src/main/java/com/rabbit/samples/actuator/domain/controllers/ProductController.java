package com.rabbit.samples.actuator.domain.controllers;

import com.rabbit.samples.actuator.domain.data.Product;
import com.rabbit.samples.actuator.domain.services.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@RestController
@RequestMapping(value = "/products")
public class ProductController {

	ProductService productService;

	@GetMapping
	public List<Product> getAll() {

		log.debug("Get all products");

		return getProductService().getAllProducts();
	}

}
