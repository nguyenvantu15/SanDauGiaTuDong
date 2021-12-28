<%@  page contentType="text/html;charset=UTF-8" language="java" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--<jsp:useBean id="rowNew" scope="request" type="com.ute.sandaugiatudong.beans.HistoryAuction"/>--%>
<%--<jsp:useBean id="product" scope="request" type="com.ute.sandaugiatudong.beans.Product"/>--%>
<%--<jsp:useBean id="priceNew" scope="request" type="java.lang.Integer"/>--%>

<!DOCTYPE  html>
<html  lang="en">

<head>
    <meta  charset="UTF-8">
    <title>San Dau Gia Tu Dong</title>
    <link  rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link  rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            height: 100vh;
            background-color: #1a233a;
        }
        .titleConfirm{
            font-size: 2rem;
            color: white;
            margin-bottom: 10px;
        }
        .product-price{
            font-weight: 700;
            font-size: 24px;
            opacity: 0.9;
            font-weight: 500;
        }
    </style>
</head>
<body>
<div  class="container-fluid h-100">
    <div  class="row h-100 justify-content-center align-items-center">
        <div  class="col-sm-4">
            <div><h4 class="titleConfirm">Xác nhận đấu giá:</h4></div>
            <form  action="" method="post">
                <div  class="form-group">
                    <span class = "product-price text-danger">Giá hiện tại: <fmt:formatNumber value="${product.price}" type="number" /> vnd</span>
                    <br>
                    <span class = "product-price text-danger">Giá tối đa của bạn: <fmt:formatNumber value="${priceNew}" type="number" /> vnd</span>
                </div>
                <div  class="form-group d-flex">
                    <div>
                        <a  class="btn btn-lg btn-outline-info" href="${pageContext.request.contextPath}/Behavior/Auction" role="button">
                            <i  class="fa fa-home" aria-hidden="true"></i>
                            Back
                        </a>
                    </div>
                    <div  class="flex-fill ml-1">
                        <button  type="submit" class="btn btn-lg btn-info btn-block">
                            <i  class="fa fa-sign-in" aria-hidden="true"></i>
                            Xác nhận
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<script  src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
<script  src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script  src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
</body>
</html>