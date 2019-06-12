<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="javatime" uri="http://jakarta.apache.org/taglibs/standard/permittedTaglibs" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>


<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals table</h2>

<hr>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Options</th>
    </tr>
    </thead>
    <tbody>

    <jsp:useBean id="mealList" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
    <c:forEach items="${mealList}" var="meal" varStatus="id">

        <tr style="${meal.excess ? 'background-color: rgba(255, 0, 0, 0.2)':'background-color: rgba(0, 175, 16, 0.2)'}">
                <%--        <th style="${meal.excess ? 'background-color: rgba(255, 0, 0, 0.3)':'background-color: rgba(0, 175, 16, 0.1)'}"> <c:out value="${mealList.indexOf(meal) + 1}" /> </th>--%>
            <td>${id.count}</td>
            <fmt:parseDate value="${ meal.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
            <td><fmt:formatDate pattern="MM.dd.yyyy HH'h'mm" value="${ parsedDateTime }"/></td>
            <td>${ meal.description }</td>
            <td>${ meal.calories }</td>
                <%--        <th style="${meal.excess ? 'background-color: rgba(255, 0, 0, 0.3)':'background-color: rgba(0, 175, 16, 0.1)'}">
                <button onclick="location.href='/topjava_war_exploded/meals/${mealList.indexOf(meal)}'">Delete </button></th>--%>
            <td>
                <button onclick=location.href="meals?action=edit&mealId=<c:out value="${id.count}"/>">Update</button>

                <button onclick=location.href="meals?action=delete&mealId=<c:out value="${id.count}"/>">Delete</button>
            </td>


        </tr>

        <c:if test="${param.get('action') == 'edit'}">
            <c:if test="${param.get('mealId') == id.count}">
                <form action="meals?action=edit" method="get">
                    <td>*</td>
                    <input type="hidden" name="action" value="save">
                    <input type="hidden" name="mealId" value="${id.count}">
                    <td><input type="text" name="date" value="<fmt:formatDate pattern="MM.dd.yyyy HH'h'mm" value="${ parsedDateTime }"/>"></td>
                    <td><select name="description">
                        <option value="Breakfast" <c:if test="${meal.description == 'Breakfast'}">selected </c:if>>Breakfast</option>
                        <option value="Lunch" <c:if test="${meal.description == 'Lunch'}">selected </c:if>>Lunch</option>
                        <option value="Dinner" <c:if test="${meal.description == 'Dinner'}">selected </c:if>>Dinner</option>
                    </select></td>
                    <td><input type="text" name="calories" value="${ meal.calories }"></td>
                    <td>
                        <button onclick=location.href="meals?action=save&mealId=${id.count}">Save</button>
                    </td>
                    <br>
                </form>
            </c:if>
        </c:if>
    </c:forEach>
    </tbody>
</table>
<br>
<hr>
<form action="meals" method="get" >

    <table>
        <thead>
        <tr>
            <%--            <th>#</th>--%>
            <th>DateTime</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        </thead>
        <tbody>
        <input type="hidden" id="thisField" name="action" value="add">
        <td><input type="text" name="date" value="05.30.2015 10h00"></td>
        <td><select name="description">
            <option value="Breakfast" selected>Breakfast</option>
            <option value="Lunch">Lunch</option>
            <option value="Dinner">Dinner</option>
        </select></td>
        <td><input type="text" name="calories" value="500"></td>
        </tbody>
    </table>
    <br>
    <button onclick=location.href="meals?action=xxx">Add Meal</button>
</form>

<%--<form action="meals?action=save" method="get">--%>
<%--    <input type="hidden" id="thisField" name="inputName" value="hiddenValue">--%>
<%--    <input type="submit" value="Submit" pattern="action=save" >--%>
<%--</form>--%>


</body>
</html>