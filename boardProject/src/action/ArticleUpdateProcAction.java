package action;

import static common.RegExp.ARTICLE_CONTENT;
import static common.RegExp.ARTICLE_NUM;
import static common.RegExp.ARTICLE_SUBJECT;

import java.io.*;

import javax.servlet.http.*;

import common.*;
import service.*;
import vo.*;

public class ArticleUpdateProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LoginManager lm = LoginManager.getInstance();
		//String id = request.getParameter("id");
		String id = lm.getMemberId(request.getSession());

		if (id == null || id.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.2');location.href='/';</script>");
			out.close();
			return null;
		}

		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		if (subject == null || subject.equals("") || !RegExp.checkString(ARTICLE_SUBJECT, subject) || content == null
				|| content.equals("") || !RegExp.checkString(ARTICLE_CONTENT, content)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		String num = request.getParameter("num");
		if (num == null || num.equals("") || !RegExp.checkString(ARTICLE_NUM, num)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		int buff = Integer.parseInt(num);
		if (buff <= 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		BoardService service = new BoardService();
		String writerId = service.getWriterId(buff);
		if (writerId == null || !id.equals(writerId)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		ArticleVo vo = new ArticleVo();
		vo.setArticl_sq(buff);
		vo.setSj(Parser.chgToStr(subject));
		vo.setCn(Parser.chgToStr(content));

		if (!service.updateArticle(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글을 수정하는데 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
//		String pageNum = request.getParameter("pn");
//        if (pageNum == null
//                || !RegExp.checkString(IS_NUMBER, pageNum)) {
//            response.setContentType("text/html;charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('잘못된 접근입니다.1');location.href='/';</script>");
//            out.close();
//            return null;
//        }

//        int page = Integer.parseInt(pageNum);
//        if (page < 1) {
//            response.setContentType("text/html;charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('잘못된 접근입니다.2');location.href='/';</script>");
//            out.close();
//            return null;
//        }

		ActionForward forward = new ActionForward();
		forward.setPath("/detail.do?pn=" + /*page*/ "&num=" + buff);
		forward.setRedirect(true);
		return forward;
	}
}
