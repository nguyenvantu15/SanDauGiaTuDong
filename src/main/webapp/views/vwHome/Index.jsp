<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="top5Price" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Product>"/>
<jsp:useBean id="top5CountAuction" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Product>"/>
<jsp:useBean id="top5AuctionEnd" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Product>"/>
<jsp:useBean id="timenow" scope="request" type="com.ute.sandaugiatudong.beans.DateTimeNew"/>
<jsp:useBean id="listDateTimeEndPrice" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="listDateTimeStartPrice" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="listDateTimeEndCountAuction" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="listDateTimeStartCountAuction" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="listDateTimeEndAuctionEnd" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="listDateTimeStartAuctionEnd" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>


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

            .dateTimeText {
                color: red;
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

            var dd = new Array(5);
            for (j = 0; j < 5; j++) {
                dd[j] = 0;
            }
            nowCur = new Date(${timenow.year}, ${timenow.month}, ${timenow.day}, ${timenow.hour}, ${timenow.minute}, ${timenow.second});
            var i = 0;
            var deadline = new Array(5);
            <c:forEach items="${listDateTimeEndPrice}" var="c">
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
            <c:forEach items="${listDateTimeStartPrice}" var="c">
            st1 = new Date(${c.year}, ${c.month}, ${c.day}, ${c.hour}, ${c.minute}, ${c.second});
            if (dd[i] === 0) {
                if (nowCur <= st1) {
                    dd[i] = 1;
                }
            }
            i = i + 1;
            </c:forEach>
            i = 0;
            <c:forEach items="${top5Price}" var="c">
            if (dd[i] === 1) {
                aaa('p${c.id}', deadline[i]);
            } else {
                initializeClock('p${c.id}', deadline[i]);
            }
            i = i + 1;
            </c:forEach>

            var dd1 = new Array(5);
            for (k = 0; k < 5; k++) {
                dd1[k] = 0;
            }
            var i1 = 0;
            var deadline1 = new Array(5);
            <c:forEach items="${listDateTimeEndCountAuction}" var="c1">
            end1 = new Date(${c1.year}, ${c1.month}, ${c1.day}, ${c1.hour}, ${c1.minute}, ${c1.second});
            start1 = new Date(${timenow.year}, ${timenow.month}, ${timenow.day}, ${timenow.hour}, ${timenow.minute}, ${timenow.second});
            if (end1 <= start1) {
                dd1[i1] = 1;
            }
            a1 = end1 - start1;
            tmp1 = new Date(Date.parse(new Date()) + a1)
            deadline1[i1] = tmp1;
            i1 = i1 + 1;
            </c:forEach>

            i1 = 0;
            <c:forEach items="${listDateTimeStartCountAuction}" var="c1">
            st1 = new Date(${c1.year}, ${c1.month}, ${c1.day}, ${c1.hour}, ${c1.minute}, ${c1.second});
            if (dd1[i1] === 0) {
                if (nowCur <= st1) {
                    dd1[i1] = 1;
                }
            }
            i1 = i1 + 1;
            </c:forEach>

            i1 = 0;
            <c:forEach items="${top5CountAuction}" var="c1">
            if (dd1[i1] === 1) {
                aaa('ca${c1.id}', deadline1[i1]);
            } else {
                initializeClock('ca${c1.id}', deadline1[i1]);
            }
            i1 = i1 + 1;
            </c:forEach>


            var dd2 = new Array(5);
            for (k1 = 0; k1 < 5; k1++) {
                dd2[k1] = 0;
            }
            var i2 = 0;
            var deadline2 = new Array(5);
            <c:forEach items="${listDateTimeEndAuctionEnd}" var="c2">
            end2 = new Date(${c2.year}, ${c2.month}, ${c2.day}, ${c2.hour}, ${c2.minute}, ${c2.second});
            start2 = new Date(${timenow.year}, ${timenow.month}, ${timenow.day}, ${timenow.hour}, ${timenow.minute}, ${timenow.second});
            if (end2 <= start2) {
                dd2[i2] = 1;
            }
            a2 = end2 - start2;
            tmp2 = new Date(Date.parse(new Date()) + a2)
            deadline2[i2] = tmp2;
            i2 = i2 + 1;
            </c:forEach>
            i2 = 0;
            <c:forEach items="${listDateTimeStartAuctionEnd}" var="c2">
            st1 = new Date(${c2.year}, ${c2.month}, ${c2.day}, ${c2.hour}, ${c2.minute}, ${c2.second});
            if (dd2[i2] === 0) {
                if (nowCur <= st1) {
                    dd2[i2] = 1;
                }
            }
            i2 = i2 + 1;
            </c:forEach>

            i2 = 0;
            <c:forEach items="${top5AuctionEnd}" var="c2">
            if (dd2[i2] === 1) {
                aaa('ae${c2.id}', deadline2[i2]);
            } else {
                initializeClock('ae${c2.id}', deadline2[i2]);
            }
            i2 = i2 + 1;
            </c:forEach>
        </script>
    </jsp:attribute>
    <jsp:body>
        <div>
            <h4>Top 5 sản phẩm giá cao nhất</h4>
        </div>
        <div class="row m-0">
            <c:forEach items="${top5Price}" var="c">
                <div class="col-sm-auto col-item p-0  card_hover">
                    <div class="card h-100">
                        <img src="${pageContext.request.contextPath}/public/imgs/${c.id}/1.jpg" alt="${c.name}"
                             title="${c.name}" class="card-img-top">
                        <div class="card-body">
                            <h6 class="card-title">${c.name}</h6>
                            <h5 class="card-title text-danger mb-0">
                                <fmt:formatNumber value="${c.price}" type="number"/>
                            </h5>
                            <small class="card-text">Người bán: ${c.idUserSell} </small>
                            <br>
                            <small class="card-text">Người đặt giá cao nhất: ${c.idUserCur}</small>
                            <br>
                            <small class="card-text">Số lượt ra giá: ${c.countAuction}</small>
                            <br>
                            <div id="p${c.id}" class="clockdiv">
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

                        </div>
                        <div class="card-footer text-muted">
                            <a class="btn btn-sm btn-outline-primary"
                               href="${pageContext.request.contextPath}/Product/Detail?id=${c.id}&idType=${c.idType}"
                               role="button">
                                <i class="fa fa-eye" aria-hidden="true"></i>
                                Detail
                            </a>
                            <c:if test="${auth == 1}">
                                <a class="btn btn-sm btn-outline-success"
                                   href="${pageContext.request.contextPath}/Behavior/watchlist?id=${c.id}"
                                   role="button">
                                    <i class="fa fa-plus" aria-hidden="true"></i>
                                    Add
                                </a>
                                <a class="btn btn-sm btn-outline-danger"
                                   href="${pageContext.request.contextPath}/Behavior/Auction?id=${c.id}"
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
        <div>
            <h4>Top 5 sản phẩm nhiều lượt ra giá nhất</h4>
        </div>
        <div class="row m-0">
            <c:forEach items="${top5CountAuction}" var="c">
                <div class="col-sm-auto col-item p-0 card_hover">
                    <div class="card h-100">
                        <img src="${pageContext.request.contextPath}/public/imgs/${c.id}/1.jpg" alt="${c.name}"
                             title="${c.name}" class="card-img-top">
                        <div class="card-body">
                            <h6 class="card-title">${c.name}</h6>
                            <h5 class="card-title text-danger mb-0">
                                <fmt:formatNumber value="${c.price}" type="number"/>
                            </h5>
                            <small class="card-text">Người bán: ${c.idUserSell} </small>
                            <br>
                            <small class="card-text">Người đặt giá cao nhất: ${c.idUserCur}</small>
                            <br>
                            <small class="card-text">Số lượt ra giá: ${c.countAuction}</small>
                            <div id="ca${c.id}" class="clockdiv">
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
                        </div>
                        <div class="card-footer text-muted">
                            <a class="btn btn-sm btn-outline-primary"
                               href="${pageContext.request.contextPath}/Product/Detail?id=${c.id}&idType=${c.idType}"
                               role="button">
                                <i class="fa fa-eye" aria-hidden="true"></i>
                                Detail
                            </a>
                            <c:if test="${auth == 1}">
                                <a class="btn btn-sm btn-outline-success"
                                   href="${pageContext.request.contextPath}/Behavior/watchlist?id=${c.id}"
                                   role="button">
                                    <i class="fa fa-plus" aria-hidden="true"></i>
                                    Add
                                </a>
                                <a class="btn btn-sm btn-outline-danger"
                                   href="${pageContext.request.contextPath}/Behavior/Auction?id=${c.id}"
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
        <div>
            <h4>Top 5 sản phẩm gần kết thúc</h4>
        </div>
        <div class="row m-0">
            <c:forEach items="${top5AuctionEnd}" var="c">
                <div class="col-sm-auto col-item p-0 card_hover">
                    <div class="card h-100">
                        <img src="${pageContext.request.contextPath}/public/imgs/${c.id}/1.jpg" alt="${c.name}"
                             title="${c.name}" class="card-img-top">
                        <div class="card-body">
                            <h6 class="card-title">${c.name}</h6>
                            <h5 class="card-title text-danger mb-0">
                                <fmt:formatNumber value="${c.price}" type="number"/>
                            </h5>
                            <small class="card-text">Người bán: ${c.idUserSell} </small>
                            <br>
                            <small class="card-text">Người đặt giá cao nhất: ${c.idUserCur}</small>
                            <br>
                            <small class="card-text">Số lượt ra giá: ${c.countAuction}</small>
                            <div id="ae${c.id}" class="clockdiv">
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
                        </div>
                        <div class="card-footer text-muted">
                            <a class="btn btn-sm btn-outline-primary"
                               href="${pageContext.request.contextPath}/Product/Detail?id=${c.id}&idType=${c.idType}"
                               role="button">
                                <i class="fa fa-eye" aria-hidden="true"></i>
                                Detail
                            </a>
                            <c:if test="${auth == 1}">
                                <a class="btn btn-sm btn-outline-success"
                                   href="${pageContext.request.contextPath}/Behavior/watchlist?id=${c.id}"
                                   role="button">
                                    <i class="fa fa-plus" aria-hidden="true"></i>
                                    Add
                                </a>
                                <a class="btn btn-sm btn-outline-danger"
                                   href="${pageContext.request.contextPath}/Behavior/Auction?id=${c.id}"
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
