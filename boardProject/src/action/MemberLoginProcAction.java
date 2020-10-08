package action;

import java.io.*;

import javax.servlet.http.*;

import common.*;
import service.*;
import vo.*;

public class MemberLoginProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		if (id == null || id.equals("") || pwd == null || pwd.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		
		BoardService service = new BoardService();
		MemberVo memberVo = service.getMember(id);
		if (memberVo == null || !BCrypt.checkpw(pwd, memberVo.getPwd())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인해 주세요.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		memberVo.setLgn_fl(true);
   
		
//		if (!service.loginMember(memberVo)) {
//            response.setContentType("text/html;charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('로그인 처리에 실패하였습니다.');location.href='/';</script>");
//            out.close();
//            return null;
//        }

        
        
		LoginManager lm = LoginManager.getInstance();
		lm.setSession(request.getSession(), memberVo.getId());

		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
