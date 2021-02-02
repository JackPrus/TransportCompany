<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.02.2021
  Time: 10:00
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



            <form name="newClientForm" method="post" action="controller?command=editClient" accept-charset="UTF-8">
                <%--                сделать команду request--%>
                <h2><fmt:message key="label.edit_cleint.title"/></h2>

                <h3><label for="name"><fmt:message key="label.add_new_cleint.name" /> <br>
                    <input type="text" id= "name" name="name" value=${param.clientName}/>
                </label>
                </h3>

                <h3><label for="data"><fmt:message key="label.add_new_cleint.data" /> <br>
                    <input type="text" id= "data" name="data" value=${param.clientData}/>
                </label>
                </h3>


                    <h3><fmt:message key="label.edit_cleint.current_type"/>  ${param.clientClientType} <br> </h3>


                <select name="type" id="type">
                    <option value="1"><fmt:message key="label.add_new_cleint.type.person"/></option>
                    <option value="2"><fmt:message key="label.add_new_cleint.type.company"/></option>
                    <option value="3"><fmt:message key="label.add_new_cleint.type.office"/></option>
                </select>

                    <input type="hidden" name="clientId" value="${param.clientId}"/>

                <p>
                    <input type="submit" value="<fmt:message key="label.button.change"/>"/>
                </p>
            </form>

            <form name="delete" method="post" action="controller?command=deleteClient" accept-charset="UTF-8">
                <input type="hidden" name="clientId" value="${param.clientId}"/>
                <input type="submit" value="<fmt:message key="label.button.delete"/>"/>
            </form>

            <form name="cancel" method="post" action="controller?command=mainPage" accept-charset="UTF-8">
                <input type="submit" value="<fmt:message key="label.button.cancel"/>"/>
            </form>



            <c:if test="${errorMessage eq 'wrong_info'}">
                <fmt:message key="label.error.wrong_info"/>
            </c:if>

        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>
