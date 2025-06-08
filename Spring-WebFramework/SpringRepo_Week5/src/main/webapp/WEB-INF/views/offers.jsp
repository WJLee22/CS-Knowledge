<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 000ju
  Date: 2025-03-31
  Time: 오전 6:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--컨트롤러에서 서비스 -> DAO -> DB에서 데이터 꺼내서 모델에 저장 -> 컨트롤러에서 해당 모델 객체가 넘어옴. -->
<!--모델에 있는 id_offers attribute 에서 하나씩 끄집어낸 값인 offer -->
<!-- id_offers는 offers테이블의 모든 행에 대한 데이터를 저장해둔 객체에 대한 키이므로, 해당 offers의 forEach로 순회한 각 요소의 값은 offers테이블의 각 행 데이터임(id, name, email, text)  -->
    <c:forEach var="offer" items="${id_offers}">
        <p> <c:out value="${offer}"></c:out> </p>
    </c:forEach>
</body>
</html>
