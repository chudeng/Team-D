package com.human.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.dao.MemberDAO;

/**
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// idCheck.jsp 화면을 띄워준다.
	// join.jsp에서 받아온 userid를 idCheck.jsp로 전달하여 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");

		// DB에서 userid가 존재하는지 확인
		MemberDAO mDao = MemberDAO.getInstance();
		
		int result = mDao.confirmID(email);

		request.setAttribute("email", email);
		request.setAttribute("result", result);

		// 회원가입 화면을 표시한다.
		request.getRequestDispatcher("member/idCheck.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// 입력한 userid가 존재하는지 확인하여 결과를 idCheck.jsp로 전달한다.	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		String userid = request.getParameter("userid");

		// DB에서 userid가 존재하는지 확인
		MemberDAO mDao = MemberDAO.getInstance();
		int result = mDao.confirmID(userid);

		request.setAttribute("userid", userid);
		request.setAttribute("result", result);

		// 회원가입 화면을 표시한다.
		String url = "member/idCheck.jsp";
		request.getRequestDispatcher(url).forward(request, response);
		*/
	}

}