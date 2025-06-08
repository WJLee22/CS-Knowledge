<%--
  Created by IntelliJ IDEA.
  User: 000ju
  Date: 2025-04-04
  Time: 오전 2:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/main.css" >

</head>
<body>

<!--modelAttribute 속성: 해당 모델 attribute객체의 데이터를 가지고 Data Buffering을 하여 기존 입력값을 다시 착착 바인딩 하겠다.(여기선 offer객체) -->
<sf:form method="post" action="${pageContext.request.contextPath}/docreate" modelAttribute="offer">
    <table class="formtable">
        <tr>
            <td class="label"> Name:</td>
            <!-- sf:input: input 태그를 생성하는 태그. type, path 속성으로 input의 타입과 바인딩할 modelAttribute객체의 필드명을 지정하면 해당 모델attribute의 필드 값이 바인딩됨 -->
            <!-- offer 객체의 name 필드와 이 입력 필드를 연결. 폼 제출 시 입력값이 offer.setName()으로 자동 저장됨 -->
            <td><sf:input class="control" type="text" path="name"/> <br/>
                <sf:errors path="name" class="error"/>
            </td>
        </tr>
        <tr>
            <td class="label"> Email:</td>
            <td><sf:input class="control" type="text" path="email"/> <br/>
                <sf:errors path="email" class="error"/>
            </td>
        </tr>
        <tr>
            <td class="label"> Offer:</td>
            <td><sf:textarea class="control" rows="10" cols="10" path="text"></sf:textarea>  <br/>
                <sf:errors path="text" class="error"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="새 제안"/> </td>
        </tr>
    </table>
</sf:form>

</body>
</html>
