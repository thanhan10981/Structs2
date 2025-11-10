package com.service;

import com.dao.ProductDAO;
import com.model.Product;

import java.util.List;


public class ProductService {
    private ProductDAO dao = new ProductDAO();

    public List<Product> getProducts(String lang) {
        return dao.getProducts(lang);
    }
}
