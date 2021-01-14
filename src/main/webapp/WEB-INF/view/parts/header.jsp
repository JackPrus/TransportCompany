<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
</head>
<body>
<div class="topnav">
    <div class = "topnavhref">
        <a href="controller?command=mainPage"><fmt:message key="label.header.home"/></a>
        <a href="controller?command=aboutUsPage"><fmt:message key="label.header.about"/> </a>
        <a href="controller?command=contactsPage"><fmt:message key="label.header.contacts"/></a>
    </div>

    <div class="dropdown">
        <button class="dropbtn"><fmt:message key="label.header.language"/></button>
        <div class="dropdown-content">
            <a href="controller?command=locale&localization=en"><fmt:message key="label.header.language.eng"/></a>
            <a href="controller?command=locale&localization=ru"><fmt:message key="label.header.language.rus"/></a>
        </div>
    </div>
    <div class="log-container">
        <c:choose>
            <c:when test="${sessionScope.userId==null}">
                <a href="controller?command=authorization"><fmt:message key="label.header.sign_in"/></a>
            </c:when>
            <c:otherwise>
                <a href="controller?command=logout"><fmt:message key="label.header.sign_out"/></a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>