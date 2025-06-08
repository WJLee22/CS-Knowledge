<%--스프링 프로젝트는 다음과 같은 방식으로 동작-->
<%--
1. 프로젝트 빌드: Maven을 사용하여 프로젝트를 빌드하고, war 아티팩트를 생성.
2. Tomcat 서버에 배포: 생성된 war 파일을 Tomcat 서버에 배포.
3. 서버 실행: Tomcat 서버가 실행되면, webapps 폴더에 있는 war 파일을 자동으로 찾아서 압축을 풀고, 애플리케이션을 배포. 이 과정에서 web.xml 파일을 읽고, 서블릿과 리스너 등을 설정하여 애플리케이션을 준비.
4. 요청 처리: 클라이언트가 Tomcat 서버의 특정 포트(8080)로 요청을 보냄.
5. 컨트롤러 처리 & 뷰 렌더링: HomeController가 루트 URL (/)로 들어오는 GET 요청을 처리 -> home.jsp 파일이 서블릿에 의해 처리되어 HTML로 컴파일됨 -> 클라이언트에게 응답으로 HTML 페이지(뷰)가 전송됨.
따라서, Tomcat 서버가 실행 중인 포트 (예: http://localhost:8080/)에 접속하면 home.jsp가 렌더링된 HTML 페이지를 볼 수 있게되는 것임.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  Hello World
  </body>
</html>
