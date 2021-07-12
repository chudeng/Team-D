// DB와 연동해서 작업하는 DB처리 클래스.
// DAO(Data Access Object): DB에 접근을 담당하는 클래스.
// DB의 레코드를 조회하고 추가, 수정 삭제를 함.

package com.human.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.human.dto.MemberVO;
import com.common.util.DBConnection;

public class MemberDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberDAO() {}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	/*
	 * 사용자가 입력한 userid, pwd가 일치하는지 확인 매개변수 : userid: 사용자가 입력한 아이디, pwd : 사용자가 입력한 암호
	 * 리턴값 : userid와 pwd가 테이브렝 존재하면 1을 반환 userid는 존재하지면 pwd가 틀리면 0을 반환 userid가 존재하지
	 * 않으면 -1을 반환
	 * 
	 */
	public int userCheck(String email, String pwd) {
	
		int result = -1;

		// membership 테이블에서 사용자 아이디가 userid인 레코드의 pwd column을 조회
		String sql = "select email, pwd from member_info where email=?";

		try {
			conn = DBConnection.getConnection(); // DB 연결 시도
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String pwd_in_db = rs.getString("pwd");
				if (pwd_in_db != null && pwd.equals(pwd_in_db)) {
					// user id를 조건으로 pwd가 조회되면 입력받은 userid가 존재한다는 의미.
					result = 1;
				} else {
					result = 0;
				}
			} else {
				// 조회한 결과가 값이 없으므로 userid가 존재하지 않음.
				result = -1;
			}
			/*
			 * String memberid = rs.getString("userid"); String memberpwd =
			 * rs.getString("pwd"); int result;
			 * 
			 * if (memberid.isEmpty()) { result = -1; } else { if (memberid.equals(userid))
			 * { if (memberpwd.equals(pwd)) { result = 1; } else { result = 0; } } else {
			 * result = -1; } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/*
	 * 매개변수에 입력된 사용자 id를 갖고 DB에서 사용자 정보를 조회하여 반환하는 메소드
	 */
	public MemberVO getUser(String email) {
		MemberVO mVO = null; // 데이터가 없을 경우 null 값을 반환.

		String sql = "select * from member_info where email=?";

		try {
			conn = DBConnection.getConnection(); // DB 연결 시도
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mVO = new MemberVO();
				mVO.setEmail(rs.getString("email"));
				mVO.setPwd(rs.getString("pwd"));
				mVO.setNickname(rs.getString("nickname"));
				mVO.setSex(rs.getInt("sex"));
				mVO.setAdmin(rs.getInt("admin"));
				mVO.setGreeting(rs.getString("greeting"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return mVO;
	}
	
	// 지정한 아이디가 있으면 1, 없으면 -1을 반환한다.
		public int confirmID(String email) {
			int result = -1;

			String sql = "select pwd from member_info where email=?";

			try {
				conn = DBConnection.getConnection(); // DB 연결 시도
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);

				rs = pstmt.executeQuery();

				if (rs.next()) { // 조회 결과가 있으면 userid가 존재한다는 의미
					result = 1;
				} else {
					// 조회한 결과가 값이 없으므로 userid가 존재하지 않음.
					result = -1;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (rs != null) {
						rs.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
	    
	    public int insertMember(MemberVO mVo) {

			int result = 0;

			String sql = "insert into member_info(email, pwd, nickname, sex) values(?, ?, ?, ?)";

			try {
				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mVo.getEmail());
				pstmt.setString(2, mVo.getPwd());
				pstmt.setString(3, mVo.getNickname());
				pstmt.setInt(4, mVo.getSex());

				result = pstmt.executeUpdate(); // 성공하면 insert가 성공한 행의 수를 반환, 싪패하면 0
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (rs != null) {
						rs.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
	    
	    
	    public int updateMember(MemberVO mVo) {
			int result = -1;

			String sql = "update member_info set pwd = ?, nickname = ?, sex = ?, greeting = ? where email = ?";

			try {
				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mVo.getPwd());
				pstmt.setString(2, mVo.getNickname());
				pstmt.setInt(3, mVo.getSex());
				pstmt.setString(4, mVo.getGreeting());
				pstmt.setString(5, mVo.getEmail());

				result = pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return result;
		}
}