package com.member.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.action.Action;
import com.common.action.ActionForward;
import com.member.model.MemberDAO;
 
/**
 * 회원삭제 작업을 처리하는 Action 클래스
 */
public class MemberDeleteAction implements Action
{
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 세션이 가지고있는 로그인한 ID 정보를 가져온다
        HttpSession session = request.getSession();
        String email = session.getAttribute("sessionEmail").toString();
        String password = request.getParameter("pwd");
        
        MemberDAO dao = MemberDAO.getInstance();
        int check = dao.deleteMember(email, password);
        
        if(check == 1){
            session.removeAttribute("sessionEmail"); // 회원정보 담긴 세션 Email 삭제
            forward.setRedirect(true);
            forward.setNextPath("Result.do");
        }
        else{
            System.out.println("회원 삭제 실패");
            return null;
        }
        
        return forward;
    }
}