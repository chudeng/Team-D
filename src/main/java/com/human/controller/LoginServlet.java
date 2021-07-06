package com.human.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.human.dao.MemberDAO;
import com.human.dto.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do") // URL mapping
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      URL에 파라미터 정보가 담겨서 전송이 되기에 보안에 취약 할 수 있음.
	 *      URL뒤에 '?'를 붙여 파라미터와 URL을 구분하며, '&'로 파라미터를 구분. '='은 파라미터 이름과 값을 구분.
	 *      반면 정보를 식별하는 고유한 식별자 링크로 취급될 수 있다.
	 *      전송할 수 있는 정보의 길이가 제한 되어 있다.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/login.jsp";
//		Cookie와 Session의 개념. (참조 site. https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=suin2_91&logNo=221369666676)
//		HTTP의 약점을 보완하기 위한 개념. 어떤 약점?
//			1. Connectionless:클라이언트가 request를 서버에 보내면, 서버는 클라이언트에게 response를 하면 서로 접속을 끊는 특성입니다.
//			2. Stateless: 접속을 끊는 순간 클라이언트와 서버의 통신은 끝나고 상태 정보는 유지하지 않는 특성입니다.
//		위 두가지 특성은 자원 낭비를 줄인다는 큰 장점이 있지만, 동시에 통신을 할때마다 계속 인증이 필요한 약점이 있음.
//		(사이트에 로그인 했는데 매 페이지마다 로그인을 해야 한다고 생각해보라.)
//		이를 보완하기위해 나온게 Cookie와 Session으로 HTTP 프로토콜을 사용하여 서버가 클라이언트를 식별할 수 있도록 해준다.
//		두 기능의 차이점은 사이트 참조.
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") != null) { // 이미지 로그인 된 사용자임
		url = "main.jsp";
		}
//		getRequestDispatcher의 인자값으로 이동할 url을, 해당 url로 forward	메서드를 통해 이동하는데, 인자값으로 request, response를 줌.
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      Header의 body에 파라미터 정보가 담겨서 전송되어. URL에 정보가 표시되지 않음.
	 *     	doGet방식에 비해 보안성이 좋음.
	 *		전송할 수 있는 데이터 길이 제한이 없음.
	 *		서버쪽에 작업을 명령할 때 사용.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String url = ""; // forward할 주소를 지정하는 변수
		MemberVO member = null;

		// MemberDAO 객체생성
		MemberDAO mDao = MemberDAO.getInstance();

		// userid와 pwd DB에 있는지 유효성 검사
		int result = mDao.userCheck(email, pwd);

//		ID, password 일치시 main.jsp로 이동, password가 일치하지 않거나 없는 회원은 login.jsp로 이동.
		switch (result) {
		case 1: // userid와 pwd가 일치할 때
			// 사용자 정보를 DB에서 가져옴
			member = mDao.getUser(email);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member);
			request.setAttribute("message", "로그인에 성공하였습니다.");
			url = "main.jsp";
			break;
		case 0: // userid 일치 pwd 불일치
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
			url = "member/login.jsp";
			break;
		case -1: // userid가 존재하지 않음
			request.setAttribute("message", "존재하지않는 회원입니다.");
			url = "member/login.jsp";
			break;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}