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
		request.setCharacterEncoding("UTF-8");
		MemberVO mVo = new MemberVO();
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String nickname = request.getParameter("nickname");
		String greeting = request.getParameter("greeting");
		int sex = Integer.parseInt(request.getParameter("sex"));

		MemberDAO mDao = MemberDAO.getInstance();

		mVo.setEmail(email);
		mVo.setPwd(pwd);
		mVo.setNickname(nickname);
		mVo.setGreeting(greeting);
		mVo.setSex(sex);

		mDao.updateMember(mVo);
		
		response.sendRedirect("login.do");
	}

}
