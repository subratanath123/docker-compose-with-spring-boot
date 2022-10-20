package com.example.FirstDockerappweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author jahangir
 * @author subrata
 * @author mahfuz.ahmed
 * @since 07/18/2022
 */
@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(path = "/product/{id}")
    public Product get(@PathVariable long id) {
        LOGGER.info("[Product fetch] id={}", id);
        return getDummyProduct(id);
    }

    public Product getDummyProduct(long id) {
        LOGGER.info("[Product fetch] getting dummy product", id);
        return new Product(id, (int) (id * 100), "Product-" + id);
    }

}