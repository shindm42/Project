package action;

import javax.servlet.http.*;

import common.*;

public class MemberLoginAction implements Action{
	
	@Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = new ActionForward();
        forward.setPath("/views/loginForm.jsp");
        return forward;
    }
}
