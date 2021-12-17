<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="TypeWithDetails" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Type>"/>
<t:main>
    <jsp:attribute name="css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" integrity="sha512-f0tzWhCwVFS3WeYaofoLWkTP62ObhewQ1EZn65oSYDZUg1+CyywGKkWzm8BxaJj5HGKI72PnMH9jYyIFz+GH7g==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <style>

        </style>
    </jsp:attribute>

    <jsp:attribute name="js">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js" integrity="sha512-AIOTidJAcHBH2G/oZv9viEGXRqDNmfdPVPYOYKGy3fti0xIplnlgMHUGfuNRzC6FkzIo0iIxgFnr9RikFxK+sw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script>
            $('#txtTimeStart').datetimepicker({
                format: 'd/m/Y H:i',
                mask: true,
                lang:'ru',
                step:5
            });

            $('#txtTimeEnd').datetimepicker({
                format: 'd/m/Y H:i',
                mask: true,
                lang:'ru',
                step:5
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <h4>Thêm sản phẩm</h4>
        <form>
            <div class="form-group">
                <label for="txtProName">Tên sản phẩm</label>
                <input type="text" class="form-control" id="txtProName" placeholder="" name="name">
            </div>
            <div class="form-group">
                <label for="txtProPrice">Giá tiền</label>
                <input type="number" class="form-control" id="txtProPrice" placeholder="" name="price">
            </div>
            <div class="form-group">
                <label for="txtProType">Loại</label>
                <select class="form-control" id="txtProType">
                    <c:forEach items="${TypeWithDetails}" var="c">
                        <option>${c.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="txtTimeStart">Ngày bắt đầu đấu giá</label>
                <input type="text" class="form-control" id="txtTimeStart" name="timeStart">
            </div>
            <div class="form-group">
                <label for="txtTimeEnd">Ngày kết thúc đấu giá</label>
                <input type="text" class="form-control" id="txtTimeEnd" name="timeEnd">
            </div>

        </form>
    </jsp:body>
</t:main>