<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="annotator.model.user.UserRepository" %>
<%@ page import="com.mongodb.client.MongoDatabase" %>
<%@ page import="annotator.server.ContextListener" %>
<%@ page import="annotator.model.activepackage.ActivePackageRepository" %>
<%@ page import="org.bson.Document" %>
<%@ page import="annotator.model.user.User" %>
<%@ page import="com.mongodb.client.MongoCollection" %>
<%@ page import="java.util.Map" %>
<%@ page import="annotator.model.activepackage.ActivePackage" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="annotator.model.pack.Package" %>
<%@ page import="annotator.model.pack.PackageRepository" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<jsp:include page="common/header.jsp"/>
<jsp:include page="common/menu.jsp"/>

    <div class="site-wrapper-body">
        <p style="padding-top: 10%">Packages</p>
        <ul class="">
            <c:forEach items="${types}" var="type">
                <li>
                    ${type.getType()}

                    <ul>
                        <c:forEach items="${typePackages.get(type.getId())}" var="pack">
                            <li>${pack.getName()}</li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>

        </ul>
    </div>
    <div class="mastfoot span2">
        <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a
                href="https://twitter.com/mdo">@mdo</a>.</p>
    </div>

<jsp:include page="common/footer.jsp"/>
