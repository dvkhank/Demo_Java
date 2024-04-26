/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvk.controllers;

import com.dvk.services.CategoryService;
import com.dvk.services.ProductService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DELL
 */
@ControllerAdvice//Dùng để dùng chung Model cho các Controller khác (Hàm có annotation @ModelAttribute)
@Controller
public class IndexController {

    @Autowired
    private CategoryService cateService;
    @Autowired
    private ProductService prodService;

    @RequestMapping("/")
    public String index(Model model,
            @RequestParam Map<String, String> params) {
        model.addAttribute("products", this.prodService.getProducts(params));
        return "index"; // Chưa biết index là template -> Cấu hình rỗ đậu, cấu hình DispatcherSerplet
    }

    @ModelAttribute //Tất cả các reponse trong Controller này đều có thông tin này
    public void commonAttribute(Model model) {
        model.addAttribute("categories", this.cateService.getCates());
    }
}
