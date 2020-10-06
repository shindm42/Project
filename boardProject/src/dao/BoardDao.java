package dao;

import java.sql.*;

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
}
