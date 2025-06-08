<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>학사 정보 시스템</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
</head>
<body>
<div class="container">
  <div class="home-icon">
    <a href="${pageContext.request.contextPath}/">
      <img src="${pageContext.request.contextPath}/resources/images/home-icon.png" alt="홈으로" />
    </a>
  </div>

  <h1 class="title">학사 정보 시스템</h1>
  <p class="subtitle">원하시는 서비스를 선택하세요</p>

  <div class="card-container">
    <div class="card">
      <img src="${pageContext.request.contextPath}/resources/images/credits.png" alt="이수 학점" />
      <h2>학년별 이수 학점 조회</h2>
      <p>학년-학기별로 이수한 총 학점을 확인할 수 있습니다.</p>
      <button onclick="location.href='${pageContext.request.contextPath}/credits'">바로가기</button>
    </div>

    <div class="card">
      <img src="${pageContext.request.contextPath}/resources/images/enroll.png" alt="수강 신청" />
      <h2>수강 신청하기</h2>
      <p>2025년 2학기에 신청할 교과목을 입력하여 수강 신청을 할 수 있습니다.</p>
      <button onclick="location.href='${pageContext.request.contextPath}/enroll'">바로가기</button>
    </div>

    <div class="card">
      <img src="${pageContext.request.contextPath}/resources/images/enrolled.png" alt="수강 신청 조회" />
      <h2>수강 신청 조회</h2>
      <p>2025년 2학기 수강 신청 내역을 확인할 수 있습니다.</p>
      <button onclick="location.href='${pageContext.request.contextPath}/enrolledCourses'">바로가기</button>
    </div>
  </div>
</div>
</body>
</html>
