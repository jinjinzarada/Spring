<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글읽기</title>
</head>
<body>
<div><button type="button" onclick="location.href='<%=request.getContextPath() %>/'">홈</button></div>
<c:choose>
<c:when test="${empty board }">
	<script>
		alert("해당글이 없습니다. 글목록으로 이동합니다. ");
		location.href="<%=request.getContextPath()%>/board/list";
	</script>
</c:when>
<c:otherwise>
	<div>
		<table border="1">
			<tr>
				<td>level</td>
				<td>ref</td>
				<td>rref</td>
				<td>seq</td>
				<td>번호</td>
				<td>제목</td>
				<td>작성일</td>
				<td>작성자</td>
				<td>조회수</td>
			</tr>
			<tr>
				<td>${board.board_level }</td>	
				<td>${board.board_ref }</td>	
				<td>${board.board_reply_ref }</td>	
				<td>${board.board_reply_seq }</td>			
				<td>${board.board_num }</td>
				<td>${board.board_title }</td>
				<td>${board.board_date }</td>
				<td>${board.board_writer }</td>
				<td>${board.board_readcount }</td>
			</tr>
		</table>
		<div>내용: ${board.board_content }</div>
	<c:if test="${not empty board.board_original_filename }">
		<div>첨부파일: ${board.board_original_filename }</div>
		<div><img src="<%=request.getContextPath() %>${board.board_rename_filename }" width="500"></div>
	</c:if>
		<div><button type="button" onclick="location.href='<%=request.getContextPath()%>/board/write?refnum=${board.board_num }'">답글쓰기</button></div>
	</div>
</c:otherwise>
</c:choose>
</body>
</html>