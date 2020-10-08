package action;

import javax.servlet.http.*;

import common.*;

public class HomeAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/index.jsp");
		return forward;
	}
}
