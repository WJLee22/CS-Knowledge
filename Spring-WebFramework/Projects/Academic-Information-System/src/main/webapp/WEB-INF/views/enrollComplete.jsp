<%--
  Created by IntelliJ IDEA.
  User: 000ju
  Date: 2025-04-12
  Time: 오후 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수강 신청 완료</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/enrollComplete.css">
</head>
<body>
<div class="container">
    <div class="success-icon">
        <img src="${pageContext.request.contextPath}/resources/images/checkmark.png" alt="수강신청 완료">
    </div>
    <h1>수강 신청 완료</h1>
    <div class="subtitle">신청이 성공적으로 처리되었습니다.</div>

    <div class="message">
        <p>2025년 2학기 수강 신청 내역은 아래 버튼을 클릭하여 확인하실 수 있습니다.</p>
    </div>

    <div class="button-group">
        <a href="${pageContext.request.contextPath}/" class="back-button">
            홈으로
        </a>
        <a href="${pageContext.request.contextPath}/enrolledCourses" class="view-button">
            수강 신청 내역 보기
        </a>
    </div>
</div>
</body>
</html>
