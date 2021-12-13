<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="sanpham" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.sanpham>"/>

<t:main>
    <jsp:attribute name="css">
        <style>
            .col-item{
                width: 20%;
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <div class="row m-0">
            <div class="col-sm-auto col-item p-0">
                <div class="card h-100">
                    <img src="${pageContext.request.contextPath}/public/imgs/1/1.jpg" class="card-img-top">
                    <div class="card-body">
                        <h6 class="card-title">abc</h6>
                        <h5 class="card-title text-danger">10000</h5>
                        <p class="card-text">kkkk</p>
                    </div>
                    <div class="card-footer text-muted">
                        <a class="btn btn-sm btn-outline-primary" href=""
                           role="button">
                            <i class="fa fa-eye" aria-hidden="true"></i>
                            Detail
                        </a>
                        <a class="btn btn-outline-success" href="#" role="button">
                            <i class="fa fa-cart-plus" aria-hidden="true"></i>
                            Add to cart
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:main>