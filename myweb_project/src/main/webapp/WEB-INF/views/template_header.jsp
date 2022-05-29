<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="wrap header">
    <header>

<c:choose>
	<c:when test="${not empty loginSsInfo}">
		<button id="mypage">마이페이지</button><br>
		<button onclick="logout();">로그아웃</button><br>
	</c:when>
	<c:otherwise>
		<button id="login">로그인</button><br>
	</c:otherwise>
</c:choose>

	<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
	        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
	        laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
	        voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
	        cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem
	        ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
	        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
	        laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
	        voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
	        cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
	</header>
<script>
	$("#login").click(function(){
		location.href = "login";   // a href 동일 get 방식
	});
	/* 
	$("#logout").click(function(){
		location.href = "logout";
	});
	 */
	function logout(){
		location.href = "<%=request.getContextPath()%>/logout";
	}
	$("#mypage").click(function(){
		location.href = "mypage";
	});
</script>
</div>