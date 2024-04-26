/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvk.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dvk.formatters.CategoryFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author DELL
 */
@Configuration // Biến thành rỗ - IoC Container - Nơi sản xuất đậu
@EnableWebMvc // Hiện thực vài MVC
@EnableTransactionManagement // Bật giao tác
@ComponentScan(basePackages = { // Chỉ định những nơi scan Annotation (Controller, Services...)
    "com.dvk.controllers",
    "com.dvk.repositories",
    "com.dvk.services"})
public class WebAppContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource m = new ResourceBundleMessageSource();
        m.setBasename("messages");//File message
        return m;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean
                = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());//Truyền file để đọc
        return bean;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() { //Để lấy file
        CommonsMultipartResolver resolver
                = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    //Tạo bean ViewResolver - Để phân giải và tìm view từ controller theo tên
    @Bean // Tương ứng thẻ <Bean>
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver r = new InternalResourceViewResolver();
        r.setViewClass(JstlView.class); //View Engine
        r.setPrefix("/WEB-INF/pages/"); // Vị trí hứa các views
        r.setSuffix(".jsp");
        return r;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new CategoryFormatter());
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dfs6qhtdp",
                        "api_key", "783287636326811",
                        "api_secret", "I5yF-_c9P7bfo4rLhwoolpZ6kUE",
                        "secure", true));
        return cloudinary;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
    }

}
