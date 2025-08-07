<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
                <form method="GET" action="${pageContext.request.contextPath}/matches">
                    <c:choose>
                        <c:when test="${filterByName != null}">
                            <input class="input-filter" type="text" name="filterByName" value="${filterByName}" />
                        </c:when>
                        <c:otherwise>
                            <input class="input-filter" type="text" name="filterByName" placeholder="Filter by name" />
                        </c:otherwise>
                    </c:choose>
                    <input type="submit" value="Search"/>
                </form>
            <div>
                <a href="${pageContext.request.contextPath}/matches">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>
        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach items="${requestScope.matches}" var="match">
                <tr>
                    <td>${match.player1Name}</td>
                    <td>${match.player2Name}</td>
                    <td><span class="winner-name-td">${match.winnerName}</span></td>
                </tr>
            </c:forEach>
        </table>

        <div class="pagination">
            <c:set var="currentPage" value="${param.page != null ? param.page : 1}"/>

            <form class="prev" method="GET" action="${pageContext.request.contextPath}/matches">
                <c:choose>
                    <c:when test="${currentPage == 1}">
                        <input type="submit" value="&lt;&lt;" disabled/>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${filterByName != null}">
                            <input type="hidden" name="filterByName" value="${filterByName}"/>
                        </c:if>
                        <input type="hidden" name="page" value="1"/>
                        <input style="background-color: transparent; border: none; outline: none;" type="submit" value="&lt;&lt;"/>
                    </c:otherwise>
                </c:choose>
            </form>

            <form class="prev" method="GET" action="${pageContext.request.contextPath}/matches">
                <c:choose>
                    <c:when test="${currentPage == 1}">
                        <input type="hidden" name="page" value="1"/>
                        <input type="submit" value="&lt;" disabled/>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${filterByName != null}">
                            <input type="hidden" name="filterByName" value="${filterByName}"/>
                        </c:if>
                        <input type="hidden" name="page" value="${currentPage - 1}"/>
                        <input style="background-color: transparent; border: none; outline: none;" type="submit" value="&lt;"/>
                    </c:otherwise>
                </c:choose>
            </form>

            <form class="num-page current">
                <input style="background-color: transparent; border: none; outline: none;" type="submit" value="${currentPage}"/>
            </form>

            <form class="next" method="GET" action="${pageContext.request.contextPath}/matches">
                <c:choose>
                    <c:when test="${currentPage == pageCount}">
                        <input type="submit" value="&gt;" disabled/>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${filterByName != null}">
                            <input type="hidden" name="filterByName" value="${filterByName}"/>
                        </c:if>
                        <input type="hidden" name="page" value="${currentPage + 1}"/>
                        <input style="background-color: transparent; border: none; outline: none;" type="submit" value="&gt;"/>
                    </c:otherwise>
                </c:choose>
            </form>

            <form class="next" method="GET" action="${pageContext.request.contextPath}/matches">
                <c:choose>
                    <c:when test="${currentPage == pageCount}">
                        <input type="submit" value="&gt;&gt;" disabled/>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${filterByName != null}">
                            <input type="hidden" name="filterByName" value="${filterByName}"/>
                        </c:if>
                        <input type="hidden" name="page" value="${pageCount}"/>
                        <input style="background-color: transparent; border: none; outline: none;" type="submit" value="&gt;&gt;"/>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
