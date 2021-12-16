<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
        <jsp:attribute name="css">
<%--            link css bang datetime--%>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" integrity="sha512-f0tzWhCwVFS3WeYaofoLWkTP62ObhewQ1EZn65oSYDZUg1+CyywGKkWzm8BxaJj5HGKI72PnMH9jYyIFz+GH7g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
            <style>
                .form-register{
                    width: 800px;
                    margin: auto;
                }
            </style>
        </jsp:attribute>

        <jsp:attribute name="js">
<%--            js su li bang datetime--%>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js" integrity="sha512-AIOTidJAcHBH2G/oZv9viEGXRqDNmfdPVPYOYKGy3fti0xIplnlgMHUGfuNRzC6FkzIo0iIxgFnr9RikFxK+sw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
            <script>
<%--                kiem tra truoc khi submit--%>
                $('#frmRegister').on('submit', function(e){
                    e.preventDefault();
                    const userName = $('#txtUserName').val();
                    if(userName.length === 0)
                    {
                       alert("Tên đăng nhập không đúng hoặc đã tồn tại !!!");
                       return;
                    }

                    $.getJSON('${pageContext.request.contextPath}/Account/IsAvailable?user=' + userName, function (data) {
                        if (data === true) {
                            $('#frmRegister').off('submit').submit();
                        } else {
                            alert('Tài khoản đã tồn tại.');
                        }
                    });
                    // hành vi submit đầu tiên bị chặn lại sau nó kiểm tra rồi gỡ hàm này ra
                   // $('#frmRegister').off('submit').submit();
                });

<%--                css cho ô input ngày sinh--%>
                $('#txtDoB').datetimepicker({
                    // cho người dùng nhập ngày sinh
                    format: 'd/m/Y',
                    timepicker: false,
                    mask: true
                });

                $('#txtUserName').select();
            </script>
        </jsp:attribute>

    <jsp:body>
        <form action="" method="post" id="frmRegister">
            <div class="card mt-5 form-register" >
                    <div class="m-auto"><h3>Đăng kí</h3></div>
                <div class="card-body">
                    <h5>Tài khoản</h5>
                    <div class="form-group">
                        <label for="txtUserName">Tên đăng nhập </label>
                        <input type="text" class="form-control" id="txtUserName" name="username">
                    </div>

                    <div class="form-group">
                        <label for="txtPassWord">Mật khẩu </label>
                        <input type="password" class="form-control" id="txtPassWord" name="rawpwd">

                    </div>

                    <div class="form-group">
                        <label for="txtConfirm">Nhập lại mật khẩu </label>
                        <input type="password" class="form-control" id="txtConfirm">

                    </div>

                    <h5 class="mt-4">Thông tin cá nhân</h5>
                    <div class="form-group">
                        <label for="txtName">Họ và tên </label>
                        <input type="text" class="form-control" id="txtName"  name="name">

                    </div>
                    <div class="form-group">
                        <label for="txtDoB">Ngày sinh </label>
                        <input type="text" class="form-control" id="txtDoB"  name="dob">

                    </div>
                    <div class="form-group">
                        <label for="txtPhone">Số điện thoại </label>
                        <input type="text" class="form-control" id="txtPhone" name="phone">

                    </div>
                    <div class="form-group">
                        <label for="txtEmail">Email </label>
                        <input type="text" class="form-control" id="txtEmail" name="email" >

                    </div>
                </div>
                <div class="card-footer ">
                    <button type="submit" class="btn btn-primary mb-5">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Đăng kí
                    </button>
                </div>
            </div>
        </form>

    </jsp:body>


</t:main>
