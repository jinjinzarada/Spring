<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
</head>
<body>
<div><button type="button" onclick="location.href='<%=request.getContextPath() %>/'">홈</button></div>
<c:choose>
<c:when test="${empty board }">
	<script>
		alert("해당글이없습니다. 글목록으로 이동합니다.");
		location.href="<%=request.getContextPath()%>/board/list";
	</script>
</c:when>
<c:otherwise>
	<div>
	<form action="<%=request.getContextPath() %>/board/updateDo" method="post"
		enctype="multipart/form-data">
<!-- 	input type="file" 이 있으니까 multipart 적어주기	-->
		<table border="1">
			<tr>
				<td>level</td>	
				<td>ref</td>	
				<td>rref</td>	
				<td>seq</td>			
				<td>번호</td>
				<td>작성일</td>
				<td>작성자</td>
				<td>조회수</td>
			</tr>
			<tr>
				<td>${board.board_level }</td>	
				<td>${board.board_ref }</td>	
				<td>${board.board_reply_ref }</td>	
				<td>${board.board_reply_seq }</td>			
				<td>${board.board_num }</a></td>
				<td>${board.board_date }</td>
				<td>${board.board_writer }</td>
				<td>${board.board_readcount }</td>	
			</tr>
		</table>
		<input type="hidden" name="board_num" value="${board.board_num }">
		<div>
			제목:<input type="text" name="board_title" value="${board.board_title }">
		</div>		
		<div>
			내용: <input type="text" name="board_content" value="${board.board_content }">
		</div>
		<div>변경할첨부파일:<input type="file" name="uploadfile"></div>
		
	<c:if test="${not empty board.board_original_filename }">
		<input type="hidden" name="board_rename_filename" value="${board.board_rename_filename }">
		<div>
		기존첨부파일: <input type="text" name="board_original_filename" value="${board.board_original_filename }" id="orgFile" readonly ]>
		<button type="button" onclick="document.getElementById('orgFile').value='';">기존파일삭제</button></div>
		<div><img src="<%=request.getContextPath() %>/${board.board_rename_filename }" width="500"></div>
	</c:if>
		<button type="reset">원래대로</button>
		<button type="submit">수정하기</button>
<!-- 		submit의 위치 /board/updateDo" method="post" -->
	</form>
	</div>
</c:otherwise>
</c:choose>
</body>
</html>