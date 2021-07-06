package com.human.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.human.dao.MemberDAO;
import com.human.dto.MemberVO;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/join.jsp";
		// 회원가입 화면을 표시한다.
//		getRequestDispatcher의 인자값으로 이동할 url을, 해당 url로 forward	메서드를 통해 이동하는데, 인자값으로 request, response를 줌.
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String nickname = request.getParameter("nickname");
		int sex = Integer.parseInt(request.getParameter("sex"));
		int result = 0;
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		MemberVO mVo = new MemberVO();
		mVo.setEmail(email);
		mVo.setPwd(pwd);
		mVo.setNickname(nickname);
		mVo.setSex(sex);
		
		result = mDao.insertMember(mVo);
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			request.setAttribute("message", "회원 가입 성공");
		}else {
			request.setAttribute("message", "회원 가입 실패");
		}
		request.getRequestDispatcher("member/login.jsp").forward(request, response);
		
	}

}
