package action;

import static common.RegExp.ARTICLE_CONTENT;
import static common.RegExp.ARTICLE_SUBJECT;

import java.io.*;

import javax.servlet.http.*;

import common.*;
import service.*;
import vo.*;

public class ArticleRegisterAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/login.do';</script>");
			out.close();
			return null;
		}

		String sj = request.getParameter("subject");
		String cn = request.getParameter("content");
		if (sj == null || sj.equals("") ||
				!RegExp.checkString(ARTICLE_SUBJECT, sj) ||
				cn == null || cn.equals("")	||
				!RegExp.checkString(ARTICLE_CONTENT, cn)) {
			 response.setContentType("text/html;charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
	            out.close();
	            return null;
		}

		
		
		BoardService service = new BoardService();
		ArticleVo vo = new ArticleVo();
		
		vo.setSj(sj);
		vo.setCn(cn);
		vo.setMber_sq(service.getMemberSequence(id));

		if (!service.insertArticle(vo)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('글을 저장하는데 실패하였습니다.');location.href='/';</script>");
            out.close();
            return null;
        }
		
		ActionForward forward = new ActionForward();
		forward.setPath("/list.do");
		forward.setRedirect(true);
		return forward;
	}
}
