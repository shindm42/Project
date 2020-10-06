<%@ page import="vo.ArticleVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    ArticleVo vo = (ArticleVo) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>
<div>제목 : <%=vo.getSubject() %></div>
<div>내용 : <%=vo.getContent() %></div>

<button onclick="location.href='/list.do'">뒤로가기</button>
<%if (id.equals(vo.getId())) {%>
<button onclick="location.href='/update.do?num=<%=vo.getNum()%>'">수정</button>
<button onclick="location.href='/delete.do?num=<%=vo.getNum()%>'">삭제</button>
<%} %>
</body>
</html>