<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--Danh sach cate dduoc taoj trong MiscSerlet--%>
<jsp:useBean id="listCate" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Type>"/>
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
            $('#frmAddType').on('submit', function(e){
                e.preventDefault();
                const newType = $('#txtNewType').val();

                if(newType.length === 0 )
                {
                    alert("Chưa nhập loại sản phẩm mới !!! \n Mời nhập lại !!!!");
                    return;
                }
                else
                {
                    $('#frmRegister').off('submit').submit();
                }

            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <h4>Thêm loại sản phẩm</h4>
        <form action="" method="post" id="frmAddType">
            <div class="form-group">
                <label for="txtCategory">Category</label>
                <select class="form-control" id="txtCategory" name="category">
                    <c:forEach items="${listCate}" var="c">
                        <option>${c.id}.${c.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="txtNewType">Tên loại sản phẩm mới</label>
                <input type="text" class="form-control" id="txtNewType" placeholder="" name="newtype">
            </div>

            <button type="submit"  class="btn btn-primary mb-5">
                <i class="fa fa-save" aria-hidden="true"></i>
                Save
            </button>
        </form>
    </jsp:body>
</t:main>