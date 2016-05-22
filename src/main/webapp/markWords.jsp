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

    <div class="masthead clearfix">
        <div class="well well-spec navbar navbar-inverse">
            <ul class="nav nav-tabs ">
                <li><a href="index.jsp" class="span2">Logged as ${sessionScope.authenticatedUser}</a></li>
                <li><a href="Logout">Logout</a></li>
                <li class="active"><a href="package-list">Mark Words</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Statistics
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="menuFont"><a href="#">Vote ratio</a></li>
                        <li class="menuFont"><a href="#">Average word duration</a></li>
                        <li class="menuFont"><a href="#">Cohen cappa</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Export statistic</a></li>
                    </ul>
                </li>
                <li><a href="upload.jsp">Upload Package</a></li>

            </ul>
        </div><!--/.well -->
    </div>
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
