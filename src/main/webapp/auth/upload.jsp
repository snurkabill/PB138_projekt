<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<jsp:include page="../WEB-INF/header.jsp"/>
<jsp:include page="../WEB-INF/menu.jsp"/>

    <div class="inner cover">
        <p style="padding-top: 10%">Upload .csv file to parse as package</p>
        <label class="btn btn-primary btn-default " for="my-file-selector">
            <input id="my-file-selector" type="file" accept=".csv" style="border-bottom-left-radius: 10px" onload="return 5" onchange="$('#upload-file-info').html($(this).val());">
        </label>
        <span class='label label-info' id="upload-file-info"></span>
    </div>

<jsp:include page="../WEB-INF/footer.jsp"/>
