<%@  page contentType="text/html;charset=UTF-8" language="java" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    </style>
</head>
<body>
<div  class="container-fluid h-100">
    <div  class="row h-100 justify-content-center align-items-center">
        <div  class="col-sm-4">
            <div><h4 class="titleConfirm">CONFIRM OTP</h4></div>
            <c:if  test="${hasErrorOTP}">
                <div  class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>OTP failed! </strong> ${errorMessageOTP}
                    <button  type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span  aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>

            <form  action="" method="post">
                <div  class="form-group">
                    <label  class="sr-only" for="txtOTP">Mã OTP gửi về mail</label>
                    <div  class="input-group">
                        <input  type="text" class="form-control form-control-lg border-right-0" id="txtOTP" name="OTP" autofocus placeholder="OTP">
                        <div  class="input-group-append">
                        <span  class="input-group-text bg-white">
                          <i  class="fa fa-user" aria-hidden="true"></i>
                        </span>
                        </div>
                    </div>
                </div>

                <div  class="form-group d-flex">
                    <div>
                        <a  class="btn btn-lg btn-outline-info" href="${pageContext.request.contextPath}/Account/Register" role="button">
                            <i  class="fa fa-home" aria-hidden="true"></i>
                            Back
                        </a>
                    </div>
                    <div  class="flex-fill ml-1">
                        <button  type="submit" class="btn btn-lg btn-info btn-block">
                            <i  class="fa fa-sign-in" aria-hidden="true"></i>
                             Confirm
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