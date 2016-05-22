<div class="masthead clearfix">
    <div class="well well-spec navbar navbar-inverse">
        <ul class="nav nav-tabs ">
            <li><a href="index.jsp" class="span2">Logged as ${sessionScope.authenticatedUser}</a></li>
            <li><a href="Logout">Logout</a></li>
            <li><a href="package-list">Mark Words</a></li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    Statistics
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li class="menuFont"><a href="#">Vote ratio</a></li>
                    <li class="menuFont"><a href="#">Average word duration</a></li>
                    <li class="menuFont"><a href="#">Cohen cappa</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Export statistics</a></li>
                </ul>
            </li>
            <li><a href="upload.jsp">Upload Package</a></li>
        </ul>
    </div>
</div>
