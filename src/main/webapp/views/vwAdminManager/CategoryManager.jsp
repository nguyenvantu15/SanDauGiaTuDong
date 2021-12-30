<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="listCategory" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Category>"/>
<jsp:useBean id="listTypeCa" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Count>"/>

<t:main>
    <jsp:attribute name="js">
    <script>

    </script>
        <script>

        </script>

     </jsp:attribute>
    <jsp:body>
        <div class="card">
            <h4 class="card-header d-flex justify-content-between">
                Quản lí danh mục
                <a class="btn btn-outline-primary mr-2" href=" ${pageContext.request.contextPath}/Admin/AddCategory" role="button">
                    <i class="fa fa-plus" aria-hidden="true"></i>
                    Thêm
                </a>
            </h4>
            <c:if  test="${hasErrorCate}">
                <div  class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>Error! </strong> ${errorMessageCate}
                    <button  type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span  aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <c:choose>
                <c:when test="${listCategory.size() == 0}">
                    <div class="card-body">
                        <p class="card-text">Không có dữ liệu.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th >ID</th>
                                <th>Tên</th>
<%--                                ////////--%>
                                <th>Số lượng thể loại</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listCategory}" var="c">
                                <tr>
                                    <form action="" id="frmCateManager" name="frmCateManager" method="post">
                                    <td style="width: 172px;"><input type="text" id="id" name="id" value="${c.id}" style="width: 100%; border: none" disabled ></td>
                                    <td><input type="text" name="name" value="${c.name}" id="nameCate" style="width: 100%; border: none" disabled></td>

                                        <td>
                                            <c:forEach items="${listTypeCa}" var="t">
                                                <c:if test="${c.id == t.id}">
                                                    ${t.count}
                                                </c:if>
                                            </c:forEach>
                                        </td>

                                    <td class="text-right">
                                        <button type="submit" class="btn btn-outline-danger" style="width: 80px" formaction="${pageContext.request.contextPath}/Admin/RemoveCate?id=${c.id} ">
                                            Xóa
                                            <i class="fa fa-times" aria-hidden="true"></i>
                                        </button>
                                    </td>
                                    </form>
                                    <td class="text-right">
                                        <a href=" ${pageContext.request.contextPath}/Admin/UpdateCategory?id=${c.id}">
                                        <button type="button" class="btn btn-outline-success" style="width: 80px" >
                                            Sửa
                                            <i class="fa fa-pencil" aria-hidden="true"></i>
                                        </button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </jsp:body>
</t:main>