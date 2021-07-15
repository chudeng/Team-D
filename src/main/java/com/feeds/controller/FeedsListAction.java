package com.feeds.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feeds.model.FeedsVO;
import com.feeds.model.FeedsDAO;
import com.common.action.Action;
import com.common.action.ActionForward;

public class FeedsListAction implements Action
{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// 현재 페이지 번호 만들기
		int sPage = 1;
		String page = request.getParameter("page");

		if(page != null)	sPage = Integer.parseInt(page);
		
		// 검색조건과 내용을 가져옴
		String opt = request.getParameter("opt");
		String condition = request.getParameter("condition");
		
		// 검색조건과 검색내용을 Map에 담음
		HashMap<String, Object> listOpt = new HashMap<String, Object>();
		listOpt.put("opt", opt);
		listOpt.put("condition", condition);
		listOpt.put("start", sPage*10-9);
		
		FeedsDAO dao = FeedsDAO.getInstance();
		int listCount = dao.getFeedsListCount(listOpt);
		ArrayList<FeedsVO> list =  dao.getFeedsList(listOpt);
		
		// 한 화면에는 10개의 게시글이 보임
		// 보여지는 페이지수는 5개이며 그 이후는 "다음"으로 표시
		
		// 전체 페이지 수
		int maxPage = (int)(listCount/10.0 + 0.9);
		// 시작 페이지 번호
		int startPage = (int)(sPage/5.0 + 0.8) * 5 - 4;
		// 마지막 페이지 번호
		int endPage = startPage + 4;
		if(endPage > maxPage)	endPage = maxPage;
		
		// 4가지 페이지 번호 저장. 페이지1, 전체 페이수, 시작페이지, 마지막페이지
		request.setAttribute("sPage", sPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 글의 총 수와 목록을 저장
		request.setAttribute("list", list);
		
		// 조회만 하는 역할이므로 forward() 사용 (= DB의 상태 변호가 없으므로) 
		forward.setRedirect(false);
		forward.setNextPath("FeedsListForm.bo");
		
		return forward;
	}

}
