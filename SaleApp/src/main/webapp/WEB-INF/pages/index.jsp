<%-- 
    Document   : index
    Created on : Apr 16, 2024, 9:01:59 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center text-info mt-1">QUẢN TRỊ SẢN PHẨM</h1>

<div>
    <a class="btn btn-info mt-1" href="<c:url value="/products" />">Thêm sản phẩm</a>
</div>
<table class="table table-striped">
    <tr>
        <th></th>
        <th>Id</th>
        <th>Tên</th>
        <th>Giá</th>                           
        <th></th>
    </tr>
    <c:forEach items="${products}" var="p">
        <tr>
            <td><img class="card-img-top" src="${p.image}" alt="${p.name}" style="width:300px"  ></td>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${String.format("%,d", p.price)} VNĐ</td>
            <td>
                <c:url value="/products/${p.id}" var="url"/>
                <a class="btn btn-info" href="<c:url value="/products/${p.id}"/>">Cập nhật</a>
                <button onclick="deleteProduct('${url}',${p.id})" class="btn btn-danger">Xóa</button>
            </td>
        </tr>
    </c:forEach>
</table>
<script src="<c:url value="/js/script.js" /></script>
