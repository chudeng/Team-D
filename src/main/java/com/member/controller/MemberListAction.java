package com.member.controller;
 
import java.util.ArrayList;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.action.Action;
import com.common.action.ActionForward;
import com.member.model.MemberVO;
import com.member.model.MemberDAO;
 
/**
 * 모든 회원정보를 보여주는 Action
 */
public class MemberListAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 회원정보를 가져온다.
        MemberDAO dao = MemberDAO.getInstance();
        ArrayList<MemberVO> memberList = dao.getMemberList();
        
        // MemberListForm.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
        request.setAttribute("memberList", memberList);
 
        // request를 유지해야 하므로 setRedirect(false)로 지정한다.
        forward.setRedirect(false);
        forward.setNextPath("MemberListForm.do");
        
        return forward;
    }
}