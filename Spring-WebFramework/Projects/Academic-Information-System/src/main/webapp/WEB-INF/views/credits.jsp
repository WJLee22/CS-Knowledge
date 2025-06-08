<%--
  Created by IntelliJ IDEA.
  User: 000ju
  Date: 2025-04-11
  Time: 오후 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>학년별 이수 학점 조회</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/credits.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
</head>
<body>
<!-- 홈 아이콘 -->
<div class="home-icon">
    <a href="${pageContext.request.contextPath}/">
        <img src="${pageContext.request.contextPath}/resources/images/home-icon.png" alt="홈으로">
    </a>
</div>

<div class="container">
    <h1>학년별 이수 학점 조회</h1>
    <table class="credits-table">
        <thead>
        <tr>
            <th>년도</th>
            <th>학기</th>
            <th>취득 학점</th>
            <th>상세보기</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="semesterInfo" items="${creditsPerSemester}">
            <tr>
                <td>${semesterInfo.year}</td>
                <td>${semesterInfo.semester}</td>
                <td>${semesterInfo.totalCredits}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/details?year=${semesterInfo.year}&semester=${semesterInfo.semester}">링크</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>총계</td>
            <td></td>
            <td>${totalCredits}</td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
