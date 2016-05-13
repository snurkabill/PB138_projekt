<%@ page import="annotator.model.user.UserRepository" %>
<%@ page import="com.mongodb.client.MongoDatabase" %>
<%@ page import="annotator.server.ContextListener" %>
<%@ page import="annotator.model.activepackage.ActivePackageRepository" %>
<%@ page import="org.bson.Document" %>
<%@ page import="annotator.model.user.User" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="">

    <title>Home</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <link href="bootstrap/css/cover.css" rel="stylesheet" type="text/css">

</head>

<body>
<div class="site-wrapper">
    <div class="masthead clearfix">
        <div class="well well-spec navbar navbar-inverse">
            <ul class="nav nav-tabs ">
                <li ><a href="index.jsp" class="span2">Logged as ${sessionScope.authenticatedUser}</a></li>
                <li><a href="Logout">Logout</a></li>
                <li class="active"><a href="markWords.jsp">Mark Words</a></li>
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
        <ul class="nav nav-list package-nav">
            <%
                try {
                String email = (String)session.getAttribute("authenticatedUser");
                MongoDatabase database = (MongoDatabase)session.getServletContext().getAttribute("database");
                User user = new User(email, database);
                ActivePackageRepository repository = new ActivePackageRepository( database,user.getId());
                Document pack = repository.getNext();
                while (pack != null) {

            %>
            <li class="menuFont"><a class="menuFont" href="answerBlock.jsp"> Answer </a></li>
            <%
                    }
                }catch (Exception e ){
                    e.printStackTrace();
                }
            %>
        </ul>
    </div>
<div class="mastfoot span2">
    <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
</div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<!--&lt;!&ndash; IE10 viewport hack for Surface/desktop Windows 8 bug &ndash;&gt;-->
<!--<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>-->
</body>
</html>
