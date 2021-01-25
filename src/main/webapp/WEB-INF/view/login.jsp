<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.01.2021
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
    <title>
        <fmt:message key="label.login.title"/>
    </title>
</head>
<body>

<div class="header">
    <jsp:include page="parts/header.jsp"/>
</div>

<div class="row">

    <div class="main">
        <form name="loginForm" method="post" action="controller?command=login">
            <h3>
                <fmt:message key="label.login"/> <br>
                <label>
                    <input type="text" name="login" value=""/>
                </label>
            </h3>
            <h3>
                <fmt:message key="label.password"/><br>
                <label>
                    <input type="password" name="password" value=""/>
                </label>
            </h3>
            <p>
                <input type="submit" value="<fmt:message key="label.log_in"/>"/>
            </p>
        </form>
        <c:if test="${errorMessage eq 'wrong_login'}">
            <fmt:message key="label.error.wrong_login"/>
        </c:if>

    </div>
</div>

<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>
