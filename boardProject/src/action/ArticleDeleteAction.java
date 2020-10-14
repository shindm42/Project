package action;

import java.io.*;

import javax.servlet.http.*;

import common.*;
import service.*;
import vo.*;

public class ArticleDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.3');location.href='/login.do';</script>");
			out.close();
			return null;
		}

		String num = request.getParameter("num");
		
		int buff = Integer.parseInt(num);
        if (buff <= 0) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
        }
        
		
		BoardService service = new BoardService();
		ArticleVo vo = new ArticleVo();
		
		
		String writerId = service.getWriterId(buff);
        if (writerId == null || !id.equals(writerId)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
        }
        
        if (!service.deleteArticle(buff)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('글을 삭제하는데 실패하였습니다.');location.href='/';</script>");
            out.close();
            return null;
        }
        
		ActionForward forward = new ActionForward();
		forward.setPath("/list.do");
		forward.setRedirect(true);
		return forward;
	}
}
