package action;

import java.util.*;

import javax.servlet.http.*;

import common.*;
import service.*;
import vo.*;

public class ArticleListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		BoardService service = new BoardService();
		ArrayList<ArticleVo> list = service.getArticleList();
		
		ActionForward forward = new ActionForward();
		request.setAttribute("list", list);
		forward.setPath("/views/list.jsp");
		return forward;
	}
}
