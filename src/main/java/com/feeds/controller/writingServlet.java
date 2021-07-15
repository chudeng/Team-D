package com.feeds.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feeds.dao.FeedsDAO;
import com.feeds.dto.FeedsVO;

/**
 * Servlet implementation class writingServlet
 */
@WebServlet("/writing/do")
public class writingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public writingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "feeds/writing.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String created = request.getParameter("created");
		
		int result = 0;
		
		FeedsDAO fDao = FeedsDAO.getInstance();
		
		FeedsVO fVo = new FeedsVO();
		fVo.setEmail(email);
		fVo.setTitle(title);
		fVo.setContents(contents);
		fVo.setCreated(created);
		
		result = fDao.writing(fVo);
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			request.setAttribute("message", "게시물 추가 성공");
		}else {
			request.setAttribute("message", "게시물 추가 실패");
		}
		request.getRequestDispatcher("feeds/writing.jsp").forward(request, response);
		
		
	}

}
