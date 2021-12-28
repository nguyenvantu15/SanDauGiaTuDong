<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="product" scope="request" type="com.ute.sandaugiatudong.beans.Product"/>
<jsp:useBean id="historyAuction" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.HistoryAuction>"/>
<%--<jsp:useBean id="listUser" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.User>"/>--%>


<t:main>
    <jsp:attribute name="css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap');

            * {
                padding: 0;
                margin: 0;
                box-sizing: border-box;
            }

            html, body {
                font-family: 'Roboto', sans-serif;
                background-color: #1a233a;
            }

            strong {
                font-size: 1.3rem;
            }

            img {
                width: 100%;
                display: block;
            }

            .container {
                max-width: 1200px;
                padding: 0 1rem;
                margin: 0 auto;
            }

            .product-div {
                margin: 1rem 0;
                padding: 2rem 0;
                display: grid;
                grid-template-columns: repeat(2, 1fr);
                background-color: #ffffff;
                border-radius: 3px;
                column-gap: 10px;
            }

            .product-div-left {
                padding: 20px;
            }

            .product-div-right {
                padding: 20px;
            }

            .img-container {
                height: 400px;
            }

            .img-container img {
                width: 400px;
                margin: 0 auto;
                border-radius: 5px;
            }

            .hover-container {
                display: flex;
                flex-wrap: wrap;
                align-items: center;
                justify-content: center;
                margin-top: 32px;
            }

            .hover-container div {
                border: 2px solid rgba(252, 160, 175, 0.7);
                padding: 1rem;
                border-radius: 3px;
                margin: 0 4px 8px 4px;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .active {
                border-color: rgb(255, 145, 163) !important;
            }

            .hover-container div:hover {
                border-color: rgb(255, 145, 163);
            }

            .hover-container div img {
                width: 50px;
                cursor: pointer;
            }

            .product-div-right span {
                display: block;
            }

            .product-name {
                font-size: 26px;
                margin-bottom: 22px;
                font-weight: 700;
                letter-spacing: 1px;
                opacity: 0.9;
            }

            .product-price {
                font-weight: 700;
                font-size: 24px;
                opacity: 0.9;
                font-weight: 500;
            }

            .product-rating {
                display: flex;
                align-items: center;
                margin-top: 12px;
            }

            .product-rating span {
                margin-right: 6px;
            }

            .product-description {
                line-height: 1.6;
                font-weight: 300;
                opacity: 0.9;
                margin-top: 22px;
            }

            .btn-groups {
                margin-top: 22px;
            }

            .btn-groups button {
                display: inline-block;
                font-size: 16px;
                font-family: inherit;
                text-transform: uppercase;
                padding: 15px 16px;
                color: #fff;
                cursor: pointer;
                transition: all 0.3s ease;
            }

            .btn-groups button .fas {
                margin-right: 8px;
            }

            .add-cart-btn {
                background-color: #FF9F00;
                border: 2px solid #FF9F00;
                margin-right: 8px;
            }

            .add-cart-btn:hover {
                background-color: #fff;
                color: #FF9F00;
            }

            .buy-now-btn {
                background-color: #000;
                border: 2px solid #000;
            }

            .buy-now-btn:hover {
                background-color: #fff;
                color: #000;
            }

            @media screen and (max-width: 992px) {
                .product-div {
                    grid-template-columns: 100%;
                }

                .product-div-right {
                    text-align: center;
                }

                .product-rating {
                    justify-content: center;
                }

                .product-description {
                    max-width: 400px;
                    margin-right: auto;
                    margin-left: auto;
                }
            }

            @media screen and (max-width: 400px) {
                .btn-groups button {
                    width: 100%;
                    margin-bottom: 10px;
                }
            }

            /*    san pham cung loai*/
            .row {
                margin-left: -6px;
                margin-right: -6px;
                width: 1200px;
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

            ul {
                padding-left: 18px;
            }

        </style>
    </jsp:attribute>
    <jsp:attribute name="js">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <script>

            const allHoverImages = document.querySelectorAll('.hover-container div img');
            const imgContainer = document.querySelector('.img-container');

            window.addEventListener('DOMContentLoaded', () => {
                allHoverImages[0].parentElement.classList.add('active');
            });

            allHoverImages.forEach((image) => {
                image.addEventListener('mouseover', () => {
                    imgContainer.querySelector('img').src = image.src;
                    resetActiveImg();
                    image.parentElement.classList.add('active');
                });
            });

            function resetActiveImg() {
                allHoverImages.forEach((img) => {
                    img.parentElement.classList.remove('active');
                });
            }
        </script>
    </jsp:attribute>

    <jsp:body>

        <div class="container">
            <div class="product-div">
                <div class="product-div-left">
                    <div class="img-container">
                        <img src="${pageContext.request.contextPath}/public/imgs/${product.id}/1.jpg" alt="watch">
                    </div>
                    <div class="hover-container">
                        <div><img src="${pageContext.request.contextPath}/public/imgs/${product.id}/1.jpg"></div>
                        <div><img src="${pageContext.request.contextPath}/public/imgs/${product.id}/2.jpg"></div>
                        <div><img src="${pageContext.request.contextPath}/public/imgs/${product.id}/3.jpg"></div>
                        <div><img src="${pageContext.request.contextPath}/public/imgs/${product.id}/4.jpg"></div>
                    </div>
                </div>
                <div class="product-div-right">
                    <h3>Lịch sử đấu giá: </h3>
                    <span class="product-name">${product.name}</span>
                    <span class="product-price text-danger">Giá hiện tại: <fmt:formatNumber value="${product.price}"
                                                                                            type="number"/> vnd</span>
                    <div>Thời điểm đăng
                        <input type="datetime-local" class="form-control" value="${product.timeStart}" disabled
                               name="timeStart">
                    </div>
                    <div class="mt-2">Thời điểm kết thúc
                        <input type="datetime-local" class="form-control" value="${product.timeEnd}" disabled
                               name="timeStart">
                    </div>
                    <div class="mt-3"><strong>Lịch sử đấu giá</strong></div>
                    <div>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Giá vào</th>
                                <th>Người giữ giá</th>
                                <th>Thời gian</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${historyAuction}" var="c">
                                <tr>
                                    <td style="width: 172px;"><fmt:formatNumber value="${c.priceIn}" type="number"/></td>
                                    <td>#${c.bidderCur}</td>
                                    <td class="">${c.time}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="btn-groups">
                        <c:if test="${auth == 1}">
                            <a class="btn btn-danger"
                               href="${pageContext.request.contextPath}/Behavior/Auction?id=${product.id}"
                               role="button">
                                <i class="fa fa-eraser" aria-hidden="true"></i>
                                xóa người đang giữ giá
                            </a>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:main>
