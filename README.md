# SpringRepo_Week11
SpringBoot outline

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