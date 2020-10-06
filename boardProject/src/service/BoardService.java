package service;

import static common.JdbcUtil.close;
import static common.JdbcUtil.commit;
import static common.JdbcUtil.getConnection;
import static common.JdbcUtil.rollback;

import java.sql.*;

import dao.*;
import vo.*;

public class BoardService {
	public boolean joinMember(MemberVo memberVo) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count_01 = dao.insertMember(memberVo);
		
		if (count_01 > 0) {
			commit(con);
			isSucess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		return isSucess;
	}
}
