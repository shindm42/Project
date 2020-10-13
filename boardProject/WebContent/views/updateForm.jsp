<%@page import="vo.ArticleVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArticleVo vo = (ArticleVo) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script src="https://code.jquery.com/jquery-3.5.1.slim.js"
	integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
	crossorigin="anonymous">
</script>
 <script>

        var content = '<%=vo.getCn()%>';

        function checkData() {
            var subject = $('#sj').val();
            if (!subject) {
                alert("제목을 입력하세요.");
                $('#sj').focus();
                return false;
            }
             var content = $('#cn').val();
             if (!content) {
                 alert("내용을 입력하세요.");
                 $('#cn').focus();
                 return false;
             }
            save();
        }
    </script>
<script>
	
</script>
</head>
<body>
<form id="editorForm" action="/updateProc.do" method="post">
	제목 <input type="text" name="subject" id="subject" maxlength="100" />
	내용  <jsp:include page="/editor/editorSkinForModify.jsp" flush="false"/>
    <input type="button" onclick="checkData()" value="수정">
</form>
</body>
</html>