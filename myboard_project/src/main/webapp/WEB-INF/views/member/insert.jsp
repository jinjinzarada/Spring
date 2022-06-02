<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
[[ ${msg }]]
	<form action="<%=request.getContextPath()%>/member/enroll" method="post">
		<div>아이디:<input type="text" name="id" required></div>
		<div>패스워드:<input type="password" name="passwd" required></div>
		<div>이름:<input type="text" name="name" required></div>
		<button type="submit">회원가입</button>
	</form>
</body>
</html>