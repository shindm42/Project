package dao;

import static common.JdbcUtil.close;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

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

	public int insertMember(MemberVo memberVo) {
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
			pstmt = con.prepareStatement("select * from articl_db");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVo vo = new ArticleVo();
				vo.setArticl_sq(rs.getInt("articl_sq"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setSj(rs.getString("sj"));
				vo.setCn(rs.getString("cn"));
				vo.setHit(rs.getInt("hit"));
				vo.setDttm(rs.getString("dttm"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public ArticleVo getArticle(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVo vo = null;
		try {
			pstmt = con.prepareStatement("select" + " a.articl_sq" + " , a.mber_sq" + ", a.sj" + ", a.cn" + ", a.hit"
					+  ", a.dttm" + ", b.id"
					+ " from articl_db a inner join mber_db b on a.mber_sq = b.sq where articl_sq=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new ArticleVo();
				vo.setArticl_sq(rs.getInt("articl_sq"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setSj(rs.getString("sj"));
				vo.setCn(rs.getString("cn"));
				vo.setHit(rs.getInt("hit"));
				vo.setDttm(rs.getString("dttm"));
				vo.setId(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}

	public int updateHitCount(int num) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update articl_db set hit=hit+1 where articl_sq=?");
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public MemberVo getMember(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		try {
			pstmt = con.prepareStatement("select sq, id, pwd from mber_db where binary(id)=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setMber_sq(rs.getInt("sq"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}

	public int updateLoginState(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update mber_db set id=? where mber_sq=?");
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int insertArticle(ArticleVo vo) {
		PreparedStatement pstmt = null;

		int count_01 = 0;

		try {
			pstmt = con.prepareStatement("insert into articl_db (mber_sq , sj , cn) values (?,?,?)");
			pstmt.setInt(1, vo.getMber_sq());
			pstmt.setString(2, vo.getSj());
			pstmt.setString(3, vo.getCn());

			count_01 = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count_01;
	}

	public int updateArticle(ArticleVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update articl_db set sj=?, cn=?, udate=? where articl_sq=?");
			pstmt.setString(1, vo.getSj());
			pstmt.setString(2, vo.getCn());
			pstmt.setString(3,
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pstmt.setInt(4, vo.getArticl_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int deleteArticle(int articl_sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("delete from articl_db where articl_sq=?");
			pstmt.setInt(1, articl_sq);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public String getWriterId(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		try {
			pstmt = con.prepareStatement("select" + " m.id" + " from articl_db b"
					+ " inner join mber_db m on b.mber_sq = m.sq" + " where articl_sq=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return id;
	}
	
	public int getMemberSequence(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			pstmt = con.prepareStatement("select" + " sq" + " from mber_db"
					+ " where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("sq");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return num;
	}
}
