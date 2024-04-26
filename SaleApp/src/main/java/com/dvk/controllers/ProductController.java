/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvk.controllers;

import com.dvk.pojo.Product;
import com.dvk.services.ProductService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author DELL
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService prodService;
    @GetMapping("/products")
    public String createView(Model model) {
        model.addAttribute("product", new Product());
        return "products";
    }
    @PostMapping("/products") //ModelAttribute có 2 công dụng : 1 là dùng chung, 2 là trả lại đối tượng là backing bean      //Serialize : Chuyển đối tượng thành JSON , Deserialize: Chuyển JSON (dữ liệu) thành đối tượng
    public String createProduct(@ModelAttribute (value="product") @Valid Product p,
            BindingResult rs) { //Client gửi post lên sẽ đóng gói lại thành đối tượng Product P sau ( Quá trình Deserilize
        if(!rs.hasErrors()) {
            try {
                this.prodService.addOrUpdate(p);
                return "redirect:/";
            } catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
            
        
        return "products";
    }
    @GetMapping("/products/{productId}")
    public String updateView(Model model,@PathVariable(value = "productId") int id) { // Đỗ 1 đối tượng Backing Bean có dữ liệu
        model.addAttribute("product", this.prodService.getProductById(id));
        return "products";
    }
}
