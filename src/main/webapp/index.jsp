<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>Annotator - home</title>
</head>
<body>
<h2>Annotator</h2>

    <%
    if (session.getAttribute("authenticatedUser") == null) {
    %>

    <form action="Login" method="post">
        Username: <input type="text" name="username"><br/>
        Password: <input type="password" name="password"><br/>
        <input type="submit" value="Login"/>
    </form>
    ${message}

    <%
    } else {
    %>

    <h3>Welcome, ${sessionScope.authenticatedUser} </h3>
    <a href="profile.jsp">User profile</a> <br/>

    Here may be a list of packages and upload button

    <a href="Logout">Logout</a>

    <%
        }
    %>
</body>
</html>
