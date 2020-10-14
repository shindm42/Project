package action;

import static common.RegExp.ARTICLE_NUM;

import java.io.*;

import javax.servlet.http.*;

import common.*;
import service.*;
import vo.*;

public class ArticleUpdateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.1');location.href='/login.do';</script>");
			out.close();
			return null;
		}

		String num = request.getParameter("num");
		if (num == null || num.equals("") || !RegExp.checkString(ARTICLE_NUM, num)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('잘못된 접근입니다.1');location.href='/';</script>");
		}

		int buff = Integer.parseInt(num);
		if (buff <= 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.2');location.href='/';</script>");
			out.close();
			return null;
		}

		BoardService service = new BoardService();
		ArticleVo vo = service.getArticle(buff);
		if (vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.3');location.href='/';</script>");
			out.close();
			return null;
		}

		if (!vo.getId().equals(id)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.4');location.href='/';</script>");
			out.close();
			return null;
		}

		
		
		ActionForward forward = new ActionForward();
		request.setAttribute("vo", vo);
		forward.setPath("/views/updateForm.jsp");
		return forward;
	}
}
