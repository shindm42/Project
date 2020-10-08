package action;

import java.io.*;

import javax.servlet.http.*;

import common.*;

public class ArticleWriteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		LoginManager lm = LoginManager.getInstance();
		if (lm.getMemberId(request.getSession()) == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/login.do';</script>");
			out.close();
			return null;
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/views/writeForm.jsp");
		return forward;
	}
}
