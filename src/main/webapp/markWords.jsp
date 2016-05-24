<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<jsp:include page="WEB-INF/header.jsp"/>
<jsp:include page="WEB-INF/menu.jsp"/>

<div class="inner cover">
    <h1 class="cover-heading">Packages</h1>
    <ul class="">
        <c:forEach items="${types}" var="type">
            <li>
                ${type.getType()}

                <ul>
                    <c:forEach items="${typePackages.get(type.getId())}" var="pack">
                        <li><a href="">${pack.getName()}</a></li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>

    </ul>
</div>

<jsp:include page="WEB-INF/footer.jsp"/>
