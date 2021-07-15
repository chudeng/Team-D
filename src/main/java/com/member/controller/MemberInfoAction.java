package com.member.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.action.Action;
import com.common.action.ActionForward;
import com.member.model.MemberVO;
import com.member.model.MemberDAO;
 
/** 
 * 현재 로그인한 사용자의 회원정보를 보여주는 Action 클래스
 */
public class MemberInfoAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 세션이 가지고있는 로그인한 ID 정보를 가져온다
        HttpSession session = request.getSession();
        String email = session.getAttribute("sessionEmail").toString();
        
        // 그 아이디 해당하는 회원정보를 가져온다.
        MemberDAO dao = MemberDAO.getInstance();
        MemberVO member = dao.getUserInfo(email);
        
        // UserInfoForm.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
        request.setAttribute("memberInfo", member);
        
        forward.setRedirect(false);
        forward.setNextPath("UserInfoForm.do");
        // MainForm.jsp?contentPage=member/view/UserInfoForm.jsp
        
        return forward;
    }
 
}