<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.01.2021
  Time: 20:06
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
        <fmt:message key="label.orderform.title"/>
    </title>
</head>
<body>

<div class="header">
    <jsp:include page="parts/header.jsp"/>
</div>

<div class="row">

    <div class="side">
        <jsp:include page="parts/leftPane.jsp"/>
    </div>
    <div class="main">
        <div class="form">

            <form name="requestForm" method="post" action="controller?command=gotorequest">
                <h3><fmt:message key="label.orderform.new_order"/> <br>
                <p>
                    <input type="submit" value="<fmt:message key="label.orderform.new_order"/>"/>
                </p>
            </form>

            <form name="requestForm" method="post" action="controller?command=mainPage">
                <h3><fmt:message key="label.orderform.tomainpage"/> <br>
                <p>
                    <input type="submit" value="<fmt:message key="label.orderform.buttongo"/>"/>
                </p>
            </form>

            ${errorMessage}
        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>
