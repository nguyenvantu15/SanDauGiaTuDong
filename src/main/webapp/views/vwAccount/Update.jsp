<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="userFind" scope="request" type="com.ute.sandaugiatudong.beans.User"/>

<t:main>

    <jsp:attribute name="css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" integrity="sha512-f0tzWhCwVFS3WeYaofoLWkTP62ObhewQ1EZn65oSYDZUg1+CyywGKkWzm8BxaJj5HGKI72PnMH9jYyIFz+GH7g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
            <style>
                body{
                    margin: 0;
                    color: #bcd0f7;
                    font-size: 1.3rem;
                    background: #1A233A;
                }
                h5{
                    font-size: 1.3rem;
                }
                .account-settings .user-profile {
                    margin: 0 0 1rem 0;
                    padding-bottom: 1rem;
                    text-align: center;
                }
                .account-settings .user-profile .user-avatar {
                    margin: 0 0 1rem 0;
                }
                .account-settings .user-profile .user-avatar img {
                    width: 90px;
                    height: 90px;
                    -webkit-border-radius: 100px;
                    -moz-border-radius: 100px;
                    border-radius: 100px;
                }
                .account-settings .user-profile h5.user-name {
                    margin: 0 0 0.5rem 0;
                }
                .account-settings .user-profile h6.user-email {
                    margin: 0;
                    font-size: .9rem;
                    font-weight: 400;
                }
                .account-settings .about {
                    margin: 1rem 0 0 0;
                    font-size: 0.8rem;
                    text-align: center;
                }
                .card {
                    background: #272E48;
                    -webkit-border-radius: 5px;
                    -moz-border-radius: 5px;
                    border-radius: 5px;
                    border: 0;
                    margin-bottom: 1rem;
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
                    font-size: 1.1rem;
                }
                .mg-top{
                    margin-top: 30px;
                    margin-bottom: 30px;
                }
                .font_1_3rem{
                    font-size: 1.3rem;
                }

            </style>
        </jsp:attribute>


    <jsp:attribute name="js">
<%--            js su li bang datetime--%>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js" integrity="sha512-AIOTidJAcHBH2G/oZv9viEGXRqDNmfdPVPYOYKGy3fti0xIplnlgMHUGfuNRzC6FkzIo0iIxgFnr9RikFxK+sw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

        <%--                css cho ô input ngày sinh--%>
       <script>
        $('#txtDoB').datetimepicker({
                    // cho người dùng nhập ngày sinh
                    format: 'd/m/Y',
                    timepicker: false,
                    mask: true
                });
       </script>

        <script>
            <%--                kiem tra truoc khi submit--%>
            $('#frmUpdatePro').on('submit', function(e){
                e.preventDefault();
                const oldPass= $('#txtOldPass').val();
                const newPass = $('#txtNewPass').val();
                const name = $('#txtName').val();
                const doB = $('#txtDoB').val();
                const phone = $('#txtPhone').val();
                const email = $('#txtEmail').val();

                // const  checkRepass = checkRepass(passWord.trim(), rePass.trim());
                if(oldPass.length === 0 || newPass.length === 0
                    || name.length === 0 || doB.length === 0 || phone.length ===0 || email.length === 0)
                {
                    alert("Thông tin người dùng chưa đầy đủ !!! \n Mời nhập lại !!!!");
                    return;
                }
                // hành vi submit đầu tiên bị chặn lại sau nó kiểm tra rồi gỡ hàm này ra
                $('#frmUpdatePro').off('submit').submit();
            });

        </script>

        <script>
            function check(){
                var txtdate = "${userFind.dob}";

                const d1 = txtdate.split('T');
                const d2 = d1[0];
                const d3 = d2.split('-');
                const date = d3[2]+ '/' +d3[1] + '/' + d3[0];
                $('#txtDoB').val(date);
            }
            check();
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row gutters mg-top">
                <div class=" col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <form class="card h-100" action="" method="post" id="frmUpdatePro">
                    <div class="card h-100">
                        <div class="card-body">
                            <c:if  test="${hasErrorPass}">
                                <div  class="alert alert-warning alert-dismissible fade show" role="alert">
                                    <strong>Error!!!! </strong> ${errorMessagePass}
                                    <button  type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span  aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </c:if>
                            <div class="row gutters">
                                <div class="col-xl-12  col-lg-12 col-md-12 col-sm-12 col-12">
                                    <h6 class="mb-3 text-primary font_1_3rem">Edit Profile with ID Account</h6>
                                    <input  type="text" style="background-color:#1A233A " class="form-control" id="txtID"   name="id" value="ID = ${userFind.id}" disabled>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="txtName">Full Name</label>
                                        <input value="${userFind.name}" type="text" class="form-control" id="txtName"  name="name">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="txtEmail">Email</label>
                                        <input  type="email" class="form-control" id="txtEmail" value="${userFind.email}"  name="email">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="txtPhone">Phone</label>
                                        <input type="text" class="form-control" id="txtPhone"  value="${userFind.phone}" name="phone">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label >Date of Birth</label>
<%--                                        <input  type="datetime-local" id ="dob" value="${userFind.dob}" class="form-control" name="dob">--%>
                                        <input type="text" class="form-control" id="txtDoB" value="${userFind.dob}"  name="dob" >
                                    </div>
                                </div>
                            </div>
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <h6 class="mb-3 text-primary font_1_3rem">Edit PassWord</h6>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="txtOldPass">Old PassWord</label>

                                        <input type="password" class="form-control" id="txtOldPass" placeholder="Old Pass" name="oldpass">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="txtNewPass">New PassWord</label>
                                        <input type="password" class="form-control" id="txtNewPass" placeholder="New Pass" name="newpass">
                                    </div>
                                </div>

                            </div>
                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="text-right">
                                        <a href="${pageContext.request.contextPath}/Account/Profile">
                                        <button type="button" id="submitNot" name="submit" class="btn btn-secondary">Cancel</button>
                                        </a>
                                        <button type="submit" id="submitSave" name="submit" class="btn btn-success" formaction="${pageContext.request.contextPath}/Account/Update?id=${userFind.id}">Save</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    </form>
            </div>
        </div>
    </jsp:body>
</t:main>
