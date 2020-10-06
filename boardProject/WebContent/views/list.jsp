<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.ArticleVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<script>
	function goDetail(num) {
		location.href="/detail.do?num=" + num;
	}
</script>
</head>
<body>
	<table>
		<tr>
			<td>글 번호</td>
			<td>조회수</td>
			<td>작성 일시</td>
			<td>제목</td>
			<td>내용</td>
		</tr>
		<%for(int i = 0; i < list.size(); i++) {%>
		<tr onclick="goDetail(<%=list.get(i).getNum()%>)">
			<td><%=list.get(i).getNum() %></td>
			<td><%=list.get(i).getHit() %></td>
			<td><%=list.get(i).getWdate() %></td>
			<td><%=list.get(i).getSubject() %></td>
			<td><%=list.get(i).getContent() %></td>
		</tr>
		<%} %>
	</table>
	<button onclick="location.href='/write.do'">글쓰기</button>
</body>
</html>