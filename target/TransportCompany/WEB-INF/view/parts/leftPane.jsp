<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.01.2021
  Time: 14:03
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/leftPane.css"/>
    <title></title>
</head>
<body>

<div class="left-pane">
    <c:if test="${userRole eq 'CLIENT'}">
        <form name="userForm" method="post" action="controller?command=allOrdersForClient">
            <input type="submit" value="<fmt:message key="label.left_pane.client.all_orders"/>"/>
        </form>

    </c:if>


    <c:if test="${userRole eq 'MANAGER'}">
        <form name="adminForm" method="post" action="controller?command=allRooms">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.all_rooms"/>"/>
        </form>
        <form name="adminForm" method="post" action="controller?command=allUsers">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.all_users"/>"/>
        </form>
        <form name="adminForm" method="post" action="controller?command=allApplications">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.all_applications"/>"/>
        </form>

        <form name="adminForm" method="post" action="controller?command=allInvoices">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.all_invoices"/>"/>
        </form>

        <form name="adminForm" method="post" action="controller?command=inProgressApplication">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.in_process_applications"/>"/>
        </form>
        <form name="adminForm" method="post" action="controller?command=addRoomPage">
            <input type="submit" value="<fmt:message key="label.left_pane.admin.add_room"/>"/>
        </form>


    </c:if>
</div>
</body>
</html>
