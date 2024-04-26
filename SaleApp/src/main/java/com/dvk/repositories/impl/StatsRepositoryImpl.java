///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.dvk.repositories.impl;
//
//import com.dvk.demo.HibernateUtils;
//import com.dvk.pojo.OrderDetail;
//import com.dvk.pojo.Product;
//import com.dvk.pojo.SaleOrder;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import org.hibernate.Session;
//
///**
// *
// * @author DELL
// */
//public class StatsRepositoryImpl {
//    
//    public List<Object[]> statsRevenueByProduct() {
//        try ( Session s = HibernateUtils.getFactory().openSession()) {
//            CriteriaBuilder b = s.getCriteriaBuilder();//Muốn lấy điều kiện
//            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//            Root rP = q.from(Product.class);//Muốn lấy trường ( column)
//            Root rD = q.from(OrderDetail.class);
//            
//            q.multiselect(rP.get("id"), rP.get("name"), b.sum(b.prod(rD.get("quantity"),
//                    rD.get("unitPrice"))));
//            
//            List<Predicate> predicates = new ArrayList<>();
//            predicates.add(b.equal(rP.get("categoryId"), rD.get("id")));
//            q = q.where(predicates.toArray(Predicate[]::new));
//            q.groupBy(rP.get("id"));
//            
//            Query query = s.createQuery(q);
//            return query.getResultList();
//            
//        }
//    }
//    
//    public List<Object[]> statsRevenueByMonth(int year) {
//        try ( Session s = HibernateUtils.getFactory().openSession()) {
//            CriteriaBuilder b = s.getCriteriaBuilder();//Muốn lấy điều kiện
//            CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
////            Root rP = q.from(Product.class);//Muốn lấy trường ( column)
//            Root rO = q.from(SaleOrder.class);
//            Root rD = q.from(OrderDetail.class);
//            q.multiselect(b.function("MONTH", Integer.class, rO.get("createdDate")), b.sum(b.prod(rD.get("quantity"),
//                    rD.get("unitPrice"))));
//            
//            List<Predicate> predicates = new ArrayList<>();
//            predicates.add(b.equal(rD.get("orderId"), rO.get("id")));
//            predicates.add(b.equal(b.function("YEAR", Integer.class, rO.get("createdDate")),year));
//            q = q.where(predicates.toArray(Predicate[]::new));
//            q.groupBy(b.function("MONTH", Integer.class, rO.get("createdDate")));
//            
//            Query query = s.createQuery(q);
//            return query.getResultList();
//            
//        }
//    }
//}
