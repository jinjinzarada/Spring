<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<form action="login" method="post">
		<input type="text" name="mId" required="required">
		<input type="password" name="password">
		<br>
		page:<input type="text" name="page">
		<br>
		<button type="submit">로그인</button>
	</form>
	<button type="button" id="enroll">회원가입</button>
	<button type="button" id="findId">아이디찾기</button>
	<button type="button" id="findPwd">비밀번호찾기</button>
	<script>
		$("#enroll").click(function(){
			location.href = "enroll";
		});
		$("#findId").click(function(){
			location.href = "findId";
		});
		$("#findPwd").click(function(){
			location.href = "findPwd";
		});
	</script>
</body>
</html>
