<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.01.2021
  Time: 12:28
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
        <fmt:message key="label.application.title"/>
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

            <form name="bookingForm" method="post" action="controller?command=ordersOfManager">

                <%--                сделать команду request--%>
                <h2><fmt:message key="label.managerform.orders"/></h2>

                    <p>
                        <input type="submit" value="<fmt:message key="label.managerform.buttonOrders"/>"/>
                    </p>


            </form>

            <form name="bookingForm" method="post" action="controller?command=ordersWithoutManager">

                </h3>

                <h2><fmt:message key="label.managerform.requests"/></h2>
                <p>
                    <input type="submit" value="<fmt:message key="label.managerform.buttonRequest"/>"/>
                </p>

                </h3>


            </form>
            ${errorMessage}
        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>
