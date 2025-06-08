<%--
  Created by IntelliJ IDEA.
  User: 000ju
  Date: 2025-04-12
  Time: 오전 3:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>학년-학기별 수강 내역</title>
  <link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/details.css" rel="stylesheet">
</head>
<body>
<!-- 홈 아이콘 -->
<div class="home-icon">
  <a href="${pageContext.request.contextPath}/">
    <img src="${pageContext.request.contextPath}/resources/images/home-icon.png" alt="홈으로">
  </a>
</div>

<div class="container">
  <h1>${year}년 ${semester}학기 수강 내역</h1>
  <table class="details-table">
    <thead>
    <tr>
      <th>년도</th>
      <th>학기</th>
      <th>교과목명</th>
      <th>교과구분</th>
      <th>담당교수</th>
      <th>학점</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="course" items="${courses}">
      <tr>
        <td>${course.year}</td>
        <td>${course.semester}</td>
        <td>${course.courseName}</td>
        <td>${course.courseType}</td>
        <td>${course.professor}</td>
        <td>${course.credits}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div class="back-link">
    <a href="${pageContext.request.contextPath}/credits">← 학년별 이수 학점 조회메뉴로 돌아가기</a>
  </div>
</div>
</body>
</html>
