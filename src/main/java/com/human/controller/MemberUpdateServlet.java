package com.human.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.MemberDAO;
import com.human.dto.MemberVO;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = mDao.getUser(email);
		
		request.setAttribute("mVo", mVo);
		request.getRequestDispatcher("member/memberUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");			//한글 깨짐 방지
		
		// 폼에서 입력한 회원 정보 얻어오기
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String nickname = request.getParameter("nickname");
		int sex = Integer.parseInt(request.getParameter("sex"));
		String greeting = request.getParameter("greeting");
		//propic variable
		
		// 회원 정보를 저장할 객체 생성
		MemberVO mVo = new MemberVO();
		mVo.setEmail(email);
		mVo.setPwd(pwd);
		mVo.setNickname(nickname);
		mVo.setSex(sex);
		mVo.setGreeting(greeting);
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		mDao.updateMember(mVo);
		response.sendRedirect("main.jsp");
	}

}
