<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.ArticleVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<ArticleVo> list = (ArrayList<ArticleVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<script>
	function goDetail(num) {
		location.href="detail.do?num=" + num;
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
		<tr onclick="goDetail(<%=list.get(i).getArticl_sq()%>)">
			<td><%=list.get(i).getArticl_sq() %></td>
			<td><%=list.get(i).getHit() %></td>
			<td><%=list.get(i).getDttm() %></td>
			<td><%=list.get(i).getSj() %></td>
			<td><%=list.get(i).getCn() %></td>
		</tr>
		<%} %>
	</table>
	<button onclick="location.href='/write.do'">글쓰기</button>
</body>
</html>