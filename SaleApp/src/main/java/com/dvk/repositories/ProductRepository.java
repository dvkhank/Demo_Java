/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvk.repositories;

import com.dvk.pojo.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface ProductRepository {
    List<Product> getProducts(Map<String, String> params);
    void addOrUpdate(Product p);
    Product getProductById(int id);
    public void deleteProduct(int id);
}
