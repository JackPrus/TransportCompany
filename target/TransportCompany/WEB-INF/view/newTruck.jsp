<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.02.2021
  Time: 21:46
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
            <form name="newTruckForm" method="post" action="controller?command=newTruck" accept-charset="UTF-8">
                <%--                сделать команду request--%>
                <h2><fmt:message key="label.truck.title"/></h2>


                <h3><label for="truckNo"><fmt:message key="label.truck.truckno" /> <br>
                    <input type="text" id= "truckNo" name="truckNo" value="${param.truckNo}"/>
                </label>
                </h3>

                <h3><label for="length"><fmt:message key="label.truck.length" /> <br>
                    <input type="number" id= "length" name="length" value="${param.lengthCapacity}" min="1" max="1360"/>
                </label>
                </h3>

                    <h3><label for="width"><fmt:message key="label.truck.width" /> <br>
                        <input type="number" id= "width" name="width" value="${param.widthCapacity}" min="1" max="250"/>
                    </label>
                    </h3>

                    <h3><label for="height"><fmt:message key="label.truck.height" /> <br>
                        <input type="number" id= "height" name="height" value="${param.heightCapacity}" min="1" max="280"/>
                    </label>
                    </h3>

                    <h3><label for="weight"><fmt:message key="label.truck.weight" /> <br>
                        <input type="number" id= "weight" name="weight" value="${param.weighCapacity}" min="1" max="23500"/>
                    </label>
                    </h3>

                <p>
                    <input type="submit" value="<fmt:message key="label.button.add"/>"/>
                </p>
            </form>

        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>
