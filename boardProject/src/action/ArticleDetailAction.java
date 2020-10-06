package action;

import java.io.*;

import javax.servlet.http.*;

import common.*;
import service.*;
import vo.*;

public class ArticleDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		
		int buff = Integer.parseInt(num);
		BoardService service = new BoardService();
		ArticleVo vo = service.getArticle(buff);
		if (vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		request.setAttribute("vo", vo);
		forward.setPath("/views/detail.jsp");
		return forward;
	}
}
