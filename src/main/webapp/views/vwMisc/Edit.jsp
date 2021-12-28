<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="ProductEdit" scope="request" type="com.ute.sandaugiatudong.beans.Product"/>
<jsp:useBean id="listType" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Type>"/>
<jsp:useBean id="listCate" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Category>"/>


<t:main>
    <jsp:attribute name="css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" integrity="sha512-f0tzWhCwVFS3WeYaofoLWkTP62ObhewQ1EZn65oSYDZUg1+CyywGKkWzm8BxaJj5HGKI72PnMH9jYyIFz+GH7g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.2.5/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap');

            *{
                padding: 0;
                margin: 0;
                box-sizing: border-box;
            }

            html, body{
                font-family: 'Roboto', sans-serif;
                background-color: #1a233a;
            }

            img{
                width: 100%;
                display: block;
            }
            .container{
                max-width: 1200px;
                padding: 0 1rem;
                margin: 0 auto;
            }
            .product-div{
                margin: 1rem 0;
                padding: 2rem 0;
                display: grid;
                grid-template-columns: repeat(2, 1fr);
                background-color: #ffffff;
                border-radius: 3px;
                column-gap: 10px;
            }
            .product-div-left{
                padding: 20px;
            }
            .product-div-right{
                padding: 20px;
            }
            .img-container{
                height: 400px;
            }
            .img-container img{
                width: 400px;
                margin: 0 auto;
                border-radius: 5px;
            }
            .hover-container{
                display: flex;
                flex-wrap: wrap;
                align-items: center;
                justify-content: center;
                margin-top: 32px;
            }
            .hover-container div{
                border: 2px solid rgba(252, 160, 175, 0.7);
                padding: 1rem;
                border-radius: 3px;
                margin: 0 4px 8px 4px;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .hover-container div:hover{
                border-color: rgb(255, 145, 163);
            }
            .hover-container div img{
                width: 50px;
                cursor: pointer;
            }
            .product-div-right span{
                display: block;
            }

            .product-rating span{
                margin-right: 6px;
            }

            .btn-groups{
                margin-top: 22px;
            }
            .btn-groups button{
                display: inline-block;
                font-size: 16px;
                font-family: inherit;
                text-transform: uppercase;
                padding: 15px 16px;
                color: #fff;
                cursor: pointer;
                transition: all 0.3s ease;
            }
            .btn-groups button .fas{
                margin-right: 8px;
            }


            @media screen and (max-width: 992px){
                .product-div{
                    grid-template-columns: 100%;
                }
                .product-div-right{
                    text-align: center;
                }

            }

            @media screen and (max-width: 400px){
                .btn-groups button{
                    width: 100%;
                    margin-bottom: 10px;
                }
            }
            .color_primary{
                color: #d99f3f;
            }

        </style>
    </jsp:attribute>

    <jsp:attribute name="js">
        <script src="https://cdn.tiny.cloud/1/5nmlpb2qma707p8n50ohwl9uai2afuehks9mqypol4rj4wf6/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js" integrity="sha512-AIOTidJAcHBH2G/oZv9viEGXRqDNmfdPVPYOYKGy3fti0xIplnlgMHUGfuNRzC6FkzIo0iIxgFnr9RikFxK+sw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdn.jsdelivr.net/gh/kartik-v/bootstrap-fileinput@5.2.5/js/fileinput.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.2.5/themes/fa/theme.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.2.5/js/locales/vi.min.js"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script>

            const allHoverImages = document.querySelectorAll('.hover-container div img');
            const imgContainer = document.querySelector('.img-container');

            window.addEventListener('DOMContentLoaded', () => {
                allHoverImages[0].parentElement.classList.add('active');
            });

            allHoverImages.forEach((image) => {
                image.addEventListener('mouseover', () =>{
                    imgContainer.querySelector('img').src = image.src;
                    resetActiveImg();
                    image.parentElement.classList.add('active');
                });
            });

            function resetActiveImg(){
                allHoverImages.forEach((img) => {
                    img.parentElement.classList.remove('active');
                });
            }
        </script>
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
                entity_encoding: "raw",
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

       <script>
           var today = new Date();
           var date = today.getDate()+'-'+(today.getMonth()+1)+'-'+today.getFullYear();
           var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
           var dateTime = date+' '+time;

           document.getElementById("hvn").innerHTML = dateTime;
       </script>
    </jsp:attribute>

    <jsp:body>

        <div class = "container">
            <div class = "product-div">
                <div class = "product-div-left">
                    <div class = "img-container">
                        <img src = "${pageContext.request.contextPath}/public/imgs/${ProductEdit.id}/1.jpg" alt = "watch">
                    </div>
                    <div class = "hover-container">
                        <div><img src = "${pageContext.request.contextPath}/public/imgs/${ProductEdit.id}/1.jpg"></div>
                        <div><img src = "${pageContext.request.contextPath}/public/imgs/${ProductEdit.id}/2.jpg"></div>
                        <div><img src = "${pageContext.request.contextPath}/public/imgs/${ProductEdit.id}/3.jpg"></div>
                        <div><img src = "${pageContext.request.contextPath}/public/imgs/${ProductEdit.id}/4.jpg"></div>
                    </div>
                </div>

                <form action="" method="post" id="frmEditPro" enctype="multipart/form-data">
                    <div class = "product-div-right">
                        <h4 class="color_primary mb-3">Chỉnh sửa sản phẩm</h4>

                        <div class="color_primary mb-3">Tên sản phẩm
                            <input type="text" value="${ProductEdit.name}" class="form-control" id="txtProName" placeholder="" name="name">
                        </div>

                        <div class="color_primary mb-3">Giá hiện tại
                            <input type="number" value="${ProductEdit.price}" class="form-control" id="txtProPrice" placeholder="" name="price">
                        </div>

                        <div class="color_primary mb-3">Danh mục
                            <select class="form-control" id="txtCategory" name="listCate">


                                <c:forEach items="${listCate}" var="c">
                                    <c:choose>
                                        <c:when test="${ProductEdit.idCat == c.id}">
                                            <option selected>${c.id}.${c.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>${c.id}.${c.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="color_primary mb-3">Loại
                            <select class="form-control" id="txtProType" name="ProType">
                                <c:forEach items="${listType}" var="t">
                                    <c:choose>
                                        <c:when test="${ProductEdit.idType == t.id}">
                                            <option selected>${t.id}.${t.name}&nbsp;&nbsp;&ndash;&ndash;${t.idCat} </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option >${t.id}.${t.name}&nbsp;&nbsp;&ndash;&ndash;${t.idCat} </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="color_primary mb-3">Thời gian bắt đầu:  ${ProductEdit.timeStart}

                            <input type="datetime-local" class="form-control"  value="${ProductEdit.timeStart}"  name="timeStart">
                        </div>

                        <div class="color_primary mb-3">Thời gian kết thúc: ${ProductEdit.timeEnd}
                            <input type="datetime-local" class="form-control" value="${ProductEdit.timeEnd}" name="timeEnd">
                        </div>

                        <div class="color_primary mb-3">Mô tả
                            <input type="text" class="form-control" id="txtTinyDes" value="${ProductEdit.tinyDes}" placeholder="" name="tinyDes">
                        </div>

                        <div class="color_primary mb-3">Mô tả chi tiết
                            <div id="hvn"></div>
                            <textarea type="text" id="txtFullDes" name="fullDes" >${ProductEdit.fullDes}</textarea>
                        </div>

                        <div class = "btn-groups  justify-content-between" >

                            <button type = "submit" href="${pageContext.request.contextPath}/Misc/Edit?id=${ProductEdit.id}" class = "btn btn-success text-white"><i class = "fas  fa-save fa-lg" style="color: white"></i>Save</button>
                            <a href="${pageContext.request.contextPath}/Manage/Userproduct">
                                <button type = "button" class = "btn btn-danger text-white" ><i class = "fas fa-times fa-lg" style="color: white"></i>Exit</button>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </jsp:body>
</t:main>