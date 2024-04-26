<%-- 
    Document   : products
    Created on : Apr 20, 2024, 4:07:31 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<h1 class="text-center text-info mt-1">QUẢN LÍ SẢN PHẨM</h1>
<c:url value="/products" var="action" />
<form:form action="${action}" method="post" modelAttribute="product" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert-danger" />
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="name" placeholder="Nhập tên sản phẩm" path="name"/>
        <label for="name">Tên sản phẩm</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="price" placeholder="Nhập tên sản phẩm" path="price"/>
        <label for="price">Giá sản phẩm</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" id="image" path="file"/>
        <label for="image">Ảnh sản phẩm</label>
        <c:if test="${product.id > 0}">
            <img src="${product.image}" width="200" class="img-fluid">
        </c:if>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="categoryId" path="categoryId">
            <c:forEach items="${categories}" var ="c">

                <c:choose>
                    <c:when test="${c.id==product.categoryId.id}">
                        <option selected value="${c.id}">${c.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.name}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>

        </form:select>
        <label for="categoryId" class="form-label">Danh mục:</label>
    </div>
    <div class="form-floating">
        <button type="submit" class="btn btn-info mt-1">
            <c:choose>
                <c:when test="${product.id > 0}">
                    Cập nhật
                </c:when>
                <c:otherwise>
                    Thêm
                </c:otherwise>
            </c:choose>
        </button>
    </div>
    <form:hidden path="id" />
</form:form>