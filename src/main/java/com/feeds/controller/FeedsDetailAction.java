package com.feeds.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.feeds.model.FeedsVO;
import com.feeds.model.FeedsDAO;
import com.common.action.Action;
import com.common.action.ActionForward;
 
public class FeedsDetailAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 파라미터로 넘어온 글번호를 가져온다.
        String idx = request.getParameter("idx");
        int idxNum = Integer.parseInt(idx);
        
        String pageNum = request.getParameter("pageNum");	// 해당 게시글이 있는 페이지 번호를 가져온다. 목록으로 복귀 할때 원래 위치로 돌아가기 위함
        
        FeedsDAO dao = FeedsDAO.getInstance();
        FeedsVO feed = dao.getDetail(idxNum);		// idxNum(게시글 idx)로 해당 게시글 내용 가져옴
        
        request.setAttribute("feed", feed);
        request.setAttribute("pageNum", pageNum);

        forward.setRedirect(false); // 단순한 조회이므로
        forward.setNextPath("BoardDetailForm.bo");
        
        return forward;
    }
}