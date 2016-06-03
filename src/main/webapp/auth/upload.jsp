<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<jsp:include page="../WEB-INF/header.jsp"/>
<jsp:include page="../WEB-INF/menu.jsp"/>

    <div class="inner cover">
        <c:if test="${not empty alertMessage}">
            <div class="alert alert-danger">${alertMessage}</div>
        </c:if>
        <c:if test="${not empty message}">
            ${message}
        </c:if>

        <p style="padding-top: 10%">Upload .csv file to parse as package</p>
        <form action="Upload" method="post" enctype="multipart/form-data">

            <div class="form-group">
                <label for="package-name">Meno balíku:</label>
                <input type="text" name="packageName" id="package-name" class="form-control" required />
            </div>

            <div class="form-group">
                <label for="package-type">Typ balíku:</label>
                <input type="text" name="packageType" id="package-type" class="form-control" required />
            </div>

            <div class="form-group">
                <label for="my-file-selector">Súbor:</label>
                <input type="file" name="file" id="my-file-selector" accept=".csv" required />
            </div>

            <button type="submit" class="btn btn-default">Nahraj</button>
        </form>
        <span class='label label-info' id="upload-file-info"></span>
    </div>

<jsp:include page="../WEB-INF/footer.jsp"/>
