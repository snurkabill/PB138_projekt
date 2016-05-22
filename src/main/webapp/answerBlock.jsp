<%@ page import="annotator.model.pack.PackageRepository" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.mongodb.client.MongoDatabase" %>
<%@ page import="annotator.model.activepackage.ActivePackage" %>
<%@ page import="annotator.model.activepackage.ActivePackageRepository" %>
<%@ page import="annotator.model.pack.Package" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="annotator.model.word.Word" %>
<%@ page import="annotator.model.word.WordRepository" %>
<%@ page import="annotator.model.type.Type" %>
<%@ page import="annotator.model.type.TypeRepository" %>
<%@ page import="annotator.model.user.User" %>
<%@ page import="java.time.temporal.Temporal" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.Period" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="annotator.model.vote.VoteRepository" %>
<%@ page import="java.time.LocalDateTime" %>

<jsp:include page="common/header.jsp"/>

    <div class="masthead clearfix">
        <div class="well navbar navbar-inverse">
            <ul class=" nav nav-tabs ">
                <li><a class="span2">Logged As </a></li>
                <li><a href="Logout">Logout</a></li>
                <li class="active"><a href="#">Marking</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Statistics
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="menuFont"><a href="#">Vote ratio</a></li>
                        <li class="menuFont"><a href="#">Average word duration</a></li>
                        <li class="menuFont"><a href="#">Cohen cappa</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Export statistic</a></li>
                    </ul>
                </li>
                <li><a href="package-list">Back</a></li>

            </ul>
        </div>
    </div>

    <div class="site-wrapper-body">

        <div class="left-answer-box" style="cursor: pointer;" onclick="window.location='answerBlock.jsp?answer=no';">
        </div>
        <div class="site-wrapper-inner">
            <div class="cover">
                <%
                    LocalDateTime start = LocalDateTime.now();
                    session.setAttribute("start", start);
                    if (request.getParameter("active") != null || request.getParameter("unactive") != null) {
                        try {
                            ActivePackage activePackage;
                            MongoDatabase database = (MongoDatabase) session.getAttribute("database");
                            PackageRepository packageRepository = new PackageRepository(database);
                            ActivePackageRepository activePackageRepository = new ActivePackageRepository(database);
                            Package pack;
                            if (request.getParameter("unactive") != null) {
                                System.out.println(request.getParameter("unactive"));
                                pack = packageRepository.getPackage(request.getParameter("unactive"));
                                activePackage = activePackageRepository.
                                        makeNew(pack,
                                                ((User) session.getAttribute("loggedUser")).getId());
                            } else {
                                System.out.println(request.getParameter("active"));
                                activePackage = activePackageRepository.getActivePackage(request.getParameter("active"));
                                pack = packageRepository.getPackage(activePackage.getPackageId());
                            }

                            if (activePackage.getProgress().equals(pack.getWordCount())) {
                                response.sendRedirect("package-list");
                            }
                            ArrayList<String> words = pack.getWordList();
                            session.setAttribute("pack", pack);
                            session.setAttribute("activePackage", activePackage);
                            WordRepository wordRepository = new WordRepository(database);
                            TypeRepository typeRepository = new TypeRepository(database);
                            Word word = wordRepository.getWord(words.get(activePackage.getProgress()));
                            session.setAttribute("word", word);
                            Type type = typeRepository.getType(word.getTypeId());
                            session.setAttribute("type", type);
                %>
                <h1 class="span3">${sessionScope.word.getWord()}</h1>
                <h3 class="span4"> ${sessionScope.type.getType()} </h3>
                <h3 class="span4">
                    ${sessionScope.activePackage.getProgress()} / ${sessionScope.pack.getWordCount()}
                </h3>
                <%
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                %>
                <h1> Something wrong happend</h1>
                <h3 class="span4"> Package not found </h3>
                <%
                        }
                } else if(request.getParameter("answer") != null) {
                    LocalDateTime end = LocalDateTime.now();
                    long duration2 = ((LocalDateTime) session.getAttribute("start")).until(end, ChronoUnit.MILLIS);
                    Integer duration = ((Long) duration2).intValue();
                    VoteRepository voteRepository = new VoteRepository((MongoDatabase) session.getAttribute("database"));
                    Boolean result = request.getParameter("answer").equals("yes");
                    voteRepository.addVote(((User) session.getAttribute("loggedUser")).getId(),
                    (Word) session.getAttribute("word"), result, duration);
                    ActivePackage activePackage = (ActivePackage) session.getAttribute("activePackage");
                    activePackage.increaseProgress((MongoDatabase) session.getAttribute("database"));
                    response.sendRedirect("answerBlock.jsp?active=".concat(activePackage.getId()));
                } else {
                %>
                <h1> Something wrong happend</h1>
                <h3 class="span4"> chosenPackage is null </h3>
                <%
                    }
                %>
            </div>
        </div>

        <div class="right-answer-box" style="cursor: pointer;" onclick="window.location='answerBlock.jsp?answer=yes';">
        </div>

    </div>
    <div class="mastfoot span2">
        <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a
                href="https://twitter.com/mdo">@mdo</a>.</p>
    </div>


<jsp:include page="common/footer.jsp"/>
