package com.feeds.controller;
 
import java.io.File;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.feeds.model.FeedsDAO;
import com.common.action.Action;
import com.common.action.ActionForward;
 
public class FeedsDeleteAction implements Action
{
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 글번호를 가져온다.
        String num = request.getParameter("num");
        int boardNum = Integer.parseInt(num);
        
        FeedsDAO dao = FeedsDAO.getInstance();
        // 삭제할 글에 있는 파일 정보를 가져온다.
        String fileName = dao.getFileName(boardNum);
        // 글 삭제 - 답글이 있을경우 답글도 모두 삭제한다.
        boolean result = dao.deleteBoard(boardNum);
        
        // 파일삭제 
        if(fileName != null)
        {
            // 파일이 있는 폴더의 절대경로를 가져온다.
            String folder = request.getServletContext().getRealPath("UploadFolder");
            // 파일의 절대경로를 만든다.
            String filePath = folder + "/" + fileName;
 
            File file = new File(filePath);
            if(file.exists()) file.delete(); // 파일은 1개만 업로드 되므로 한번만 삭제하면 된다.
        }
        
        if(result){
            forward.setRedirect(true);
            forward.setNextPath("BoardListAction.bo");
        }
        else
            return null;
 
        return forward;
    }
}