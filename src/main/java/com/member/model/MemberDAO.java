package com.member.model;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
 
import com.common.util.DBConnection;
 
 
/**
 * JSP_MEMBER 테이블과 연관된 DAO로
 * 회원 데이터를 처리하는 클래스이다.
 * <br><br>
 * Data Access Object - 테이블 당 한개의 DAO를 작성한다.
 */
public class MemberDAO 
{
    private static MemberDAO instance;
    
    // 싱글톤 패턴
    private MemberDAO(){}
    public static MemberDAO getInstance(){
        if(instance==null)
            instance=new MemberDAO();
        return instance;
    }
    
    /**
     * String -> java.sql.Date로 변경하는 메서드
     * <pre>
     * 문자열로된 생년월일을 Date로 변경하기 위해 필요하다.
     * java.util.Date클래스로는 오라클의 Date형식과 연동할 수 없다.
     * Oracle의 date형식과 연동되는 java의 Date는 java.sql.Date 클래스이다. </pre>
     * @param member 회원정보를 담고있는 TO
     * @return java.sql.Date
     */
    public Date stringToDate(MemberVO member)
    {
        String year = member.getBirthyy();
        String month = member.getBirthmm();
        String day = member.getBirthdd();
        
        Date birthday  = null;
        
        if(year != null && month != null && day != null)
            birthday = Date.valueOf(year+"-"+month+"-"+day);
        
        return birthday;
        
    } // end stringToDate()
    
    
    /**
     * 회원정보를 JSP_MEMBER 테이블에 저장하는 메서드
     * @param member 가입할 회원정보를 담고있는 TO
     * @throws SQLException
     */
    public void insertMember(MemberVO member) throws SQLException
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // 커넥션을 가져온다.
            conn = DBConnection.getConnection();
            
            // 자동 커밋을 false로 한다.
            conn.setAutoCommit(false);
            
            // 쿼리 생성한다.
            // 가입일의 경우 자동으로 세팅되게 하기 위해 sysdate를 사용
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO MEMBER_INFO (email, pwd, nickname, sex, birth, reg) VALUES");
            sql.append("(?, ?, ?, ?, ?, sysdate)");        
            stringToDate(member);
            /* 
             * StringBuffer에 담긴 값을 얻으려면 toString()메서드를
             * 이용해야 한다.
             */
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, member.getEmail());		// 완전한 이메일 입력시
//          pstmt.setString(1, member.getMail1()+"@"+member.getMail2());		//@기준 앞뒤 따로 받을 시
            pstmt.setString(2, member.getPwd());
            pstmt.setString(3, member.getNickname());
            pstmt.setString(4, member.getSex());
            pstmt.setDate(5, stringToDate(member));
            
            // 쿼리 실행
            pstmt.executeUpdate();
            // 완료시 커밋
            conn.commit(); 
            
        } catch (Exception sqle) {
            // 오류시 롤백
            conn.rollback(); 
            throw new RuntimeException(sqle.getMessage());
        } finally {
            // Connection, PreparedStatement를 닫는다.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } // end insertMember()
    
    
    /**
     * 아이디를 이용해 현재 회원정보를 가져온다.
     * @param id 회원 아이디
     * @return MemberBean
     */
    public MemberVO getUserInfo(String email) 
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVO member = null;
 
        try {
            // 쿼리
            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM MEMBER_INFO WHERE email=?");
 
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(query.toString());
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
 
            if (rs.next()) // 회원정보를 DTO에 담는다.
            {
                // DB의 생년월일정보 -> 년, 월, 일로 문자열 자른다.
                String birthday = rs.getDate("birth").toString();
                String year = birthday.substring(0, 4);
                String month = birthday.substring(5, 7);
                String day = birthday.substring(8, 10);
                
                // 이메일을 @ 기준으로 자른다.
//                String email = rs.getString("email");
//                int idx = mail.indexOf("@"); 
//                String mail1 = mail.substring(0, idx);
//                String mail2 = mail.substring(idx+1);
                
                // 자바빈에 정보를 담는다.
                member = new MemberVO();
                member.setEmail(rs.getString("email"));
                member.setPwd(rs.getString("pwd"));
                member.setNickname(rs.getString("nickname"));
                member.setSex(rs.getString("sex"));
                member.setBirthyy(year);
                member.setBirthmm(month);
                member.setBirthdd(day);
//                member.setMail1(mail1);
//                member.setMail2(mail2);
                member.setAdmin(rs.getInt("admin"));
                member.setGreeting(rs.getString("greeting"));
                member.setReg(rs.getTimestamp("reg"));
            }
 
            return member;
 
        } catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            // Connection, PreparedStatement를 닫는다.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }    // end getUserInfo
    
    
    /**
     * 회원정보를 수정한다.
     * @param member 수정할 회원정보를 담고있는 TO
     * @throws SQLException
     */
    public void updateMember(MemberVO member) throws SQLException{
        
        Connection conn = null;
        PreparedStatement pstmt = null;
 
        try {
 
            StringBuffer query = new StringBuffer();
            query.append("UPDATE MEMBER_INFO SET pwd=?, nickname=?, sex=?, birth=?, greeting=? WHERE email=?");
            
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(query.toString());
 
            // 자동 커밋을 false로 한다.
            conn.setAutoCommit(false);
            
            pstmt.setString(1, member.getPwd());
//            pstmt.setString(2, member.getMail1()+"@"+member.getMail2());
            pstmt.setString(2, member.getNickname());
            pstmt.setString(3, member.getSex());
            pstmt.setDate(4, stringToDate(member));
            pstmt.setString(5, member.getGreeting());
            pstmt.setString(6, member.getEmail());
            pstmt.executeUpdate();
            // 완료시 커밋
            conn.commit(); 
                        
        } catch (Exception sqle) {
            conn.rollback(); // 오류시 롤백
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } // end updateMember
    
    
    /**
     * 회원정보를 삭제한다.
     * @param id 회원정보 삭제 시 필요한 아이디
     * @param pw 회원정보 삭제 시 필요한 비밀번호
     * @return x : deleteMember() 수행 후 결과값
     */
    @SuppressWarnings("resource")
    public int deleteMember(String email, String pwd) 
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
 
        String dbpw = ""; // DB상의 비밀번호를 담아둘 변수
        int x = -1;
 
        try {
            // 비밀번호 조회
            StringBuffer query1 = new StringBuffer();
            query1.append("SELECT pwd FROM MEMBER_INFO WHERE email=?");
 
            // 회원 삭제
            StringBuffer query2 = new StringBuffer();
            query2.append("DELETE FROM MEMBER_INFO WHERE email=?");
 
            conn = DBConnection.getConnection();
 
            // 자동 커밋을 false로 한다.
            conn.setAutoCommit(false);
            
            // 1. 아이디에 해당하는 비밀번호를 조회한다.
            pstmt = conn.prepareStatement(query1.toString());
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
 
            if (rs.next()) 
            {
                dbpw = rs.getString("pwd");
                if (dbpw.equals(pwd)) // 입력된 비밀번호와 DB비번 비교
                {
                    // 같을경우 회원삭제 진행
                    pstmt = conn.prepareStatement(query2.toString());
                    pstmt.setString(1, email);
                    pstmt.executeUpdate();
                    conn.commit(); 
                    x = 1; // 삭제 성공
                } else {
                    x = 0; // 비밀번호 비교결과 - 다름
                }
            }
 
            return x;
 
        } catch (Exception sqle) {
            try {
                conn.rollback(); // 오류시 롤백
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } // end deleteMember
    
    
    /**
     * 모든 회원정보를 가져온다.
     * @return ArrayList<MemberBean> : 회원 List를 리턴한다.
     */
    public ArrayList<MemberVO> getMemberList()
    {
        ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVO member = null;
        
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT * FROM MEMBER_INFO");
            
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(query.toString());
            rs = pstmt.executeQuery();
            
            while (rs.next()) 
            {
                member = new MemberVO();
                member.setEmail(rs.getString("email"));
                member.setPwd(rs.getString("pwd"));
                member.setNickname(rs.getString("nickname"));
                member.setSex(rs.getString("sex"));
                member.setBirthyy(rs.getDate("birth").toString());
                member.setGreeting(rs.getString("Greeting"));
                member.setAdmin(rs.getInt("admin"));
                member.setReg(rs.getTimestamp("reg"));
                memberList.add(member);
            }
            
            return memberList;
            
        } catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            // Connection, PreparedStatement를 닫는다.
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }
 
    /**
     * 로그인시 아이디, 비밀번호 체크 메서드
     * @param id 로그인할 아이디
     * @param pw 비밀번호
     * @return x : loginCheck() 수행 후 결과값 
     */
    public int[] loginCheck(String email, String pwd) 
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
 
        String dbPW = ""; // db에서 꺼낸 비밀번호를 담을 변수
        int[] result = {-1, 0}; //로그인 결과값과 관리자 여부 값을 받을 배열
 
        try {
            // 쿼리 - 먼저 입력된 아이디로 DB에서 비밀번호를 조회한다.
            StringBuffer query = new StringBuffer();
            query.append("SELECT pwd, admin FROM MEMBER_INFO WHERE email=?");
 
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(query.toString());
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
 
            if (rs.next()) // 입려된 아이디에 해당하는 비번 있을경우
            {
                dbPW = rs.getString("pwd"); // 비번을 변수에 넣는다.
 
                if (dbPW.equals(pwd)) {
                    result[0] = 1; // 넘겨받은 비번과 꺼내온 비번 비교. 같으면 인증성공
                	result[1] = rs.getInt("admin");
                } else {                  
                	result[0] = 0; // DB의 비밀번호와 입력받은 비밀번호 다름, 인증실패
                	result[1] = 0; // 관리자 아님.
                }
            } else {
                result[0] = -1; // 해당 아이디가 없을 경우
                result[1] = 0;	// 관리자 아님.
            }
 
            return result;
 
        } catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } // end loginCheck()    
}