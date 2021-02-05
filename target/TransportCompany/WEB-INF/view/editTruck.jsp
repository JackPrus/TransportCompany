<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.02.2021
  Time: 10:08
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
            <form name="newTruckForm" method="post" action="controller?command=editTruck" accept-charset="UTF-8">
                <%--                сделать команду request--%>
                <h2><fmt:message key="label.truck.edit"/></h2>


                <h3><label for="truckNo"><fmt:message key="label.truck.truckno" /> <br>
                    <input type="text" id= "truckNo" name="truckNo" value="${param.truckNo}"/>
                </label>
                </h3>

                <h3><label for="lengthCapacity"><fmt:message key="label.truck.length" /> <br>
                    <input type="number" id= "lengthCapacity" name="lengthCapacity" value="${param.lengthCapacity}" min="1" max="1360"/>
                </label>
                </h3>

                <h3><label for="widthCapacity"><fmt:message key="label.truck.width" /> <br>
                    <input type="number" id= "widthCapacity" name="widthCapacity" value="${param.widthCapacity}" min="1" max="250"/>
                </label>
                </h3>

                <h3><label for="heightCapacity"><fmt:message key="label.truck.height" /> <br>
                    <input type="number" id= "heightCapacity" name="heightCapacity" value="${param.heightCapacity}" min="1" max="280"/>
                </label>
                </h3>

                <h3><label for="weightCapacity"><fmt:message key="label.truck.weight" /> <br>
                    <input type="number" id= "weightCapacity" name="weightCapacity" value="${param.weightCapacity}" min="1" max="23500"/>
                </label>
                </h3>

                    <input type="hidden" name="truckId" value="${param.truckId}"/>
                    <input type="hidden" name="busy" value="${param.busy}"/>
                    <input type="hidden" name="managerId" value="${param.managerId}"/>

                <p>
                    <input type="submit" value="<fmt:message key="label.button.change"/>"/>
                </p>
            </form>

            <form name="deleteTruck" method="post" action="controller?command=deleteTruck" accept-charset="UTF-8">
                <c:if test="${not empty param.truckId}">
                    <input type="hidden" name="truckId" value="${param.truckId}"/>
                    <p>
                        <input type="submit" value="<fmt:message key="label.button.delete"/>"/>
                    </p>
                </c:if>
            </form>

            <form name="cancel" method="post" action="controller?command=mainPage" accept-charset="UTF-8">
                <input type="submit" value="<fmt:message key="label.button.cancel"/>"/>
            </form>


            <c:if test="${errorMessage eq 'wrong_data'}">
                <fmt:message key="label.error.truck.wrong_data"/>
            </c:if>

        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>
