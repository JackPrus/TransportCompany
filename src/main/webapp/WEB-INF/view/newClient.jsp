<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.01.2021
  Time: 17:58
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
            <form name="newClientForm" method="post" action="controller?command=newClient" accept-charset="UTF-8">
                <%--                сделать команду request--%>
                <h2><fmt:message key="label.add_new_cleint.title"/></h2>


                <h3><label for="name"><fmt:message key="label.add_new_cleint.name" /> <br>
                    <input type="text" id= "name" name="name" value=""/>
                </label>
                </h3>

                    <h3><label for="data"><fmt:message key="label.add_new_cleint.data" /> <br>
                        <input type="text" id= "data" name="data" value=""/>
                    </label>
                    </h3>


                    <h3>
                        <label for="type"><fmt:message key="label.add_new_cleint.type"/></label>
                    </h3>

                    <select name="type" id="type">
                        <option value="1"><fmt:message key="label.add_new_cleint.type.person"/></option>
                        <option value="2"><fmt:message key="label.add_new_cleint.type.company"/></option>
                        <option value="3"><fmt:message key="label.add_new_cleint.type.office"/></option>
                    </select>

                    <h3><label for="login"><fmt:message key="label.add_new_cleint.login" /> <br>
                        <input type="text" id= "login" name="login" value=""/>
                    </label>
                    </h3>

                    <h3><label for="password"><fmt:message key="label.add_new_cleint.password" /> <br>
                        <input type="password" id= "password" name="password" value=""/>
                    </label>
                    </h3>

                    <h3><label for="repeatpassword"><fmt:message key="label.add_new_cleint.repeatp_password" /> <br>
                        <input type="password" id= "repeatpassword" name="repeatpassword" value=""/>
                    </label>
                    </h3>

                    <h3><label for="email"><fmt:message key="label.add_new_cleint.email" /> <br>
                        <input type="email" id= "email" name="email" value=""/>
                    </label>
                    </h3>

                <p>
                    <input type="submit" value="<fmt:message key="label.add_new_cleint.submit"/>"/>
                </p>
            </form>
            <c:if test="${errorMessage eq 'wrong_login'}">
                <fmt:message key="label.error.wrong_login"/>
            </c:if>
            <c:if test="${errorMessage eq 'wrong_secondpasword'}">
                <fmt:message key="label.error.secondpassword"/>
            </c:if>
            <c:if test="${errorMessage eq 'user_exists'}">
                <fmt:message key="label.error.user_exists"/>
            </c:if>
        </div>
    </div>
</div>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>

</body>
</html>
