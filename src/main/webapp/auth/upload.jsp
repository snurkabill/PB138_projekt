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
        <label class="btn btn-primary btn-default " for="my-file-selector">
            <form action="Upload" method="post" enctype="multipart/form-data">
                Meno balíku: <input name="packageName" type="text" required="required"/>
                Typ balíku: <input name="packageType" type="text" required="required"/>
                Súbor: <input id="my-file-selector" name="file" type="file" required="required" accept=".csv" style="border-bottom-left-radius: 10px" onload="return 5" onchange="$('#upload-file-info').html($(this).val());">
                <input type="submit" value="Nahraj"/>
            </form>
        </label>
        <span class='label label-info' id="upload-file-info"></span>
    </div>

<jsp:include page="../WEB-INF/footer.jsp"/>
