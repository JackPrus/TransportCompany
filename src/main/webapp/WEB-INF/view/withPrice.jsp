<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.01.2021
  Time: 16:30
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
            <form name="orderForm" method="post" action="controller?command=order">

                <h3><fmt:message key="label.requestform.length"/>    ${param.length} <br>
                <h3><fmt:message key="label.requestform.width"/>    ${param.width} <br>
                <h3><fmt:message key="label.requestform.height"/>   ${param.height} <br>
                <h3><fmt:message key="label.requestform.weight"/>   ${param.weight} <br>
                <h3><fmt:message key="label.requestform.city_pickup"/>   ${param.city1} <br>
                <h3><fmt:message key="label.requestform.city_delibery"/>   ${param.city2} <br>

                    <h3><label for="adressPickUp"><fmt:message key="label.orderform.pickup_address"/> <br>
                        <input type="text" id= "adressPickUp" name="adressPickUp" value=""/>
                    </label>
                    </h3>

                    <h3><label for="adressDelivery"><fmt:message key="label.orderform.delivery_address"/> <br>
                        <input type="text" id= "adressDelivery" name="adressDelivery" value=""/>
                    </label>
                    </h3>

                    <h3><fmt:message key="label.requestform.resultText"   />   ${price}</h3>

                    <p>
                        <input type="submit" value="<fmt:message key="label.orderform.submitorder"/>"/>
                    </p>

                    <input type="hidden" name="length" value="${param.length}"/><br>
                    <input type="hidden" name="width" value="${param.width}"/><br>
                    <input type="hidden" name="height" value="${param.height}"/><br>
                    <input type="hidden" name="weight" value="${param.weight}"/><br>
                    <input type="hidden" name="city1" value="${param.city1}"/><br>
                    <input type="hidden" name="city2" value="${param.city2}"/><br>
                    <input type="hidden" name="price" value="${price}"/><br>


<%--                    <jsp:useBean id="rfq" scope="request" class="by.prus.finalproject.bean.RequestForQuotation" />--%>
<%--                    <jsp:setProperty name="rfq" property="length" value= "${param.length}" />--%>
<%--                    <jsp:setProperty name="rfq" property="width" value= "${param.width}" />--%>
<%--                    <jsp:setProperty name="rfq" property="height" value= "${param.height}" />--%>
<%--                    <jsp:setProperty name="rfq" property="weight" value= "${param.weight}" />--%>
<%--                    <jsp:setProperty name="rfq" property="city1" value= "${param.city1}" />--%>
<%--                    <jsp:setProperty name="rfq" property="city2" value= "${param.city2}" />--%>
<%--                    <jsp:setProperty name="rfq" property="price" value= "${param.price}" />--%>

            </form>

            <form name="requestForm" method="post" action="controller?command=gotorequest">

             <p>
                 <input type="submit" value="<fmt:message key="label.orderform.correction"/>"/>
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