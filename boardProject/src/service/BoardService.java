package service;

import static common.JdbcUtil.close;
import static common.JdbcUtil.commit;
import static common.JdbcUtil.getConnection;
import static common.JdbcUtil.rollback;

import java.sql.*;
import java.util.*;

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
	
//	public boolean loginMember(MemberVo memberVo) {
//		BoardDao dao = BoardDao.getInstance();
//		Connection con = getConnection();
//		dao.setConnection(con);
//		boolean isSucess = false;
//		int count_01 = dao.updateLoginState(memberVo);
//		
//		if (count_01 > 0) {
//			commit(con);
//			isSucess = true;
//		} else {
//			rollback(con);
//		}
//		
//		close(con);
//		return isSucess;
//	}
	
	public MemberVo getMember(String id) {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        MemberVo vo = dao.getMember(id);
        close(con);
        return vo;
    }
	
	public ArrayList<ArticleVo> getArticleList() {
        BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        ArrayList<ArticleVo> list = dao.getArticleList();
        close(con);
        return list;
    }
	
	public ArticleVo getArticle(int num) {
		BoardDao dao = BoardDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArticleVo vo = null;
		int count = dao.updateHitCount(num);
		if (count > 0) {
			commit(con);
			vo = dao.getArticle(num);
		} else {
			rollback(con);
		}
		close(con);
		return vo;
		
	}
}
