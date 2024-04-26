/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dvk.repositories.impl;

import com.dvk.pojo.Product;
import com.dvk.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    
    @Autowired
    private Environment env;

    @Override
    public List<Product> getProducts(Map<String, String> params) {
        Session s = factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();//Muốn lấy điều kiện
        CriteriaQuery<Product> q = b.createQuery(Product.class);
        Root r = q.from(Product.class);//Muốn lấy trường ( column)
        q = q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("name"), String.format("%%%s%%", kw)));
        }
        String fromPrice = params.get("fromPrice");
        if (fromPrice != null && !fromPrice.isEmpty()) {
            predicates.add(b.greaterThanOrEqualTo(r.get("price"), Double.parseDouble(fromPrice)));
        }

        String toPrice = params.get("toPrice");
        if (toPrice != null && !toPrice.isEmpty()) {
            predicates.add(b.lessThanOrEqualTo(r.get("price"), Double.parseDouble(toPrice)));
        }

        String cateId = params.get("cateId");
        if (cateId != null && !cateId.isEmpty()) {
            predicates.add(b.equal(r.get("categoryId"), Integer.parseInt(cateId)));
        }

        q = q.where(predicates.toArray(Predicate[]::new));
        q = q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("products.pageSize").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);//Bắt  đầu lấy từ đâu
            query.setMaxResults(6);//Lấy bao nhiêu
        }

        List<Product> kq = query.getResultList();
        return kq;
//            Query query  = s.createQuery("From Product");//HQL Query
//            List<Product> products = query.getResultList();
//            return products;

    }

    @Override
    public void addOrUpdate(Product p) {
        Session s = factoryBean.getObject().getCurrentSession();
        s.saveOrUpdate(p);
    }

    @Override
    public Product getProductById(int id) {
    Session s = factoryBean.getObject().getCurrentSession();
    return s.get(Product.class, id);

    }
}
