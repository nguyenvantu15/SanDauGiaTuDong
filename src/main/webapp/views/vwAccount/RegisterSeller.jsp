<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="ListRegisterSeller" scope="request" type="java.util.List<com.ute.sandaugiatudong.beans.User>"/>


<t:main>
    <jsp:body>
        <div class="card">
<%--            <h4 class="card-header d-flex justify-content-between">--%>
<%--                Categories--%>
<%--                <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/Admin/Category/Add" role="button">--%>
<%--                    <i class="fa fa-plus" aria-hidden="true"></i>--%>
<%--                    Add Category--%>
<%--                </a>--%>
<%--            </h4>--%>
            <c:choose>
                <c:when test="${ListRegisterSeller.size() == 0}">
                    <div class="card-body">
                        <p class="card-text">Không có dữ liệu.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th style="font-size: 1.4rem; color: royalblue ; width: 10%">#</th>
                                <th style="font-size: 1.4rem; color: royalblue  ; width: 20%">Danh sách xin nâng cấp seller</th>
                                <th>&nbsp;</th>
                            </tr>
                            <tr>
                                <th name="id">ID</th>
                                <th>Username</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Permission</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${ListRegisterSeller}" var="s">
                                <form action="" method="post" id="frmSeller">
                                <tr>

                                    <td><input type="text" name="id" value="${s.id}" style="width: 100%; border: none" disabled></td>
                                    <td>${s.username}</td>
                                    <td>${s.name}</td>
                                    <td>${s.email}</td>
                                    <td>${s.phone}</td>
                                    <td>${s.permission}</td>

                                    <td class="text-right">
                                        <button type="submit" class="btn btn-outline-success" formaction="${pageContext.request.contextPath}/Account/Accept?id=${s.id}">
                                            <i class="fa fa-check" aria-hidden="true"></i>
                                        </button>
                                    </td>

                                </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </jsp:body>
</t:main>