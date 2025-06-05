# SpringRepo_Week11
SpringBoot outline

-  `.\mvnw.cmd clean package` 명령어를 실행한다.  
  - `mvnw`는 Maven Wrapper로, Maven이 설치되어 있지 않은 환경에서도 Maven 명령어를 실행할 수 있게 해준다.  
  - `clean`은 이전 빌드 결과물을 삭제하고, `package`는 프로젝트를 빌드하여 `.jar` 파일을 생성한다.
  - `package`는 프로젝트를 빌드하고, `.jar` 파일을 생성하는 단계로, 다음 3가지 작업을 한번에 수행한다.  
    - `compile`: 소스 코드를 컴파일하여 바이트코드로 변환한다.
    - `test`: 테스트 코드를 실행하여 기능 검증을 진행한다.
    - `package`: 컴파일된 코드와 리소스를 `.jar` 파일로 패키징한다.

- `.java` 소스코드를 컴파일하여 `.class` 바이트코드로 변환한다.  

- 테스트 코드를 실행해 기능 검증을 진행한다.(단위 테스트)

- 프로젝트 전체를 실행 가능한 `.jar` 파일로 패키징한다. (Spring Boot의 경우 `fat jar` 또는 `uber jar`라고도 함)  
  - `.jar` 파일은 Java 애플리케이션을 실행할 수 있는 압축 파일 형식이다.  
  - `.jar` 파일은 Java Virtual Machine(JVM)에서 실행할 수 있다.  
  - `.jar` 파일은 Java 애플리케이션의 클래스 파일, 리소스 파일, 메타데이터 등을 포함한다.

- 생성된 `.jar`에는 작성한 코드, 의존성 라이브러리, 내장 톰캣이 포함된다.  
  - 작성한 코드: @RestController, @Service, @Entity, JPA, HTML, static/ 리소스 등 모든 .java → .class
  - 의존성 라이브러리: pom.xml에 정의된 의존성 라이브러리들(Hibernate, Spring Security, JPA, Jackson, Thymeleaf, Logback, 등등 .jar 형태로 BOOT-INF/lib에 포함)
  - 내장 톰캣: Spring Boot는 내장 톰캣을 제공하여 별도의 서버 설치 없이 애플리케이션을 실행할 수 있다.(tomcat-embed-core, tomcat-embed-websocket, tomcat-embed-el 등 — 톰캣을 직접 포함해서 서버 역할 수행 가능)  


- `.jar` 파일을 실행하면 내장 톰캣이 시작되고, 애플리케이션이 실행된다.
  - `java -jar your-application.jar` 명령어로 실행할 수 있다.(java -jar .\target\helloSpringBoot-0.0.1-SNAPSHOT.jar)
  - 내장 톰캣이 애플리케이션을 호스팅하고, HTTP 요청을 처리한다.
  - 애플리케이션은 지정된 포트(기본적으로 8080)에서 실행된다.