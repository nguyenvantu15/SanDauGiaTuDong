<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="ProductByUser" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Product>"/>

<jsp:useBean id="listDateTimeProByCat" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="listDateTimeStartProByCat" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="timenow" scope="request" type="com.ute.sandaugiatudong.beans.DateTimeNew"/>
<jsp:useBean id="listUser" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.User>"/>

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
            .dateTimeText{
                color : red;
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
        <style>
            .rowHead{
                justify-content: space-between;
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

            var dd = new Array(${ProductByUser.size()});
            for (j = 0; j < ${ProductByUser.size()}; j++) {
                dd[j] = 0;
            }
            var i = 0;
            var deadline = new Array(${ProductByUser.size()});
            <c:forEach items="${listDateTimeProByCat}" var="c">
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
            <c:forEach items="${listDateTimeStartProByCat}" var="c">
            st1 = new Date(${c.year}, ${c.month}, ${c.day}, ${c.hour}, ${c.minute}, ${c.second});
            if (dd[i] === 0){
                if(nowCur <= st1 ){
                    dd[i]=1;
                }
            }
            i = i+1;
            </c:forEach>
            i = 0;
            <c:forEach items="${ProductByUser}" var="c">
            if (dd[i] === 1) {
                aaa('p${c.id}', deadline[i]);
            } else {
                initializeClock('p${c.id}', deadline[i]);
            }
            i = i + 1;
            </c:forEach>
        </script>
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
                            <small class="card-text">Người bán:
                                <c:forEach items="${listUser}" var="u">
                                    <c:if test="${u.id == c.idUserSell}">
                                        ${u.username}
                                    </c:if>
                                </c:forEach>
                            </small>
                            <br>
                            <small class="card-text">Người giữ giá:
                                <c:if test="${c.idUserCur != 0}">
                                    <c:forEach items="${listUser}" var="u">
                                        <c:if test="${u.id == c.idUserCur}">
                                            ${u.username}
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </small>
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