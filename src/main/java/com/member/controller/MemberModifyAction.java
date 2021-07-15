package com.member.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.action.Action;
import com.common.action.ActionForward;
import com.member.model.MemberVO;
import com.member.model.MemberDAO;
 
/**
 * 회원정보 수정작업을 처리하는 Action 클래스
 *
 */
public class MemberModifyAction implements Action
{
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        request.setCharacterEncoding("euc-kr"); // 인코딩
        
        ActionForward forward = new ActionForward();
        
        MemberDAO dao = MemberDAO.getInstance();
        
        // 세션이 가지고있는 로그인한 ID 정보를 가져온다
        HttpSession session = request.getSession();
        String email = session.getAttribute("sessionEmail").toString();
        
        // 수정할 정보를 자바빈에 세팅한다.
        MemberVO member = new MemberVO();
        member.setEmail(email);
        member.setPwd(request.getParameter("pwd"));
        member.setNickname(request.getParameter("nickname"));
        member.setGreeting(request.getParameter("greeting"));
        member.setBirthyy(request.getParameter("birthyy"));
        member.setBirthmm(request.getParameter("birthmm"));
        member.setBirthdd(request.getParameter("birthdd"));
        member.setSex(request.getParameter("sex"));
        
        dao.updateMember(member);
 
        forward.setRedirect(true);
        forward.setNextPath("Result.do");
        
        // 회원정보 수정 성공 메시지를 세션에 담는다.
        session.setAttribute("msg", "0");
           
        return forward;
    }
 
}