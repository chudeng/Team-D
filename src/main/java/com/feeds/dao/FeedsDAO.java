package com.feeds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.common.util.DBConnection;
import com.feeds.dto.FeedsVO;

public class FeedsDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private FeedsDAO() {}

	private static FeedsDAO instance = new FeedsDAO();

	public static FeedsDAO getInstance() {
		return instance;
	}
	
	public FeedsVO feeds(String email) {
		
		
		return fVO;
	}
	
	public int writing(FeedsVO fVo) {
		int result = -1;
		
		String sql = 	"insert into feeds_info (idx, email, title, contents, created)" +
						" values" + 
						"(idx_seq.nextval, ?, ?, ?, ?)";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fVo.getEmail());
			pstmt.setString(2, fVo.getTitle());
			pstmt.setString(1, fVo.getContents());
			pstmt.setString(1, fVo.getCreated());
			
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
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
