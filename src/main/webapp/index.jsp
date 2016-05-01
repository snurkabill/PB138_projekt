<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Annotator - home</title>
</head>
<body>
<h2>Annotator</h2>

    <h3>Welcome, ${sessionScope.authenticatedUser} </h3>
    <a href="profile.jsp">User profile</a> <br/>

    Here may be a list of packages and upload button

    <form action="Logout" method="post">
        <input type="submit" value="Logout"/>
    </form>
</body>
</html>
