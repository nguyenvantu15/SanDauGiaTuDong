<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main>
        <jsp:attribute name="css">
<%--            link css bang datetime--%>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" integrity="sha512-f0tzWhCwVFS3WeYaofoLWkTP62ObhewQ1EZn65oSYDZUg1+CyywGKkWzm8BxaJj5HGKI72PnMH9jYyIFz+GH7g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
            <style>
                body{
                    background: #1A233A;
                }
                .form-register{
                    width: 800px;
                    margin: auto;
                    background-color: #272e48;
                    color: white;
                }
                .form-control {
                    border: 1px solid #596280;
                    -webkit-border-radius: 2px;
                    -moz-border-radius: 2px;
                    border-radius: 2px;
                    font-size: 1.2rem;
                    background: #1A233A;
                    color: #bcd0f7;
                }
                .btn{
                    font-size: 1.2rem;
                }
            </style>
        </jsp:attribute>
    <jsp:attribute name="js">
<%--            js su li bang datetime--%>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js" integrity="sha512-AIOTidJAcHBH2G/oZv9viEGXRqDNmfdPVPYOYKGy3fti0xIplnlgMHUGfuNRzC6FkzIo0iIxgFnr9RikFxK+sw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<%--        Tạo captcha--%>
        <script>
            function getRanIndex(maxLength){
            //taoj một vị trí ngẫu nhiên
                return Math.floor(Math.random() * maxLength);

            }
            var cap;
            function getCaptcha(){
                var canvas = document.getElementById('canvas');
                var pen = canvas.getContext('2d');
                var captch = Math.random().toString(36).substring(2,8);
                //Math.random -> random ra so float nhu 0.0123
                //toString(36) -> Chuyển đổi num thành cơ số 36
                //để loại bỏ dấu chấm động được sử dụng substring(2,8)

                pen.font = "60px Georgia";
                pen.fillStyle ="grey";
                pen.fillRect(0,0,600,600);
                pen.fillStyle = "orange";
                maxLength = captch.length;
                index1 = getRanIndex(maxLength);
                index2 = getRanIndex(maxLength);
                //lam bat ki ki tu nao in hoa

                captch = captch.substring(0,index1-1) + captch[index1].toUpperCase() + captch.substring(index1+1,maxLength);
                captch = captch.substring(0,index2-1) + captch[index2].toUpperCase() + captch.substring(index2+1,maxLength);

                cap = captch;
                captch = captch.split('').join('');
                pen.fillText(captch,50,100)
            }
            getCaptcha();

        </script>
<%--        Kiem tra truoc khi submit--%>
        <script>
                <%--                kiem tra truoc khi submit--%>
                $('#frmRegister').on('submit', function(e){
                    e.preventDefault();
                    const userName = $('#txtUserName').val().trim();
                    const passWord = $('#txtPassWord').val();
                    const rePass= $('#txtConfirm').val();
                    const name = $('#txtName').val();
                    const doB = $('#txtDoB').val();
                    const phone = $('#txtPhone').val();
                    const email = $('#txtEmail').val().trim();

                    // const  checkRepass = checkRepass(passWord.trim(), rePass.trim());
                    if(userName.length === 0 || passWord.length === 0 || rePass.length === 0 || doB ==='__/__/____'
                        || name.length === 0 || doB.length === 0 || phone.length ===0 || email.length === 0)
                    {
                        alert("Thông tin người dùng chưa đầy đủ !!! \n Mời nhập lại !!!!");
                        return;
                    }

                    if(passWord.trim() !== rePass.trim())
                    {
                        alert("Mật khẩu không trùng khớp !!!");
                        return;
                    }

                    $.getJSON('${pageContext.request.contextPath}/Account/IsAvailable?username=' + userName, function (data) {

                        if (data === true) {
                            $.getJSON('${pageContext.request.contextPath}/Account/checkEmail?email=' +email, function (dataEmail) {
                                if(dataEmail === true){
                                    typedData = document.getElementById('txtUserCap').value;
                                   if(typedData === cap)
                                   {
                                       $('#frmRegister').off('submit').submit();
                                   }
                                   else {
                                       alert("Capcha không chính xác !!!");
                                       document.getElementById('txtUserCap').value = "";
                                       getCaptcha();
                                   }
                                }
                                else {
                                    alert("Email đã được sử dụng !!!");
                                    document.getElementById('txtUserCap').value = "";
                                    getCaptcha()
                                }
                            });
                        }
                        else{
                            alert("Tên tài khoản đã tồn tại !!!")
                        }
                    });

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
                <div class="m-auto"><h2>Đăng kí</h2></div>
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

                    <div class="form-group">
                        <label for="txtUserCap">Captcha </label>
                        <div style="display: flex; justify-content:space-between;align-items:center">
                            <canvas id="canvas"  style="width: 44%; height: 60px; border: 2px solid grey;"></canvas>
                            <button onclick="getCaptcha()" type="button" class = "btn btn-outline-primary">
                                <i class="fa fa-refresh" aria-hidden="true"></i>
                            </button>
                            <input style="width: 44%;height: 65px;" type="text" class="form-control" id="txtUserCap" name="email" >
                        </div>

                    </div>
                </div>
                <div class="card-footer" style="display: flex">
                    <button type="submit" class="btn btn-primary mb-5" id="OTP" name="btnOTP">
                        <i class="fa fa-check" aria-hidden="true"></i>
                        Đăng kí
                    </button>
                </div>

            </div>
        </form>

    </jsp:body>


</t:main>
