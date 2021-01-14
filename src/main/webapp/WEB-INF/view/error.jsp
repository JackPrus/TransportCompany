<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.01.2021
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <title>

    </title>
</head>
<body>
<h1> ${errorMessage}</h1>


<div class="footer">
    <jsp:include page="parts/footer.jsp"/>
</div>
</body>
</html>
