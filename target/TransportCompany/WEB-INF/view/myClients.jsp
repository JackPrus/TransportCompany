<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 30.01.2021
  Time: 20:47
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
                    <th><fmt:message key="label.myclients.id"/></th>
                    <th><fmt:message key="label.myclients.name"/></th>
                    <th><fmt:message key="label.myclients.data"/></th>
                    <th><fmt:message key="label.myclients.type"/></th>
                </tr>

                <c:forEach items="${allClients}" var="client">
                    <form name="requestForm" method="post" action="controller?command=gotoEditClient">
                        <tr>
                            <td>${client.identity}</td>
                            <td>${client.name}</td>
                            <td>${client.data}</td>
                            <td>${client.clientType}</td>
                            <td><input type="submit"  value="<fmt:message key="label.button.change"/>"/></td>
                        </tr>
                        <input type="hidden" name="clientId" value="${client.identity}"/>
                        <input type="hidden" name="clientName" value="${client.name}"/>
                        <input type="hidden" name="clientData" value="${client.data}"/>
                        <input type="hidden" name="clientClientType" value="${client.clientType}"/>
                    </form>
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
