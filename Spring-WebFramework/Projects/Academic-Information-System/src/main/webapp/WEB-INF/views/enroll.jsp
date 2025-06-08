<%--
  Created by IntelliJ IDEA.
  User: 000ju
  Date: 2025-04-12
  Time: 오전 6:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>수강 신청하기</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/enroll.css">
</head>
<body>
<!-- 홈 아이콘 -->
<div class="home-icon">
    <a href="${pageContext.request.contextPath}/">
        <img src="${pageContext.request.contextPath}/resources/images/home-icon.png" alt="홈으로">
    </a>
</div>

<div class="container">
    <h1>수강 신청하기</h1>
    <p class="subtitle">2025년 2학기 수강신청 정보를 입력해주세요</p>

    <sf:form method="post" action="${pageContext.request.contextPath}/docreate" modelAttribute="course">
        <div class="form-grid">
            <div class="form-row">
                <div class="form-group">
                    <label>년도:</label>
                    <sf:input class="readonly-input" type="text" path="year" value="2025" readonly="readonly"/>
                    <sf:errors path="year" class="error"/>
                </div>

                <div class="form-group">
                    <label>학기:</label>
                    <sf:input class="readonly-input" type="text" path="semester" value="2" readonly="readonly"/>
                    <sf:errors path="semester" class="error"/>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>교과코드:</label>
                    <sf:input class="form-control" path="courseCode"/>
                    <sf:errors path="courseCode" class="error"/>
                </div>

                <div class="form-group">
                    <label>교과목명:</label>
                    <sf:input class="form-control" path="courseName"/>
                    <sf:errors path="courseName" class="error"/>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>교과구분:</label>
                    <sf:select class="form-control" path="courseType">
                        <sf:option value="" label="-- 교과구분 선택 --"/>
                        <sf:option value="전기" label="전기"/>
                        <sf:option value="전선" label="전선"/>
                        <sf:option value="전필" label="전필"/>
                        <sf:option value="선필교" label="선필교"/>
                        <sf:option value="교필" label="교필"/>
                        <sf:option value="일교" label="일교"/>
                        <sf:option value="일선" label="일선"/>
                    </sf:select>
                    <sf:errors path="courseType" class="error"/>
                </div>

                <div class="form-group">
                    <label>담당교수:</label>
                    <sf:input class="form-control" path="professor"/>
                    <sf:errors path="professor" class="error"/>
                </div>
            </div>

            <div class="form-row credits-row">
                <div class="form-group">
                    <label>학점:</label>
                    <sf:input class="form-control" path="credits"/>
                    <sf:errors path="credits" class="error"/>
                </div>
            </div>

            <div class="submit-group">
                <input type="submit" value="수강 신청" class="submit-button"/>
            </div>
        </div>
    </sf:form>
</div>
</body>
</html>
