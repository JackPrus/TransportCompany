<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.02.2021
  Time: 19:26
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
        <fmt:message key="label.myclients.head"/>
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

            <table width="100%" border="4" cellpadding="4">

                <tr>
                    <th><fmt:message key="label.truck.id"/></th>
                    <th><fmt:message key="label.truck.truckno"/></th>
                    <th><fmt:message key="label.truck.length"/></th>
                    <th><fmt:message key="label.truck.width"/></th>
                    <th><fmt:message key="label.truck.height"/></th>
                    <th><fmt:message key="label.truck.weight"/></th>
                    <th><fmt:message key="label.truck.isbusy"/></th>
                    <th><fmt:message key="label.truck.manager_id"/></th>
                    <th><fmt:message key="label.truck.edit"/></th>
                </tr>

                <c:forEach items="${allTrucks}" var="truck">
                        <tr>
                            <form name="requestForm" method="post" action="controller?command=gotoEditTruck">
                            <td>${truck.identity}</td>
                            <td>${truck.truckNo}</td>
                            <td>${truck.lengthCapacity}</td>
                            <td>${truck.widthCapacity}</td>
                            <td>${truck.heightCapacity}</td>
                            <td>${truck.weightCapacity}</td>
                            <td>${truck.busy}</td>
                            <td>${truck.manager.identity}</td>
                            <td><input type="submit"  value="<fmt:message key="label.button.change"/>"/></td>

                        <input type="hidden" name="truckId" value="${truck.identity}"/>
                        <input type="hidden" name="truckNo" value="${truck.truckNo}"/>
                        <input type="hidden" name="lengthCapacity" value="${truck.lengthCapacity}"/>
                        <input type="hidden" name="widthCapacity" value="${truck.widthCapacity}"/>
                        <input type="hidden" name="heightCapacity" value="${truck.heightCapacity}"/>
                        <input type="hidden" name="weightCapacity" value="${truck.weightCapacity}"/>
                        <input type="hidden" name="busy" value="${truck.busy}"/>
                        <input type="hidden" name="managerId" value="${truck.manager.identity}"/>
                        </form>

                            <c:if test="${not empty param.orderId}">
                            <form name="pointTruck" method="post" action="controller?command=pointTruck">
                                <input type="hidden" name="orderId" value="${param.orderId}">
                                <input type="hidden" name="truckId" value="${truck.identity}">
                                <td><input type="submit"  value="<fmt:message key="label.button.point"/>"/></td>
                            </c:if>

                    </tr>

                </c:forEach>

            </table>
            ${errorMessage}
        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>
