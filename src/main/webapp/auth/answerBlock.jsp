<jsp:include page="../WEB-INF/markingHeader.jsp"/>
<jsp:include page="../WEB-INF/menu.jsp"/>


    <a href="vote?vote=no&packageId=${packageId}&start=${start}&word=${word.getId()}">
        <div class="left-answer-box"></div>
    </a>

    <div class="inner cover">
        <h1 class="span3">${word.getWord()}</h1>
        <h3 class="span4"> ${type.getType()} </h3>
        <h3 class="span4">
            ${currentPosition} / ${fullSize}
        </h3>
        ${message}
    </div>

    <a href="vote?vote=yes&packageId=${packageId}&start=${start}&word=${word.getId()}">
        <div class="right-answer-box"></div>
    </a>

<jsp:include page="../WEB-INF/footer.jsp"/>
