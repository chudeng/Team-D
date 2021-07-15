package com.feeds.model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
 
import com.common.util.DBConnection;
 
 
public class FeedsDAO 
{
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    private static FeedsDAO instance;
    
    private FeedsDAO(){}
    public static FeedsDAO getInstance(){
        if(instance==null)
            instance=new FeedsDAO();
        return instance;
    }
    
    // 시퀀스를 가져온다.
    public int getSeq()
    {
        int result = 1;
        
        try {
            conn = DBConnection.getConnection();
            
            // 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT idx_seq.NEXTVAL FROM DUAL");
            
            pstmt = conn.prepareStatement(sql.toString());
            // 쿼리 실행
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	result = rs.getInt(1);
            }
 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        close();
        return result;    
    } // end getSeq
    
    // 글 삽입
    public boolean feedsInsert(FeedsVO feed)
    {
        boolean result = false;
        
        try {
            conn = DBConnection.getConnection();
            
            // 자동 커밋을 false로 한다.
            conn.setAutoCommit(false);
            
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO FEEDS_INFO");
            sql.append(" VALUES(?,?,?,?,sysdate,sysdate)");
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, feed.getIdx());
            pstmt.setString(2, feed.getEmail());
            pstmt.setString(3, feed.getTitle());
            pstmt.setString(4, feed.getContents());
 
            int flag = pstmt.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }
            
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } 
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;    
    } // end boardInsert();
    
    
    // 글목록 가져오기
    public ArrayList<FeedsVO> getFeedsList(HashMap<String, Object> listOpt)
    {
        ArrayList<FeedsVO> list = new ArrayList<FeedsVO>();
        
        String opt = (String)listOpt.get("opt");
        String condition = (String)listOpt.get("condition");
        int start = (Integer)listOpt.get("start");
 
        
        try {
            conn = DBConnection.getConnection();
            StringBuffer sql = new StringBuffer();
            
            // 글목록 전체를 보여줄 때
            if(opt == null)
            {
                sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM rnum, idx, email, title, contents, created");
                sql.append(" from (SELECT * from FEEDS_INFO order by idx desc))");
                sql.append("WHERE rnum >= ? and rnum <= ?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setInt(1, start);
                pstmt.setInt(2, start+9);
                // StringBuffer를 비운다.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색
            {
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM rnum, idx, email, title, contents, created");
                sql.append(" from (SELECT * from FEEDS_INFO WHERE title like ? order by idx desc))");
                sql.append("WHERE rnum >= ? and rnum <= ?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색
            {
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM rnum, idx, email, title, contents, created");
                sql.append(" from (SELECT * from FEEDS_INFO WHERE contents like ? order by idx desc))");
                sql.append("WHERE rnum >= ? and rnum <= ?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용으로 검색
            {
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM rnum, idx, email, title, contents, created");
                sql.append(" from (SELECT * from FEEDS_INFO WHERE title like ? or contents like ? order by idx desc))");
                sql.append("WHERE rnum >= ? and rnum <= ?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setString(2, "%"+condition+"%");
                pstmt.setInt(3, start);
                pstmt.setInt(4, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) // 글쓴이로 검색
            {    
            	sql.append("SELECT * FROM");
                sql.append(" (SELECT  ROWNUM rnum, idx, email, title, contents, created");
                sql.append(" from (SELECT * from FEEDS_INFO WHERE email like ? order by idx desc))");
                sql.append("WHERE rnum >= ? and rnum <= ?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
      
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                FeedsVO feed = new FeedsVO();
                feed.setIdx(rs.getInt("idx"));
                feed.setEmail(rs.getString("email"));
                feed.setTitle(rs.getString("title"));
                feed.setContents(rs.getString("contents"));
                feed.setCreated(rs.getDate("created"));
                list.add(feed);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return list;
    } // end getFeedsList
    
    
    // 글의 개수를 가져오는 메서드
    public int getFeedsListCount(HashMap<String, Object> listOpt)
    {
        int result = 0;
        String opt = (String)listOpt.get("opt");
        String condition = (String)listOpt.get("condition");
        
        try {
            conn = DBConnection.getConnection();
            StringBuffer sql = new StringBuffer();
            
            if(opt == null)    // 전체글의 개수
            {
                sql.append("SELECT COUNT(*) FROM FEEDS_INFO");
                pstmt = conn.prepareStatement(sql.toString());
                
                // StringBuffer를 비운다.
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색한 글의 개수
            {
                sql.append("SELECT COUNT(*) FROM FEEDS_INFO WHERE title LIKE ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색한 글의 개수
            {
                sql.append("SELECT COUNT(*) FROM FEEDS_INFO WHERE contents LIKE ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용으로 검색한 글의 개수
            {
                sql.append("SELECT COUNT(*) FROM FEEDS_INFO WHERE title LIKE ? or contents LIKE ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) // 글쓴이로 검색한 글의 개수
            {
                sql.append("SELECT COUNT(*) FROM FEEDS_INFO WHERE email LIKE ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = pstmt.executeQuery();
            if(rs.next())    result = rs.getInt(1);
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;
    } // end getBoardListCount
    
    
    // 상세보기
    public FeedsVO getDetail(int idx)
    {    
    	FeedsVO board = null;
        
        try {
            conn = DBConnection.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("select * from FEEDS_INFO where idx = ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, idx);
            
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                board = new FeedsVO();
                board.setIdx(idx);
                board.setEmail(rs.getString("email"));
                board.setTitle(rs.getString("title"));
                board.setContents(rs.getString("contents"));
                board.setCreated(rs.getDate("created"));
                board.setUpdated(rs.getDate("updated"));
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return board;
    } // end getDetail()
    
//    
//    // 삭제할 파일명을 가져온다.
//    public String getFileName(int boardNum)
//    {
//        String fileName = null;
//        
//        try {
//            conn = DBConnection.getConnection();
//            
//            StringBuffer sql = new StringBuffer();
//            sql.append("SELECT BOARD_FILE from MEMBER_BOARD where BOARD_NUM=?");
//            
//            pstmt = conn.prepareStatement(sql.toString());
//            pstmt.setInt(1, boardNum);
//            
//            rs = pstmt.executeQuery();
//            if(rs.next()) fileName = rs.getString("BOARD_FILE");
//            
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        
//        close();
//        return fileName;
//    } // end getFileName
//    
    
 // 글 수정
    public boolean updateBoard(FeedsVO border) 
    {
        boolean result = false;
        
        try{
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // 자동 커밋을 false로 한다.
            
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE MEMBER_BOARD SET");
            sql.append(" BOARD_SUBJECT=?");
            sql.append(" ,BOARD_CONTENT=?");
            sql.append(" ,BOARD_FILE=?");
            sql.append(" ,BOARD_DATE=SYSDATE ");
            sql.append("WHERE BOARD_NUM=?");
 
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, border.getBoard_subject());
            pstmt.setString(2, border.getBoard_content());
            pstmt.setString(3, border.getBoard_file());
            pstmt.setInt(4, border.getBoard_num());
            
            int flag = pstmt.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }
            
        } catch (Exception e) {
            try {
                conn.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
    
        close();
        return result;
    } // end updateBoard
    
        
    // 게시글 삭제
    public boolean deleteBoard(int boardNum) 
    {
        boolean result = false;
 
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // 자동 커밋을 false로 한다.
 
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM MEMBER_BOARD");
            sql.append(" WHERE BOARD_NUM IN");
            sql.append(" (SELECT BOARD_NUM");
            sql.append(" FROM MEMBER_BOARD");
            sql.append(" START WITH BOARD_NUM = ?");
            sql.append(" CONNECT BY PRIOR BOARD_NUM = BOARD_PARENT) ");
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, boardNum);
            
            int flag = pstmt.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }    
            
        } catch (Exception e) {
            try {
                conn.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
 
        close();
        return result;
    } // end deleteBoard
    
    // DB 자원해제
    private void close()
    {
        try {
            if ( pstmt != null ){ pstmt.close(); pstmt=null; }
            if ( conn != null ){ conn.close(); conn=null;    }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    } // end close()
}