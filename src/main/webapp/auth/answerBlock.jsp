<jsp:include page="../WEB-INF/markingHeader.jsp"/>
<jsp:include page="../WEB-INF/menu.jsp"/>

<div class="site-wrapper-body">

    <div class="left-answer-box" style="cursor: pointer;"
         onclick="window.location='marking-manager?vote=no&package=${packageId}&start=${start}&word=${word.getId()}';">
    </div>

    <div class="site-wrapper-inner">
        <div class="cover">
            <h1 class="span3">${word.getWord()}</h1>
            <h3 class="span4"> ${type.getType()} </h3>
            <h3 class="span4">
                ${currentPosition} / ${fullSize}
            </h3>
            ${message}
        </div>
    </div>

    <div class="right-answer-box" style="cursor: pointer;"
         onclick="window.location='marking-manager?vote=no&package=${packageId}&start=${start}&word=${word.getId()}';">
    </div>

</div>
<div class="mastfoot span2">
    <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a
            href="https://twitter.com/mdo">@mdo</a>.</p>
</div>

<jsp:include page="../WEB-INF/footer.jsp"/>
