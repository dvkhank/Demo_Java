/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvk.formatters;

import com.dvk.pojo.Category;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author DELL
 */
public class CategoryFormatter implements Formatter<Category> {

    @Override
    public String print(Category cate, Locale locale) { // Đẩy đối tượng lên web
        return String.valueOf(cate.getId());
    }

    @Override // Lấy đối tượng trên web về -> Chuyển vè đối tượng
    public Category parse(String id, Locale locale) throws ParseException {
        Category c = new Category();
        c.setId(Integer.parseInt(id));
        return c;
    }

}
