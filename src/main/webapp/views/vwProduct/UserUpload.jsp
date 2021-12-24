<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="ProductByUser" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Product>"/>

<t:main>
    <jsp:attribute name="css">
        <style>
            body {
                background: #e5e9f3;
            }

            .row {
                margin-left: -6px;
                margin-right: -6px;
            }

            .col-item {
                width: 19%;
                margin-left: 6px;
                margin-right: 6px;
                margin-bottom: 12px;
            }

            .card-img-top {
                height: 30vh;
                object-fit: contain;
            }

            .card_hover:hover {
                transform: translateY(-1px);
                box-shadow: 0 2px 10px 4px rgba(0, 0, 0, 0.5);;
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <div>
            <h4>Sản phẩm bạn đã đăng</h4>
        </div>
        <div class="row">
            <c:forEach items="${ProductByUser}" var="c">
                <div class="col-sm-auto col-item p-0 card_hover">
                    <div class="card h-100">
                        <img src="${pageContext.request.contextPath}/public/imgs/${c.id}/1.jpg" alt="${c.name}"
                             title="${c.name}" class="card-img-top">
                        <div class="card-body">
                            <h6 class="card-title">${c.name}</h6>
                            <h5 class="card-title text-danger">
                                <fmt:formatNumber value="${c.price}" type="number"/>
                            </h5>
                            <p class="card-text">kkkk</p>
                        </div>
                        <div class="card-footer text-muted">
                            <a class="btn btn-sm btn-outline-primary"
                               href="${pageContext.request.contextPath}/Misc/Edit?id=${c.id}"
                               role="button">
                                <i class="fa fa-pencil" aria-hidden="true"></i>
                                Detail and Edit
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </jsp:body>
</t:main>