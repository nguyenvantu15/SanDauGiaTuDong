<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="listType" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Type>"/>
<jsp:useBean id="listPrOfType" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.Count>"/>


<t:main>
    <jsp:body>
        <div class="card">
            <h4 class="card-header d-flex justify-content-between">
                Quản lí cái loại sản phẩm trong danh mục
                <a class="btn btn-outline-primary mr-2" href=" ${pageContext.request.contextPath}/Admin/AddType" role="button">
                    <i class="fa fa-plus" aria-hidden="true"></i>
                    Thêm
                </a>
            </h4>
            <c:if  test="${hasErrorType}">
                <div  class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>Error! </strong> ${errorMessageType}
                    <button  type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span  aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <c:choose>
                <c:when test="${listType.size() == 0}">
                    <div class="card-body">
                        <p class="card-text">Không có dữ liệu.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th >ID Category</th>
                                <th>ID</th>
                                <th>Tên</th>
                                <th>Số lượng sản phẩm có trong thể loại</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listType}" var="t">
                                <tr>
                                    <form action="" id="frmAdminManager" method="post">
                                    <td style="width: 172px;">${t.idCat}</td>
                                    <td style="width: 172px;"><input type="text" name="id" value="${t.id}" style="width: 100%; border: none" disabled></td>
                                    <td>${t.name}</td>
                                        <td>
                                            <c:forEach items="${listPrOfType}" var="p">
                                                <c:if test="${t.id == p.id}">
                                                    ${p.count}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                    <td class="text-right">
                                        <button type="submit" class="btn btn-outline-danger" style="width: 80px" formaction="${pageContext.request.contextPath}/Admin/RemoveType?id=${t.id} ">
                                            Xóa
                                            <i class="fa fa-times" aria-hidden="true"></i>
                                        </button>
                                    </td>
                                    </form>
                                    <td class="text-right">
                                        <a href=" ${pageContext.request.contextPath}/Admin/UpdateType?id=${t.id}">
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