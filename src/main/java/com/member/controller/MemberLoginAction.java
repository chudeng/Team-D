package com.member.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.action.Action;
import com.common.action.ActionForward;
import com.member.model.MemberDAO;
 
/**
 * 로그인 작업을 처리하는 Action 클래스
 */
public class MemberLoginAction implements Action
{
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        HttpSession session=request.getSession();
        
        // 아이디와 비밀번호를 가져온다.
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        
        // DB에서 아이디, 비밀번호 확인
        MemberDAO dao = MemberDAO.getInstance();
        int[] check = dao.loginCheck(email, pwd);
        
        if(check[0] == 0)    // 비밀번호 틀릴경우 -> 다시 로그인 화면으로 이동
        { 
            // 로그인 실패시 메시지를 request에 담는다.
               request.setAttribute("fail", "0");
               
               forward.setRedirect(false);
               forward.setNextPath("LoginForm.do");
        }
        else if(check[0] == -1) // 아이디가 없을 경우 -> 다시 로그인 화면으로 이동
        {
            request.setAttribute("fail", "-1");
 
               forward.setRedirect(false);
               forward.setNextPath("LoginForm.do");
        }
        else
        {
            //로그인 성공 -> 세션에 아이디와 관리자값을 저장
               session.setAttribute("sessionEmail", email);
               session.setAttribute("sessionAdmin", check[1]);
               
               // 로그인 성공후 메인화면으로 이동
               forward.setRedirect(true);
               forward.setNextPath("MainForm.do");
        }
           
        return forward;
    }
 
}