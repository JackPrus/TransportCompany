<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.01.2021
  Time: 13:59
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

            <c:if test="${errorMessage eq 'No_truck_for_order'}">
                <fmt:message key="label.error.truck.no_trucks_for_order"/>
            </c:if>

            <table width="100%" border="4" cellpadding="4">

                <tr>
                    <th>ID</th>
                    <th><fmt:message key="label.ordertable.address_pick_up"/></th>
                    <th><fmt:message key="label.ordertable.loading_city"/></th>
                    <th><fmt:message key="label.ordertable.unloading_city"/></th>
                    <th><fmt:message key="label.ordertable.address_ubloading"/></th>
                    <th><fmt:message key="label.ordertable.length"/></th>
                    <th><fmt:message key="label.ordertable.width"/></th>
                    <th><fmt:message key="label.ordertable.height"/></th>
                    <th><fmt:message key="label.ordertable.weight"/></th>
                    <th><fmt:message key="label.ordertable.date"/></th>
                    <th><fmt:message key="label.ordertable.isactive"/></th>
                    <th><fmt:message key="label.ordertable.price"/></th>
                    <th><fmt:message key="label.ordertable.truck_id"/></th>
                    <th><fmt:message key="label.ordertable.manager_id"/></th>
                    <th><fmt:message key="label.ordertable.client_id"/></th>
                    <th><fmt:message key="label.button.close"/></th>
                </tr>

                <c:forEach items="${ordersOfManager}" var="order">
                    <tr>

                        <td>${order.identity}</td>
                        <td>${order.pickupAdress}</td>
                        <td>${order.cityPickUp}</td>
                        <td>${order.cityDelivery}</td>
                        <td>${order.unloadingAdress}</td>
                        <td>${order.length}</td>
                        <td>${order.width}</td>
                        <td>${order.height}</td>
                        <td>${order.weight}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.active}</td>
                        <td>${order.price}</td>

                        <form name="newTruckForm" method="post" action="controller?command=pointTruckPage">
                        <c:choose>
                            <c:when test="${order.truck.identity eq 0}">
                                <input type="hidden" name="orderId" value="${order.identity}">
                                <td><input type="submit"  value="<fmt:message key="label.button.point"/>"/></td>
                            </c:when>
                            <c:otherwise>
                                <td>${order.truck.identity}</td>
                            </c:otherwise>
                        </c:choose>
                        </form>

                        <td>${order.manager.identity}</td>
                        <td>${order.client.identity}</td>

                        <td>
                            <c:choose>
                            <c:when test="${order.active eq true}">
                                <form name="newTruckForm" method="post" action="controller?command=markAsDelivered">
                                <input type="hidden" name="orderId" value="${order.identity}">
                                <input type="hidden" name="truckId" value="${order.truck.identity}">
                                <input type="submit"  value="<fmt:message key="label.button.close"/>"/>
                                </form>
                            </c:when>
                                    <c:otherwise>
                                        <fmt:message key="label.orderform.delivered"/>
                                    </c:otherwise>
                            </c:choose>
                        </td>


                    </tr>

                </c:forEach>

            </table>

            <div class="pagination">

                <c:if test="${requestScope.amountPages>3}">
                    <a href="#">&laquo;</a>
                </c:if>
                <c:if test="${requestScope.amountPages > 1}">
                    <a href="controller?command=${requestScope.command}&page=1">1</a>
                    <a href="controller?command=${requestScope.command}&page=2">2</a>
                </c:if>
                <c:if test="${requestScope.amountPages > 2}">
                    <a href="controller?command=${requestScope.command}&page=3">3</a>
                </c:if>
                <c:if test="${requestScope.amountPages > 3}">
                    <a href="#">&raquo;</a>
                </c:if>

            </div>

        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>
