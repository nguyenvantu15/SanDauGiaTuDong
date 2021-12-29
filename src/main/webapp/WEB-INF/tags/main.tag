<%@ tag pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="css" fragment="true" required="false" %>
<%@ attribute name="js" fragment="true" required="false" %>


<jsp:useBean id="CategoryWithDetails" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Product>"/>
<jsp:useBean id="TypeWithDetails" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Type>"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>San dau gia</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        body{
            font-size: 1.2rem;
            box-sizing: border-box;
            margin: 0;
        }
        .navbar{
            background-color:#222838;
            width: 80%;
            margin: auto;
            padding: 0;
            height: 4rem !important;
        }
        .nav-tren{
            background-color: #222838;
            height: 4rem !important;
            align-items: center;
        }

        .form-inline{
            flex: 1;
        }
        .btn_search{
            font-size: 1.1rem;
            margin-left: 5px;
        }
        .nav-link{
            color: white;
            margin-left: 10px;
        }
        .form-control-sm{
            border-radius: 5px;
            font-size: 1.1rem;
            width: 50%;
            outline: none;
        }
        .nav-duoi{
            height: 2.5rem;
            background-color: #d99f3f;
        }
        .danhmuc{
            align-items: center;
            margin: auto;
            display: flex;
            height: 100%;
            width: 80%;
            justify-content: space-between;
            padding: 0;
            box-sizing: border-box;
        }
        .dropbtn-danhmuc {
            background-color: transparent;
            color: black;
            font-weight: 600;
            border: none;
            width: 100%;
            height: 100%;
            line-height: 2.5rem;
            display: flex;
            justify-content: space-between;
            padding-right: 10px;
            padding-left: 10px;
        }
        .dropdown-danhmuc :hover{
            color: #5e9de1;
        }
        .dropdown-danhmuc {
            position: relative;
            width: 25%;
            height: 100%;
            display: inline-block;
            align-items: stretch;

        }

        .dropdown-content-danhmuc {
            display: none;
            position: absolute;
            width: 100%;
            background-color: #f1f1f1;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content-danhmuc a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content-danhmuc a:hover {
            background-color: #ddd;

        }

        .dropdown-danhmuc:hover .dropdown-content-danhmuc {
            display: block;
        }

        .dropdown-danhmuc:hover .dropbtn-danhmuc {
            background-color: white;
            text-decoration: none;

        }
        .textDrop{
            margin-left: 0;
        }
        .fa-caret-down{
            margin: auto;
            margin-right: 0px;
        }
        .fanav{
            margin: auto;
            margin-right: 0px;
        }
        .body{
            width: 80%;
            margin: auto;
        }
        .col-body
        {
            margin: auto;
        }
        .fa-home{
            color: #fcd001;
        }
        .selectType{
            height: 35px;
        }
        .selectType select,button{
            height: 100%;
        }

    </style>


    <%--    Lỗ css --%>
    <jsp:invoke fragment = "css"/>
</head>
<body>
<div class="nav-tren">
    <nav class="navbar navbar-expand-sm">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Home/">
            <i class="fa fa-home fa-2x" aria-hidden="true"></i>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-1-">
                <li class="nav-item dropdown">
                </li>
            </ul>
            <form id="frmSearch" action="${pageContext.request.contextPath}/SearchControl/Search" method="get" class="form-inline my-2 my-lg-0">
                <input class="form-control-sm mr-sm-2 " id="search" type="text" name="txtsearch" placeholder="Search" >

                <div class="input-group selectType">
                    <select class="custom-select" name="searchType" id="searchType">
                        <option style="height: 50px" value="1" selected>Tìm kiếm theo tên</option>
                        <option value="2" >Tìm kiếm theo tinyDes</option>
                    </select>
                    <div class="input-group-append">
                        <button  type="submit" name="btnSearch" class="btn btn-outline-success btn_search btn-sm">Search</button>
                    </div>
                </div>

            </form>

        </div>
        <ul class="navbar-nav mr-0">
            <c:choose>
                <c:when test="${auth==0}">
                    <li class="nav-item ">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Account/Register">Đăng Ký</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Account/Login">Đăng Nhập</a>
                    </li>

                </c:when>
                <c:otherwise>
                    <form id="frmLogout" action="${pageContext.request.contextPath}/Account/Logout" method="post"></form>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownR" role="button" data-toggle="dropdown" aria-expanded="false">
                                Hi, <b>${authUser.username}</b>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                <c:if test="${auth ==2}">
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Manage/Userproduct">
                                        <i class="fa fa-truck" aria-hidden="true"></i>
                                        Sản phẩm của bạn
                                    </a>

                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Manage/ViewSellerProAuction">
                                        <i class="fa fa-money" aria-hidden="true"></i>
                                        Đang đấu giá
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Manage/Productsold">
                                        <i class="fa fa-check" aria-hidden="true"></i>
                                        Đã bán
                                    </a>

                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Misc/Add">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                        Thêm sản phẩm
                                    </a>

                                </c:if>

                                <c:if test="${auth == 1}">
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Behavior/viewwatchlist">
                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                        Watch List
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Behavior/Producbidderauction">
                                        <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                        Đang đấu giá
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Behavior/ProductWin">
                                        <i class="fa fa-money" aria-hidden="true"></i>
                                        Sản phẩm đã thắng
                                    </a>
                                </c:if>

<%--                                account admin có permission = 3 được xem danh sách chấp nhận seller--%>
                                <c:if test="${auth == 3}">
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/RegisterSeller">
                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                        List Access Seller
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Admin/UserManager">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                        Account User Manager
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Admin/CategoryManager">
                                        <i class="fa fa-list-alt" aria-hidden="true"></i>
                                        Category Manager
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/Admin/TypeManager">
                                        <i class="fa fa-list" aria-hidden="true"></i>
                                        Type Product Manager
                                    </a>


                                </c:if>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/Account/Profile">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                    Profile
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="javascript: $('#frmLogout').submit()">
                                    <i class="fa fa-sign-out" aria-hidden="true"></i>
                                    Sigh Out
                                </a>
                            </div>
                        </li>

                </c:otherwise>
            </c:choose>

        </ul>
    </nav>
</div>
<div class="nav-duoi">
    <ul class="danhmuc">
        <c:forEach items="${CategoryWithDetails}" var="c">

            <li class="dropdown-danhmuc">
                <a href="${pageContext.request.contextPath}/Product/Category?id=${c.id}" class="dropbtn-danhmuc">
                    <div>
<%--                        <i class="fa fa-microchip fanav" aria-hidden="true"></i>--%>
                        <span class="textDrop">${c.name}</span>
                    </div>
                    <i class="fa fa-caret-down"></i>
                </a>
                <div class="dropdown-content-danhmuc">
                    <c:forEach items="${TypeWithDetails}" var="d">
                        <c:if test="${d.idCat == c.id}">
                            <a href="${pageContext.request.contextPath}/Product/Type?id=${d.id}">${d.name}</a>
                        </c:if>
                    </c:forEach>

                </div>
            </li>
            </a>
        </c:forEach>
    </ul>
</div>
<div class="col col-10 col-body mt-5">
    <jsp:doBody/>
</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js" integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2" crossorigin="anonymous"></script>
<script>
    $('#frmSearch').on('submit', function(e){
        e.preventDefault();
        const txtS = $('#search').val();
        if(txtS.length !== 0)
            $('#frmSearch').off('submit').submit();
    });
</script>
<%--Lỗ js--%>
<jsp:invoke fragment="js"/>
</body>
</html>