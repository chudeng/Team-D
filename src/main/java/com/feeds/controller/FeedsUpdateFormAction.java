package com.feeds.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.feeds.model.FeedsVO;
import com.feeds.model.FeedsDAO;
import com.common.action.Action;
import com.common.action.ActionForward;
 
public class FeedsUpdateFormAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 페이지 번호와 글 번호를 가져온다.
        String pageNum = request.getParameter("page");
        String num = request.getParameter("num");
        int boardNum = Integer.parseInt(num);
 
        FeedsDAO dao = FeedsDAO.getInstance();
        FeedsVO board = dao.getDetail(boardNum);
        
        request.setAttribute("board", board);
        request.setAttribute("pageNum", pageNum);
        
        forward.setRedirect(false); // 단순한 조회이므로
        forward.setNextPath("BoardUpdateForm.bo");
        
        return forward;
    }
}