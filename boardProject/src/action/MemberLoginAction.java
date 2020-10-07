package action;

import javax.servlet.http.*;

import common.*;
import service.*;
import vo.*;

public class MemberLoginAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		BoardService service = new BoardService();
		MemberVo memberVo = new MemberVo();
		
		memberVo.setId(id);
		memberVo.setPwd(pwd);
		
		service.joinMember(memberVo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		//forward.setRedirect(true);
		return forward;
	}
}
