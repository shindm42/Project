package action;

import javax.servlet.http.*;

import common.*;
import service.*;
import vo.*;

public class MemberJoinFormAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		
		BoardService service = new BoardService();
		MemberVo memberVo = new MemberVo();
		
		memberVo.setId(id);
		memberVo.setEmail(email);
		memberVo.setNickname(nickname);
		memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		
		service.joinMember(memberVo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		//forward.setRedirect(true);
		return forward;
	}
}
