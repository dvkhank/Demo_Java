/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvk.services.impl;

import com.dvk.pojo.Category;
import com.dvk.repositories.CategoryRepository;
import com.dvk.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service //Đánh dấu để Component Scaning scan tạo bean
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository cateRepo;
    @Override
    public List<Category> getCates() { //Phụ thuộc cấp Interface
        return this.cateRepo.getCates();
    }
    
}
