/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvk.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author DELL
 */
// TỔNG ĐẦU NÃO HỆ THỐNG - NƠI NHẬN REQUEST - XỬ LÝ TẤT CẢ
public class DispatcherServeletInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() { // Rỗ đậu nào mà không kế thừa nào cã
        return new Class[] {
            HibernateConfig.class,
                TilesConfig.class
                
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { //Rỗ đậu có kế thừa ( implement ) Configurer
        return new Class[]{
            WebAppContextConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
