<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
    <head>
        <title>Meals</title>
    </head>
    <body>
        <h3><a href="index.html">Home</a></h3>
        <h2>Meals</h2>
        <table border="1" cellspacing="0" cellpadding="2">
            <thead>
                <tr>
                    <th>dateTime</th>
                    <th>description</th>
                    <th>calories</th>
                    <th>exceed</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="meal" items="${mealList}">
                <tr>
                    <%--Атрибут Дата и время--%>
                    <td>
                        <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" />
                    </td>
                    <%--Атрибут Описание--%>
                    <td>${meal.description}</td>
                    <%--Атрибут Калории--%>
                    <c:choose>
                        <c:when test="${meal.exceed eq true}">
                            <c:set var="stl" value="color:red"/>
                        </c:when>
                        <c:when test="${meal.exceed eq false}">
                            <c:set var="stl" value="color:green"/>
                        </c:when>
                    </c:choose>
                    <td style=${stl}>${meal.calories}</td>
                    <%--Атрибут Признак превышения--%>
                    <td>${meal.exceed}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>