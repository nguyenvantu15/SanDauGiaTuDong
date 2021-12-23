<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="top5Price" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Product>"/>
<t:main>
    <jsp:attribute name="css">
        <style>
            body{
                background: #e5e9f3;
            }
            .row{
                margin-left: -6px;
                margin-right: -6px;
            }
            .col-item{
                width: 19%;
                margin-left: 6px;
                margin-right: 6px;
                margin-bottom: 12px;
            }
            .card-img-top{
                height: 30vh;
                object-fit: contain;
            }
            .card_hover:hover{
                transform:translateY(-1px);
                box-shadow:  0 2px 10px 4px rgba(0,0,0,0.5);;
            }
        </style>
    </jsp:attribute>
    <jsp:body>
        <div>
            <h4>Top 5 sản phẩm giá cao nhất</h4>
        </div>
        <div class="row m-0">
            <c:forEach items="${top5Price}" var="c">
                <div class="col-sm-auto col-item p-0  card_hover">
                    <div class="card h-100">
                        <img src="${pageContext.request.contextPath}/public/imgs/${c.id}/1.jpg" alt="${c.name}" title="${c.name}" class="card-img-top">
                        <div class="card-body">
                            <h6 class="card-title">${c.name}</h6>
                            <h5 class="card-title text-danger">
                                <fmt:formatNumber value="${c.price}" type="number" />
                            </h5>
                            <p class="card-text">ID người bán: ${c.idUserSell} </p>
                            <p class="card-text">ID người đặt giá cao nhất: ${c.idUserCur}</p>
                        </div>
                        <div class="card-footer text-muted">
                            <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/Product/Detail?id=${c.id}&idType=${c.idType}"
                               role="button">
                                <i class="fa fa-eye" aria-hidden="true"></i>
                                Detail
                            </a>
                            <c:if test="${auth == 1}">
                                <a class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/Behavior/watchlist?id=${c.id}"
                                   role="button">
                                    <i class="fa fa-plus" aria-hidden="true"></i>
                                    Add
                                </a>
                                <a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/Behavior/Auction?id=${c.id}"
                                   role="button">
                                    <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                    Đấu giá
                                </a>
                            </c:if>

                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </jsp:body>
</t:main>
