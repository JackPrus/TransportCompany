<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.01.2021
  Time: 14:01
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

            <form name="bookingForm" method="post" action="controller?command=request" accept-charset="UTF-8">
<%--                сделать команду request--%>
                <h2><fmt:message key="label.requestform.text"/></h2>


                <h3><label for="length"><fmt:message key="label.requestform.length" /> <br>
                    <input type="number" id= "length" name="length" value="" min="1" max="1300"/>
                </label>
                </h3>

                <h3><label for="width"><fmt:message key="label.requestform.width"/> <br>
                    <input type="number" id= "width" name="width" value="" min="1" max="245"/>
                </label>

                </h3>
                <h3><label for="height"><fmt:message key="label.requestform.height"/> <br>
                    <input type="number" id= "height" name="height" value="" min="1" max="275"/>
                </label>

                </h3>
                <h3><label for="weight"><fmt:message key="label.requestform.weight"/> <br>
                    <input type="number" id= "weight" name="weight" value="" min="0" max="23000" step="0.01"/>
                </label>
                </h3>

                <h3>
                    <label for="city1"><fmt:message key="label.requestform.city_pickup"/></label>
                </h3>

                    <select name="city1" id="city1">
                        <option value="BREST"><fmt:message key="label.cities.BREST"/></option>
                        <option value="COBRIN"><fmt:message key="label.cities.COBRIN"/></option>
                        <option value="PINSK"><fmt:message key="label.cities.PINSK"/></option>
                        <option value="BARANOVICHY"><fmt:message key="label.cities.BARANOVICHY"/></option>
                        <option value="IVATSEVICHY"><fmt:message key="label.cities.IVATSEVICHY"/></option>
                        <option value="LUNINETS"><fmt:message key="label.cities.LUNINETS"/></option>
                        <option value="PRUZHANY"><fmt:message key="label.cities.PRUZHANY"/></option>
                        <option value="HRODNA"><fmt:message key="label.cities.HRODNA"/></option>
                        <option value="VOLKOVYSK"><fmt:message key="label.cities.VOLKOVYSK"/></option>
                        <option value="SLONIM"><fmt:message key="label.cities.SLONIM"/></option>
                        <option value="NOVOHRUDOK"><fmt:message key="label.cities.NOVOHRUDOK"/></option>
                        <option value="LIDA"><fmt:message key="label.cities.LIDA"/></option>
                        <option value="OSHMYANY"><fmt:message key="label.cities.OSHMYANY"/></option>
                        <option value="VITEBSK"><fmt:message key="label.cities.VITEBSK"/></option>
                        <option value="GLUBOKOE"><fmt:message key="label.cities.GLUBOKOE"/></option>
                        <option value="LEPEL"><fmt:message key="label.cities.LEPEL"/></option>
                        <option value="POLOTSK"><fmt:message key="label.cities.POLOTSK"/></option>
                        <option value="ORSHA"><fmt:message key="label.cities.ORSHA"/></option>
                        <option value="MAHILYOW "><fmt:message key="label.cities.MAHILYOW"/></option>
                        <option value="GORKY"><fmt:message key="label.cities.GORKY"/></option>
                        <option value="MSTISLAVL"><fmt:message key="label.cities.MSTISLAVL"/></option>
                        <option value="KRYCHEV"><fmt:message key="label.cities.KRYCHEV"/></option>
                        <option value="BOBRUISK"><fmt:message key="label.cities.BOBRUISK"/></option>
                        <option value="OSIPOVICHY"><fmt:message key="label.cities.OSIPOVICHY"/></option>
                        <option value="HOMEL"><fmt:message key="label.cities.HOMEL"/></option>
                        <option value="MAZYR"><fmt:message key="label.cities.MAZYR"/></option>
                        <option value="ZHLOBIN"><fmt:message key="label.cities.ZHLOBIN"/></option>
                        <option value="RECHITSA"><fmt:message key="label.cities.RECHITSA"/></option>
                        <option value="SVETLAHORSK"><fmt:message key="label.cities.SVETLAHORSK"/></option>
                        <option value="PETRYKAU"><fmt:message key="label.cities.PETRYKAU"/></option>
                        <option value="LELCHITSY "><fmt:message key="label.cities.LELCHITSY"/></option>
                        <option value="ZHITKOVICHY"><fmt:message key="label.cities.ZHITKOVICHY"/></option>
                        <option value="MINSK"><fmt:message key="label.cities.MINSK"/></option>
                        <option value="SALIHORSK"><fmt:message key="label.cities.SALIHORSK"/></option>
                        <option value="SLUTSK"><fmt:message key="label.cities.SLUTSK"/></option>
                        <option value="MARYNAHORKA "><fmt:message key="label.cities.MARYNAHORKA"/></option>
                        <option value="STOLBTSY"><fmt:message key="label.cities.STOLBTSY"/></option>
                        <option value="DZERCHINSK"><fmt:message key="label.cities.DZERCHINSK"/></option>
                        <option value="MOLODZECHNO"><fmt:message key="label.cities.MOLODZECHNO"/></option>
                        <option value="MYADEL"><fmt:message key="label.cities.MYADEL"/></option>
                        <option value="ZHODINO"><fmt:message key="label.cities.ZHODINO"/></option>
                        <option value="BORISOV"><fmt:message key="label.cities.BORISOV"/></option>
                        <option value="BEREZINO"><fmt:message key="label.cities.BEREZINO"/></option>

                    </select>

                    <h3>
                        <label for="city2"><fmt:message key="label.requestform.city_delibery"/></label>
                    </h3>

                <select name="city2" id="city2">
                    <option value="BREST"><fmt:message key="label.cities.BREST"/></option>
                    <option value="COBRIN"><fmt:message key="label.cities.COBRIN"/></option>
                    <option value="PINSK"><fmt:message key="label.cities.PINSK"/></option>
                    <option value="BARANOVICHY"><fmt:message key="label.cities.BARANOVICHY"/></option>
                    <option value="IVATSEVICHY"><fmt:message key="label.cities.IVATSEVICHY"/></option>
                    <option value="LUNINETS"><fmt:message key="label.cities.LUNINETS"/></option>
                    <option value="PRUZHANY"><fmt:message key="label.cities.PRUZHANY"/></option>
                    <option value="HRODNA"><fmt:message key="label.cities.HRODNA"/></option>
                    <option value="VOLKOVYSK"><fmt:message key="label.cities.VOLKOVYSK"/></option>
                    <option value="SLONIM"><fmt:message key="label.cities.SLONIM"/></option>
                    <option value="NOVOHRUDOK"><fmt:message key="label.cities.NOVOHRUDOK"/></option>
                    <option value="LIDA"><fmt:message key="label.cities.LIDA"/></option>
                    <option value="OSHMYANY"><fmt:message key="label.cities.OSHMYANY"/></option>
                    <option value="VITEBSK"><fmt:message key="label.cities.VITEBSK"/></option>
                    <option value="GLUBOKOE"><fmt:message key="label.cities.GLUBOKOE"/></option>
                    <option value="LEPEL"><fmt:message key="label.cities.LEPEL"/></option>
                    <option value="POLOTSK"><fmt:message key="label.cities.POLOTSK"/></option>
                    <option value="ORSHA"><fmt:message key="label.cities.ORSHA"/></option>
                    <option value="MAHILYOW "><fmt:message key="label.cities.MAHILYOW"/></option>
                    <option value="GORKY"><fmt:message key="label.cities.GORKY"/></option>
                    <option value="MSTISLAVL"><fmt:message key="label.cities.MSTISLAVL"/></option>
                    <option value="KRYCHEV"><fmt:message key="label.cities.KRYCHEV"/></option>
                    <option value="BOBRUISK"><fmt:message key="label.cities.BOBRUISK"/></option>
                    <option value="OSIPOVICHY"><fmt:message key="label.cities.OSIPOVICHY"/></option>
                    <option value="HOMEL"><fmt:message key="label.cities.HOMEL"/></option>
                    <option value="MAZYR"><fmt:message key="label.cities.MAZYR"/></option>
                    <option value="ZHLOBIN"><fmt:message key="label.cities.ZHLOBIN"/></option>
                    <option value="RECHITSA"><fmt:message key="label.cities.RECHITSA"/></option>
                    <option value="SVETLAHORSK"><fmt:message key="label.cities.SVETLAHORSK"/></option>
                    <option value="PETRYKAU"><fmt:message key="label.cities.PETRYKAU"/></option>
                    <option value="LELCHITSY "><fmt:message key="label.cities.LELCHITSY"/></option>
                    <option value="ZHITKOVICHY"><fmt:message key="label.cities.ZHITKOVICHY"/></option>
                    <option value="MINSK"><fmt:message key="label.cities.MINSK"/></option>
                    <option value="SALIHORSK"><fmt:message key="label.cities.SALIHORSK"/></option>
                    <option value="SLUTSK"><fmt:message key="label.cities.SLUTSK"/></option>
                    <option value="MARYNAHORKA "><fmt:message key="label.cities.MARYNAHORKA"/></option>
                    <option value="STOLBTSY"><fmt:message key="label.cities.STOLBTSY"/></option>
                    <option value="DZERCHINSK"><fmt:message key="label.cities.DZERCHINSK"/></option>
                    <option value="MOLODZECHNO"><fmt:message key="label.cities.MOLODZECHNO"/></option>
                    <option value="MYADEL"><fmt:message key="label.cities.MYADEL"/></option>
                    <option value="ZHODINO"><fmt:message key="label.cities.ZHODINO"/></option>
                    <option value="BORISOV"><fmt:message key="label.cities.BORISOV"/></option>
                    <option value="BEREZINO"><fmt:message key="label.cities.BEREZINO"/></option>

                </select>


                <p>
                    <input type="submit" value="<fmt:message key="label.request_form.button"/>"/>
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
