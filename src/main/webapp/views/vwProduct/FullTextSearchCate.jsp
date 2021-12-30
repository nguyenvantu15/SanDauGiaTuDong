<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="listDateTimeEnd" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="listDateTimeStart" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="timenow" scope="request" type="com.ute.sandaugiatudong.beans.DateTimeNew"/>
<jsp:useBean id="listUser" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.User>"/>
<jsp:useBean id="listTimeUp" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.timeUpNMinute>"/>
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

            .rowHead {
                justify-content: space-between;
            }
        </style>

        <style>
            .clockdiv {
                font-family: sans-serif;
                color: #0a0202;
                display: inline-block;
            }

            .clockdiv > div {
                display: inline-block;
            }

            .clockdiv div > span {
                border-radius: 3px;
                display: inline-block;
            }

            .smalltext {
                padding-top: 0px;
                font-size: 16px;
            }

            .dateTimeText {
                color: red;
            }

            .selectT {
                height: 40px;
            }

            .selectT select, button {
                height: 100%;
            }
        </style>

        <style>
            .pagination{
                text-align: center;
                margin: 30px 30px 60px;
                user-select: none;
            }

            .pagination li{
                display: inline-block;
                margin: 5px;
                box-shadow: 0 5px 25px rgb(1 1 1 / 10%);
            }

            .pagination li a{
                color: #a82121;
                text-decoration: none;
                font-size: 1.2em;
                line-height: 45px;
            }

            .previous-page, .next-page{
                background: #0AB1CE;
                width: 80px;
                border-radius: 45px;
                cursor: pointer;
                transition: 0.3s ease;
            }

            .previous-page:hover{
                transform: translateX(-5px);
            }

            .next-page:hover{
                transform: translateX(5px);
            }

            .current-page, .dots{
                background: #ccc;
                width: 45px;
                border-radius: 50%;
                cursor: pointer;
            }

            .active{
                background: #0AB1CE;
            }

            .disable{
                background: #ccc;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="js">
        <script>
            function getTimeRemaining(endtime) {
                var t = Date.parse(endtime) - Date.parse(new Date());
                var seconds = Math.floor((t / 1000) % 60);
                var minutes = Math.floor((t / 1000 / 60) % 60);
                var hours = Math.floor((t / (1000 * 60 * 60)) % 24);
                var days = Math.floor(t / (1000 * 60 * 60 * 24));
                return {
                    'total': t,
                    'days': days,
                    'hours': hours,
                    'minutes': minutes,
                    'seconds': seconds
                };
            }

            function initializeClock(id, endtime) {
                var clock = document.getElementById(id);
                var daysSpan = clock.querySelector('.days');
                var hoursSpan = clock.querySelector('.hours');
                var minutesSpan = clock.querySelector('.minutes');
                var secondsSpan = clock.querySelector('.seconds');

                function updateClock() {
                    var t = getTimeRemaining(endtime);

                    daysSpan.innerHTML = t.days;
                    hoursSpan.innerHTML = ('0' + t.hours).slice(-2);
                    minutesSpan.innerHTML = ('0' + t.minutes).slice(-2);
                    secondsSpan.innerHTML = ('0' + t.seconds).slice(-2);

                    if (t.total <= 0) {
                        clearInterval(timeinterval);
                    }
                }

                updateClock();
                var timeinterval = setInterval(updateClock, 1000);

            }

            function aaa(id, endtime) {
                var clock = document.getElementById(id);
                var daysSpan = clock.querySelector('.days');
                var hoursSpan = clock.querySelector('.hours');
                var minutesSpan = clock.querySelector('.minutes');
                var secondsSpan = clock.querySelector('.seconds');

                function updateClock() {
                    var t = getTimeRemaining(endtime);

                    daysSpan.innerHTML = '00';
                    hoursSpan.innerHTML = '00';
                    minutesSpan.innerHTML = '00';
                    secondsSpan.innerHTML = '00';

                    if (t.total <= 0) {
                        clearInterval(timeinterval);
                    }
                }

                updateClock();
                var timeinterval = setInterval(updateClock, 1000);

            }

            var dd = new Array(${listProductSearch.size()});
            for (j = 0; j < ${listProductSearch.size()}; j++) {
                dd[j] = 0;
            }
            var i = 0;
            var deadline = new Array(${listProductSearch.size()});
            <c:forEach items="${listDateTimeEnd}" var="c">
            end = new Date(${c.year}, ${c.month}, ${c.day}, ${c.hour}, ${c.minute}, ${c.second});
            start = new Date(${timenow.year}, ${timenow.month}, ${timenow.day}, ${timenow.hour}, ${timenow.minute}, ${timenow.second});
            if (end <= start) {
                dd[i] = 1;
            }
            a = end - start;
            tmp = new Date(Date.parse(new Date()) + a)
            deadline[i] = tmp;
            i = i + 1;
            </c:forEach>

            i = 0;
            nowCur = new Date(${timenow.year}, ${timenow.month}, ${timenow.day}, ${timenow.hour}, ${timenow.minute}, ${timenow.second});
            <c:forEach items="${listDateTimeStart}" var="c">
            st1 = new Date(${c.year}, ${c.month}, ${c.day}, ${c.hour}, ${c.minute}, ${c.second});
            if (dd[i] === 0) {
                if (nowCur <= st1) {
                    dd[i] = 1;
                }
            }
            i = i + 1;
            </c:forEach>
            i = 0;
            <c:forEach items="${listProductSearch}" var="c">
            if (dd[i] === 1) {
                aaa('p${c.id}', deadline[i]);
            } else {
                initializeClock('p${c.id}', deadline[i]);
            }
            i = i + 1;
            </c:forEach>
        </script>

        <script>
            function getPageList(totalPages, page, maxLength) {
                function range(start, end) {
                    return Array.from(Array(end - start + 1), (_, i) => i + start);
                }

                var sideWidth = maxLength < 9 ? 1 : 2;
                var leftWidth = (maxLength - sideWidth * 2 - 3) >> 1;
                var rightWidth = (maxLength - sideWidth * 2 - 3) >> 1;

                if (totalPages <= maxLength) {
                    return range(1, totalPages);
                }

                if (page <= maxLength - sideWidth - 1 - rightWidth) {
                    return range(1, maxLength - sideWidth - 1).concat(0, range(totalPages - sideWidth + 1, totalPages));
                }

                if (page >= totalPages - sideWidth - 1 - rightWidth) {
                    return range(1, sideWidth).concat(0, range(totalPages - sideWidth - 1 - rightWidth - leftWidth, totalPages));
                }

                return range(1, sideWidth).concat(0, range(page - leftWidth, page + rightWidth), 0, range(totalPages - sideWidth + 1, totalPages));
            }

            $(function () {
                var numberOfItems = $(".card-content .card").length;
                var limitPerPage = 10; //How many card items visible per a page
                var totalPages = Math.ceil(numberOfItems / limitPerPage);
                var paginationSize = 5; //How many page elements visible in the pagination
                var currentPage;

                function showPage(whichPage) {
                    if (whichPage < 1 || whichPage > totalPages) return false;

                    currentPage = whichPage;

                    $(".card-content .card").hide().slice((currentPage - 1) * limitPerPage, currentPage * limitPerPage).show();

                    $(".pagination li").slice(1, -1).remove();

                    getPageList(totalPages, currentPage, paginationSize).forEach(item => {
                        $("<li>").addClass("page-item").addClass(item ? "current-page" : "dots")
                            .toggleClass("active", item === currentPage).append($("<a>").addClass("page-link")
                            .attr({href: "javascript:void(0)"}).text(item || "...")).insertBefore(".next-page");
                    });

                    $(".previous-page").toggleClass("disable", currentPage === 1);
                    $(".next-page").toggleClass("disable", currentPage === totalPages);
                    return true;
                }

                $(".pagination").append(
                    $("<li>").addClass("page-item").addClass("previous-page").append($("<a>").addClass("page-link").attr({href: "javascript:void(0)"}).text("Prev")),
                    $("<li>").addClass("page-item").addClass("next-page").append($("<a>").addClass("page-link").attr({href: "javascript:void(0)"}).text("Next"))
                );

                $(".card-content").show();
                showPage(1);

                $(document).on("click", ".pagination li.current-page:not(.active)", function () {
                    return showPage(+$(this).text());
                });

                $(".next-page").on("click", function () {
                    return showPage(currentPage + 1);
                });

                $(".previous-page").on("click", function () {
                    return showPage(currentPage - 1);
                });
            });
        </script>
    </jsp:attribute>


    <jsp:body>
        <c:choose>
            <c:when test="${listProductSearch.size() == 0}">
                <div class="card-body">
                    <p class="card-text">Không có dữ liệu.</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row rowHead mb-5">
                    <div class="ml-3">
                        <h4>Kết quả tìm kiếm cho: ${textSearch}</h4>
                    </div>
                    <div class="mr-3 d-flex">
                        <form action="" method="post">
                            <div class="input-group selectT">
                                <select class="custom-select" name="sortPro">
                                    <option value="1">Sắp xếp giá tăng</option>
                                    <option value="2">Sắp xếp giá giảm</option>
                                    <option value="3">Thời gian kết thúc tăng</option>
                                    <option value="4">Thời gian kết thúc giảm</option>
                                </select>
                                <div class="input-group-append">
                                    <button type="submit" class="btn btn-secondary pb-0 pt-0 mb-1">
                                        Xem
                                    </button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <div class="card-content row">
                    <c:forEach items="${listProductSearch}" var="p">
                        <div class="col-sm-auto col-item p-0 card_hover">
                            <div class="card h-100">
                                <img src="${pageContext.request.contextPath}/public/imgs/${p.id}/1.jpg" alt="${p.name}"
                                     title="${p.name}" class="card-img-top">
                                <div class="card-body">
                                    <h6 class="card-title">${p.name}</h6>
                                    <h5 class="card-title text-danger">
                                        <fmt:formatNumber value="${p.price}" type="number"/>
                                    </h5>
                                    <small class="card-text">Người bán:
                                        <c:forEach items="${listUser}" var="u">
                                            <c:if test="${u.id == p.idUserSell}">
                                                ${u.username}
                                            </c:if>
                                        </c:forEach>

                                    </small>
                                    <br>
                                    <small class="card-text">Người giữ giá: ${p.idUserCur}</small>
                                    <br>
                                    <small class="card-text">Số lượt ra giá: ${p.countAuction}</small>
                                    <br>
                                    <div id="p${p.id}" class="clockdiv">
                                        <div>
                                            <small class="days dateTimeText"></small>
                                            <small>Ngày</small>
                                        </div>
                                        <div>
                                            <small class="hours dateTimeText"></small>
                                            <small>Giờ</small>
                                        </div>
                                        <div>
                                            <small class="minutes dateTimeText"></small>
                                            <small>Phút</small>
                                        </div>
                                        <div>
                                            <small class="seconds dateTimeText"></small>
                                            <small>Giây</small>
                                        </div>
                                    </div>
                                    <c:forEach items="${listTimeUp}" var="t">
                                        <c:if test="${p.id == t.idPro}">
                                            <small>Mới đăng : ${t.min} phút trước</small>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="card-footer text-muted">
                                    <a class="btn btn-sm btn-outline-primary"
                                       href="${pageContext.request.contextPath}/Product/Detail?id=${p.id}&idType=${p.idType}"
                                       role="button">
                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                        Detail
                                    </a>
                                    <c:if test="${auth == 1}">
                                        <a class="btn btn-sm btn-outline-success"
                                           href="${pageContext.request.contextPath}/Behavior/watchlist?id=${p.id}"
                                           role="button">
                                            <i class="fa fa-plus" aria-hidden="true"></i>
                                            Add
                                        </a>
                                        <a class="btn btn-sm btn-outline-danger"
                                           href="${pageContext.request.contextPath}/Behavior/Auction?id=${p.id}"
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
                <div class="pagination">
                </div>
            </c:otherwise>
        </c:choose>

        </div>
    </jsp:body>
</t:main>