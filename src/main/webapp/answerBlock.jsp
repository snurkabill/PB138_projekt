<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="Kejsty">
    <link rel="icon" href="">

    <title>Package</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <link href="bootstrap/css/answerBox.css" rel="stylesheet" type="text/css">

</head>

<body>

<div class="site-wrapper">
    <div class="masthead clearfix">
        <div class="well navbar navbar-inverse">
            <ul class=" nav nav-tabs ">
                <li ><a  class="span2">Logged As </a></li>
                <li><a href="#">LogOut</a></li>
                <li class="active"><a href="#">Marking</a></li>
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
                <li><a href="markWords.jsp">Back</a></li>

            </ul>
        </div>
    </div>

    <div class="site-wrapper-body">

        <div class="left-answer-box" style="cursor: pointer;" onclick="window.location='correct.html';">
        </div>

        <div class="site-wrapper-inner">
            <div class="cover">
                <h1 class="span3" >Word.</h1>
                <h3 class="span4"> class </h3>
                <h3 class="span4">
                    (1/1000)
                </h3>
            </div>
        </div>

        <div class="right-answer-box" style="cursor: pointer;" onclick="window.location='correct.html';">
        </div>

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
</body>
</html>
