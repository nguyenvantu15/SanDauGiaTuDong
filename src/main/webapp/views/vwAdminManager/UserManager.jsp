<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="listUser" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.User>"/>


<t:main>
    <jsp:body>
        <div class="card">
            <h4 class="card-header d-flex justify-content-between">
                Quản lí tài khoản
            </h4>

            <c:choose>
                <c:when test="${listUser.size() == 0}">
                    <div class="card-body">
                        <p class="card-text">Không có dữ liệu.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Pemission</th>
                                <th name="id">ID</th>
                                <th>UserName</th>
                                <th>Name</th>
                                <th>Email</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listUser}" var="u">
                                <tr>
                                    <form action="" id="frmUserManager" method="post">
                                        <td>${u.permission}</td>
                                        <td style="width: 172px;"><input type="text" name="id" value="${u.id}" style="width: 100%; border: none" disabled></td>
                                        <td>${u.username}</td>
                                        <td>${u.name}</td>
                                        <td>${u.email}</td>
                                    </form>
                                    <td class="text-right">
                                        <a href=" ${pageContext.request.contextPath}/Admin/AccountDetail?id=${u.id}">
                                        <button type="button" class="btn btn-outline-success" style="min-width: 80px" >
                                            Xem chi tiết
                                            <i class="fa fa-eye" aria-hidden="true"></i>
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