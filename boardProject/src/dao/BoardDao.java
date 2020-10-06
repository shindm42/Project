package dao;

import static common.JdbcUtil.close;

import java.sql.*;
import java.util.*;

import vo.*;

public class BoardDao {
	 private Connection con;

	    private BoardDao() {

	    }

	    public static BoardDao getInstance() {
	        return LazyHolder.INSTANCE;
	    }

	    private static class LazyHolder {
	        private static final BoardDao INSTANCE = new BoardDao();
	    }

	    public void setConnection(Connection con) {
	        this.con = con;
	    }
	    
	    public int insertMember(MemberVo memberVo){
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	int count_01 = 0;
	    	
	    	try {
	    		pstmt = con.prepareStatement("insert into mber_db (id,nickname,email,pwd) values (?,?,?,?)");
		    	pstmt.setString(1, memberVo.getId());
		    	pstmt.setString(2, memberVo.getNickname());
		    	pstmt.setString(3, memberVo.getEmail());
		    	pstmt.setString(4, memberVo.getPwd());
		    	
		    	count_01 = pstmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    	return count_01;
	    	
	    }
	    
	    public ArrayList<ArticleVo> getArticleList() {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        ArrayList<ArticleVo> list = new ArrayList<>();
	        try {
	            pstmt = con.prepareStatement
	                    ("select b.num" +
	                            ", m.id" +
	                            ", b.subject" +
	                            ", b.content" +
	                            ", b.hit" +
	                            ", b.wdate" +                       
	                            " from board AS b" +
	                            " inner join member AS m on b.mb_sq = m.sq");
	            rs = pstmt.executeQuery();
	            while(rs.next()) {
	                ArticleVo vo = new ArticleVo();
	                vo.setNum(rs.getInt("num"));
	                vo.setSubject(rs.getString("subject"));
	                vo.setContent(rs.getString("content"));
	                vo.setHit(rs.getInt("hit"));
	                vo.setWdate(rs.getString("wdate"));
	                vo.setId(rs.getString("id"));
	                list.add(vo);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            close(rs);
	            close(pstmt);
	        }
	        return list;
	    }
}
