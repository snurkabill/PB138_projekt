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

        <%
            if (session.getAttribute("authenticatedUser") == null) {
        %>

        <div class="well well-spec navbar navbar-inverse">
            <ul class="nav nav-tabs ">
                <li><a class="span2">Welcome! You need to be logged in to mark words </a></li>
            </ul>
        </div><!--/.well -->
    </div>
    <div class="site-wrapper-body inner">
        <div class="site-wrapper-inner paddingL">
            <div class="span4">
                <p>Login</p>
            </div>
            <form action="Login" method="post">
                Username: <input type="text" name="username"><br/>
                Password: <input type="password" name="password"><br/>
                <input type="submit" value="Login"/>
            </form>
            ${message}
        </div>
        <div class="span4">
            <p>Or</p>
        </div>
        <div class="site-wrapper-inner paddingR">
            <div class="span4">
                <p>Sing Up</p>
            </div>
            <form action="CreateUser" method="post">
                Username: <input type="text" name="username"><br/>
                Password: <input type="password" name="password"><br/>
                <input type="submit" value="Sing up"/>
            </form>
            ${message}
        </div>
    </div>

    <%
    } else {
    %>

    <div class="well well-spec navbar navbar-inverse">
        <ul class="nav nav-tabs ">
            <li><a href="index.jsp" class="span2 active">Logged as ${sessionScope.authenticatedUser}</a></li>
            <li><a href="Logout">Logout</a></li>
            <li class=><a href="markWords.jsp">Mark Words</a></li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Statistics
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li class="menuFont"><a href="#">Vote ratio</a></li>
                    <li class="menuFont"><a href="#">Average word duration</a></li>
                    <li class="menuFont"><a href="#">Cohen </a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Export statistic</a></li>
                </ul>
            </li>
            <li><a href="upload.jsp">Upload Package</a></li>

        </ul>
    </div><!--/.well -->
</div>
<div class="site-wrapper-body span2">
    <p style="padding-top: 10%">
        Vitajte v aplikácii na značkovanie slov, pre potreby následnej štúdie ich novodobého lexikálneho významu.
    </p>
    <p>
        Pre začatie značkovatia, kliknite na <b>Mark Words</b>, kde sa vám zobrazí Váš zoznam balíčkov, vrátane ich
        vyplnenia Vami. <br>
        Vyhodnotenie a prípadný export štatistík nájdete v sekcii <b>Statistics</b>. <br>
        Ak chcete vložiť nové balíčky slov vo formáte CSV (ine??), kliknite na <b>Upload Package</b>
    </p>
</div>

<%
    }
%>

<div class="mastfoot span2">
    <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.
    </p>
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
