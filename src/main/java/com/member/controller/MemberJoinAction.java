package com.member.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.action.Action;
import com.common.action.ActionForward;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
 
/** 
 *  회원가입을 처리하는 Action 클래스<br>
 *  JoinForm.jsp에서 넘겨받은 정보를 이용하여
 *  회원가입을 처리한다.
 */
public class MemberJoinAction implements Action
{
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        request.setCharacterEncoding("euc-kr"); // 인코딩
        
        ActionForward forward = new ActionForward();
        
        MemberDAO dao = MemberDAO.getInstance();
        
        // 입력된 정보를 자바빈에 세팅한다.
        MemberVO member = new MemberVO();
        member.setEmail(request.getParameter("email"));
        member.setPwd(request.getParameter("pwd"));
        member.setNickname(request.getParameter("nickname"));
        member.setSex(request.getParameter("sex"));;
        member.setBirthyy(request.getParameter("birthyy"));
        member.setBirthmm(request.getParameterValues("birthmm")[0]);
        member.setBirthdd(request.getParameter("birthdd"));
//        member.setMail1(request.getParameter("mail1"));
//        member.setMail2(request.getParameterValues("mail2")[0]);
        
        // 회원가입 실행
        dao.insertMember(member);
        
        // 가입성공
        forward.setRedirect(true);
           forward.setNextPath("Result.do");
        
           // 가입성공 메시지를 세션에 담는다.
           request.getSession().setAttribute("msg", "1");
           
        return forward;
    }
}