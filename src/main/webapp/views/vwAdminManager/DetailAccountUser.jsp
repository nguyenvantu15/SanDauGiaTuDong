<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="AccountUser" scope="request" type="com.ute.sandaugiatudong.beans.User"/>

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

            </style>
        </jsp:attribute>


    <jsp:attribute name="js">
        <script>
            $('#frmUserManager').on('submit', function(e){
                // e.preventDefault();
                document
                    .querySelectorAll( "input" )
                    .forEach( ( input ) => {
                        input.removeAttribute( "disabled" );
                    } );

                $('#frmUserManager').off('submit').submit();
                });
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row gutters">
                <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="account-settings">
                                <div class="user-profile">
                                    <div class="user-avatar">
                                        <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="Maxwell Admin">
                                    </div>
                                    <h5 class="user-name" >${AccountUser.username}</h5>
                                    <h6 class="user-email">${AccountUser.email}</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                    <form action="" method="post" id="frmUserManager"class="card h-100">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="row gutters">

                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="id">ID</label>
                                        <input type="text" class="form-control" id="id" name="id" value="${AccountUser.id}" style="background: #1A233A" disabled>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="username">UserName</label>
                                        <input type="text" class="form-control" id="username" name="username" value="${AccountUser.username}" style="background: #1A233A" disabled>
                                    </div>
                                </div>

                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="Name">Full Name</label>
                                        <input type="text" class="form-control" id="Name" name="name" value="${AccountUser.name}" style="background: #1A233A" disabled >
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="Email">Email</label>
                                        <input type="email" class="form-control" id ="Email" name="email" value="${AccountUser.email}" style="background: #1A233A" disabled >
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="Phone">Phone</label>
                                        <input type="text" class="form-control" id ="Phone" name="phone" value="${AccountUser.phone}" style="background: #1A233A" disabled >
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="permission">Permission</label>
                                        <input type="text" class="form-control" id="permission" name="permission" value="${AccountUser.permission}" style="background: #1A233A" disabled  >
                                    </div>
                                </div>
                                <c:if  test="${AccountUser.permission == 2}">
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                        <div class="form-group">
                                            <label for="Mark">Mark</label>
                                            <input type="text" class="form-control" id ="Mark" name="phone" value="${AccountUser.mark}" style="background: #1A233A" disabled >
                                        </div>
                                    </div>
                                    <div class="row gutters" style="padding-left: 32px; width: 100%;">
                                        <div>Comment của khách hàng</div>
                                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mt-3 mb-3" style="padding: 0; padding: 0;
                                                                                                          border: 1px solid #596280; border-radius: 2px; background: #1a233a">
                                            <div>${AccountUser.comment}</div>
                                        </div>
                                    </div>
                                </c:if>

                            </div>

                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="text-right">
                                        <a href="${pageContext.request.contextPath}/Admin/UserManager">
                                            <button type="button" class="btn btn-warning">
                                                <i class="fa fa-backward" aria-hidden="true"></i>
                                                Trở về
                                            </button>
                                        </a>
                                        <c:if  test="${AccountUser.permission != 3}">
                                            <button type="summit" id="submitUpdate" name="submit" formaction="${pageContext.request.contextPath}/Admin/RemoveAccount" class="btn btn-danger" >
                                                <i class="fa fa-times" aria-hidden="true"></i>
                                                Xóa
                                            </button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    </form>

                </div>

            </div>
        </div>

    </jsp:body>
</t:main>
