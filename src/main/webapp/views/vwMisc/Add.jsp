<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="TypeWithDetails" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Type>"/>
<t:main>
    <jsp:attribute name="css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" integrity="sha512-f0tzWhCwVFS3WeYaofoLWkTP62ObhewQ1EZn65oSYDZUg1+CyywGKkWzm8BxaJj5HGKI72PnMH9jYyIFz+GH7g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.2.5/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
        <style>
        </style>
    </jsp:attribute>

    <jsp:attribute name="js">
        <script src="https://cdn.tiny.cloud/1/5nmlpb2qma707p8n50ohwl9uai2afuehks9mqypol4rj4wf6/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js" integrity="sha512-AIOTidJAcHBH2G/oZv9viEGXRqDNmfdPVPYOYKGy3fti0xIplnlgMHUGfuNRzC6FkzIo0iIxgFnr9RikFxK+sw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.2.5/js/fileinput.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.2.5/themes/fa/theme.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.2.5/js/locales/vi.min.js"></script>
        <script>

            $("#imgsPro").fileinput({
                theme: 'fa',
                language: 'vi',
                allowedFileExtensions: ['jpg', 'png']
            });


            tinymce.init({
                selector: '#txtFullDes',
                height: 350,
                plugins: 'paste image link autolink lists table media',
                menubar: false,
                toolbar: [
                    'undo redo | bold italic underline strikethrough | numlist bullist | alignleft aligncenter alignright | forecolor backcolor | table link image media'
                ],
            });

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
        <form action="" method="post" enctype="multipart/form-data">
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
                <select class="form-control" id="txtProType" name="ProType">
                    <c:forEach items="${TypeWithDetails}" var="c">
                        <option>${c.id}.${c.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="txtTinyDes">Mô tả ngắn</label>
                <input type="text" class="form-control" id="txtTinyDes" placeholder="" name="tinyDes">
            </div>
            <div class="form-group">
                <label for="txtTimeStart">Ngày bắt đầu đấu giá</label>
                <input type="text" class="form-control" id="txtTimeStart" name="timeStart">
            </div>
            <div class="form-group">
                <label for="txtTimeEnd">Ngày kết thúc đấu giá</label>
                <input type="text" class="form-control" id="txtTimeEnd" name="timeEnd">
            </div>
            <div class="form-group">
                <label for="txtFullDes">Mô tả sản phẩm</label>
                <textarea id="txtFullDes" name="fullDes"></textarea>
            </div>
            <div class="form-group">

            </div>
            <div class="form-group">
                <label for="imgsPro">Thêm ảnh ( 4 ảnh) </label>
                <input id="imgsPro" name="imgsPro" type="file"  data-browse-on-zone-click="true" multiple
                       data-show-upload="false" data-show-caption="true">
            </div>

            <button type="submit" class="btn btn-primary mb-5">
                <i class="fa fa-save" aria-hidden="true"></i>
                Đăng kí
            </button>
        </form>
    </jsp:body>
</t:main>