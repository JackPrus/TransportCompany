<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.01.2021
  Time: 20:57
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

            <c:forEach items="${allOrdersForClient}" var="orders">
                ${orders.identity}  <%-- BookBean fields that you want print out--%>
                ${orders.pickupAdress}
                ${orders.cityPickUp}
                ${orders.cityDelivery}
                ${orders.unloadingAdress}
                ${orders.length}
                ${orders.width}
<%--                ${orders.height}--%>
<%--                ${orders.weight}--%>
<%--                ${orders.orderDate}--%>
<%--                ${orders.isActive}--%>
<%--                ${orders.price}--%>
<%--                ${orders.truck.identity}--%>
<%--                ${orders.manager.identity}--%>
<%--                ${orders.client.identity}--%>
                <%-- another fields --%>
            </c:forEach>

            ${errorMessage}
        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>
