<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


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
                                    <h5 class="user-name" >${authUser.username}</h5>
                                    <h6 class="user-email">${authUser.email}</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="row gutters">
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="Name">Full Name</label>
                                        <input type="text" class="form-control" id="Name"value="${authUser.name}" style="background: #1A233A" disabled>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="Email">Email</label>
                                        <input type="email" class="form-control" id ="Email" value="${authUser.email}" style="background: #1A233A" disabled >
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="Phone">Phone</label>
                                        <input type="text" class="form-control" id ="Phone"  value="${authUser.phone}" style="background: #1A233A" disabled >
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-group">
                                        <label for="permission">Permission</label>
                                        <input type="text" class="form-control" id="permission" value="${authUser.permission}" style="background: #1A233A" disabled>
                                    </div>
                                </div>
                            </div>

                            <div class="row gutters">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="text-right">
                                        <a href="${pageContext.request.contextPath}/Account/Update?id=${authUser.id}">
                                            <button type="button" id="submitSeller" name="submit" class="btn btn-primary">Đăng kí Seller</button>
                                            <button type="button" id="submitUpdate" name="submit" class="btn btn-primary">Update</button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

    </jsp:body>
</t:main>
