package vo;

public class MemberVo {
	private int mber_sq;
	private String id;
	private String pwd;
	private String nickname;
	private String email;
	private boolean lgn_fl;
	
	public boolean isLgn_fl() {
		return lgn_fl;
	}
	public void setLgn_fl(boolean lgn_fl) {
		this.lgn_fl = lgn_fl;
	}
	public int getMber_sq() {
		return mber_sq;
	}
	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		
	
}
