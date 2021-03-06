<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="ProductByCategory" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Product>"/>

<jsp:useBean id="listDateTimeProByCat" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="listDateTimeStartProByCat" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.DateTimeNew>"/>
<jsp:useBean id="timenow" scope="request" type="com.ute.sandaugiatudong.beans.DateTimeNew"/>
<jsp:useBean id="listUser" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.User>"/>

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

            var dd = new Array(${ProductByCategory.size()});
            for (j = 0; j < ${ProductByCategory.size()}; j++) {
                dd[j] = 0;
            }
            var i = 0;
            var deadline = new Array(${ProductByCategory.size()});
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
            <c:forEach items="${ProductByCategory}" var="c">
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
        <div class = "row rowHead mb-5">
            <div class="ml-3">
                <h4>Danh M???c</h4>
            </div>
            <div class="mr-3 d-flex">
                <form action="" method="post">
                    <div class="input-group selectT">
                        <select class="custom-select" name="sortPro">
                            <option value="1">S???p x???p gi?? t??ng</option>
                            <option value="2">S???p x???p gi?? gi???m</option>
                            <option value="3">Th???i gian k???t th??c t??ng</option>
                            <option value="4">Th???i gian k???t th??c gi???m</option>
                        </select>
                        <div >
                            <button  style="border-bottom-left-radius: 0px;border-top-left-radius: 0px " type="submit" class="btn btn-secondary pb-0 pt-0 mb-1">
                                Xem
                            </button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <div class="row">
            <c:forEach items="${ProductByCategory}" var="c">
                <div class="col-sm-auto col-item p-0 card_hover">
                    <div class="card h-100">
                        <img src="${pageContext.request.contextPath}/public/imgs/${c.id}/1.jpg" alt="${c.name}" title="${c.name}" class="card-img-top">
                        <div class="card-body">
                            <h6 class="card-title">${c.name}</h6>
                            <h5 class="card-title text-danger">
                                <fmt:formatNumber value="${c.price}" type="number" />
                            </h5>
                            <small class="card-text">Ng?????i b??n:
                                <c:forEach items="${listUser}" var="u">
                                    <c:if test="${u.id == c.idUserSell}">
                                        ${u.username}
                                    </c:if>
                                </c:forEach>
                            </small>
                            <br>
                            <small class="card-text">Ng?????i gi??? gi??:
                                <c:if test="${c.idUserCur != 0}">
                                    <c:forEach items="${listUser}" var="u">
                                        <c:if test="${u.id == c.idUserCur}">
                                            ${u.username}
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </small>
                            <br>
                            <small class="card-text">S??? l?????t ra gi??: ${c.countAuction}</small>
                            <br>
                            <div id="p${c.id}" class="clockdiv">
                                <div>
                                    <small class="days dateTimeText"></small>
                                    <small>Ng??y</small>
                                </div>
                                <div>
                                    <small class="hours dateTimeText"></small>
                                    <small>Gi???</small>
                                </div>
                                <div>
                                    <small class="minutes dateTimeText"></small>
                                    <small>Ph??t</small>
                                </div>
                                <div>
                                    <small class="seconds dateTimeText"></small>
                                    <small>Gi??y</small>
                                </div>
                            </div>
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
                                    ?????u gi??
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </jsp:body>
</t:main>