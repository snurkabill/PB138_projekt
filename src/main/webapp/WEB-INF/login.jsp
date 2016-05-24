<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<jsp:include page="header.jsp" />

<div class="inner cover">
    <c:if test="${not empty message}">
        <div class="alert alert-danger">${message}</div>
    </c:if>

    <div>
        <h2>Login</h2>
        <form action="/Login" method="POST">

            <div class="form-group">
                <label>
                    username: <input type="text" name="username" class="form-control" />
                </label>
            </div>


            <div class="form-group">
                <label>
                    password:
                    <input type="password" name="password" class="form-control" />
                </label>
            </div>

            <button type="submit" class="btn btn-default">Login</button>
        </form>
    </div>

    <div>
        <h2>Create account</h2>
        <form action="/CreateUser" method="POST">

            <div class="form-group">
                <label>
                    username: <input type="text" name="username" class="form-control" />
                </label>
            </div>


            <div class="form-group">
                <label>
                    password:
                    <input type="password" name="password" class="form-control" />
                </label>
            </div>

            <button type="submit" class="btn btn-default">Create</button>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp"/>
