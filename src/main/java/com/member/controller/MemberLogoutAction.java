package com.member.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.action.Action;
import com.common.action.ActionForward;
 
/**
 *  로그아웃 작업을 처리하는 Action 클래스
 */
public class MemberLogoutAction implements Action
{
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 로그아웃시 세션에 담긴 ID값을 삭제한다.
        request.getSession().removeAttribute("sessionEmail");
        
        // 로그아웃 후 메인화면으로 돌아간다.
        forward.setRedirect(true);
        forward.setNextPath("MainForm.do");
        
        return forward;
    }
}