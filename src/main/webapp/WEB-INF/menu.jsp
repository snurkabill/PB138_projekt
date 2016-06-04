<div class="clearfix">
    <div class="inner">
        <h3 class="masthead-brand"><a href="index.jsp">Annotator</a></h3>
        <nav>
            <ul class="nav masthead-nav">
                <li><a href="package-list">Mark Words</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Export Statistics
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="menuFont"><a href="/auth/Export?mode=word">Word statistics</a></li>
                        <li class="menuFont"><a href="/auth/Export?mode=user">Your's Statistics</a></li>
                        <li class="menuFont"><a href="/auth/Export?mode=allUser">All User's Statistics</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/auth/Export?mode=all">All possible Statistics</a></li>
                    </ul>
                </li>
                <li><a href="upload.jsp">Upload Package</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        ${sessionScope.authenticatedUser}
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="menuFont"><a href="/auth/Logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>
