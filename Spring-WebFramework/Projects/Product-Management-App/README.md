# 📦 Product-Management-App

Spring Boot 기반 Product 관리 시스템 with Spring Security
> Spring Boot project for managing products with Spring Security-based authentication and authorization.

<br>

## 📌 프로젝트 개요
이 프로젝트는 Spring Security 기반의 사용자 인증 및 권한(Role) 기반 인가 기능을 통합한 상품 관리 웹 애플리케이션이다.  
Spring Boot & Spring Security 기반으로, 사용자 권한별로 기능 접근을 제한하고 보안을 강화하였다.  

<br>

## 🔧 기술 스택
- **Backend:** `Spring Boot 3`, `Spring Data JPA`, `Spring Security`
- **Frontend:** `Thymeleaf`
- **Database:** `MySQL`

<br>


## ✅ 주요 기능
###  ① 사용자 인증  
- 이메일 기반 회원가입 및 로그인 기능    
- 패스워드는 BCryptPasswordEncoder로 암호화하여 보안up  
- 로그인 성공/실패 시 맞춤 메시지 제공  

###  ② 권한(Role) 기반 인가
- **`ROLE_USER`:** 상품 조회 기능만 접근 가능 
- **`ROLE_ADMIN`:** 상품 등록, 수정, 삭제 및 관리자 페이지 접근 가능
- Thymeleaf에서 Spring Security 기반 인증 및 권한 상태에 따라 UI 요소 제어

###  ③ 추가 기능 
- 상품 등록/수정 시 유효성 검사 (예: 가격은 0 이상)      
- **`관리자 전용 대시보드(/admin/dashboard)`:** 총 사용자 수, 총 상품 수, 최근 사용자 및 상품 목록 표시  
- 사용자 조회 페이지: 시스템에 등록된 모든 사용자 정보를 한눈에 확인  

<br>

## 🖥️ 애플리케이션 실행 화면

### 🔹 data.sql기반, 기본 상품들을 product테이블에 저장
<img src="https://github.com/user-attachments/assets/269e3938-f974-4974-bf0f-602534a5a774" width="700"/>

<br><br>

### 🔹 홈 화면(“/”, “home” 루트 경로) 
<img src="https://github.com/user-attachments/assets/6f9ed876-712e-47f4-98eb-e1fa36f2425f" width="700"/>

<br><br>

### 🔹 회원가입 화면(“/signup”) 
<img src="https://github.com/user-attachments/assets/6a28f047-1f8b-4fd9-b07f-16963c2e218a" width="700"/>

<br><br>

### 🔹 로그인 화면(“/login”, GET) 
<img src="https://github.com/user-attachments/assets/40bcafe1-c848-407b-a04a-05643e985ff2" width="700"/>


<br><br>

### 🔹 로그인 성공시 화면(“/home”): `ROLE_USER`
<img src="https://github.com/user-attachments/assets/c9fe66cb-c598-467a-940f-0ae3f07f693d" width="700"/>

<br><br>

### 🔹 로그인 성공시 화면(“/home”): `ROLE_ADMIN`
<img src="https://github.com/user-attachments/assets/11e538b3-5907-45f7-bee6-8f1ee91d45be" width="700"/>

<br><br>

### 🔹 로그인 실패시 화면 (“/ login?error”) 
<img src="https://github.com/user-attachments/assets/cfb9d283-32b0-4457-bdd2-6637afc955c5" width="700"/>


<br><br>

### 🔹 로그아웃 화면 (“/ login?logout”) 
<img src="https://github.com/user-attachments/assets/55fc5fe0-e49b-4be5-af32-3d76ef5d3930" width="700"/>


<br><br>

### 🔹 상품 목록 조회 화면(“/products”, GET): `ROLE_USER`
<img src="https://github.com/user-attachments/assets/e5c2d76f-7e05-4ffd-af3a-f415ef4dece4" width="700"/>


<br><br>

### 🔹 상품 목록 조회 화면(“/products”, GET): `ROLE_ADMIN`
<img src="https://github.com/user-attachments/assets/2b0f996d-4613-4a52-b005-f74ae0a03b1d" width="700"/>


<br><br>

### 🔹 상품 등록 화면(“/products/new”): `ROLE_ADMIN`
<img src="https://github.com/user-attachments/assets/febb6f7d-101f-4361-ba3a-cf50f82989b7" width="700"/>


<br><br>

### 🔹 상품 수정 화면(“/products/edit/22”): `ROLE_ADMIN`
<img src="https://github.com/user-attachments/assets/e7c370fe-b8ae-4673-ab70-2a9e4b73ea88" width="700"/>


<br><br>

### 🔹 관리자 전용 대시보드 화면(“/admin/dashboard”): `ROLE_ADMIN`
<img src="https://github.com/user-attachments/assets/9e7c7f8a-9045-447a-9315-b7ab25d7a1c2" width="700"/>

<br><br>

### 🔹 전체 사용자 조회 화면(“/admin/users”): `ROLE_ADMIN`
<img src="https://github.com/user-attachments/assets/6c98336b-2413-410a-bc97-8d2e767ff5d2" width="700"/>

<br>
<br>
<hr>
<br>

## ✅ Dependencies Setting
![image](https://github.com/user-attachments/assets/7d34c63d-42d4-47bb-bfba-4c968f3d1a79)
