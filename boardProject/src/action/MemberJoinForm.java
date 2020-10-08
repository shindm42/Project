package action;

import javax.servlet.http.*;

import common.*;

public class MemberJoinForm implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/joinForm.jsp");
		return forward;
	}
}
