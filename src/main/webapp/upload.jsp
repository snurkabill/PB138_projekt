<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<jsp:include page="common/header.jsp"/>
<jsp:include page="common/menu.jsp"/>

    <div class="site-wrapper-body">
        <p style="padding-top: 10%">Upload .csv file to parse as package</p>
        <label class="btn btn-primary btn-default " for="my-file-selector">
            <input id="my-file-selector" type="file" accept=".csv" style="border-bottom-left-radius: 10px" onload="return 5" onchange="$('#upload-file-info').html($(this).val());">
        </label>
        <span class='label label-info' id="upload-file-info"></span>
    </div>
    <div class="mastfoot span2">
        <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
    </div>

<jsp:include page="common/footer.jsp"/>
