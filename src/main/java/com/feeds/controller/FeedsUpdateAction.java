package com.feeds.controller;
 
import java.util.Enumeration;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
 
import com.feeds.model.FeedsVO;
import com.feeds.model.FeedsDAO;
import com.common.action.Action;
import com.common.action.ActionForward;
 
public class FeedsUpdateAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 답글 작성 후 원래 페이지로 돌아가기 위해 페이지 번호가 필요하다.
        String pageNum = request.getParameter("page");
        
        // 업로드 파일 사이즈
        int fileSize= 5*1024*1024;
        // 업로드될 폴더 절대경로
        String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
        
        try {
            MultipartRequest multi = new MultipartRequest
                    (request, uploadPath, fileSize, "euc-kr", new DefaultFileRenamePolicy());
            
            // 파리미터 값을 가져온다.
            int num = Integer.parseInt(multi.getParameter("board_num")); // 글 번호
            String subject = multi.getParameter("board_subject");    // 글 제목
            String content = multi.getParameter("board_content");    // 글 내용
            String existFile = multi.getParameter("existing_file");    // 기존 첨부 파일
            
            // 파라미터 값을 자바빈에 세팅한다.
            FeedsVO border = new FeedsVO();
            border.setBoard_num(num);
            border.setBoard_subject(subject);
            border.setBoard_content(content);
            
            // 글 수정 시 업로드된 파일 가져오기
            Enumeration<String> fileNames = multi.getFileNames();
            if(fileNames.hasMoreElements())
            {
                String fileName = fileNames.nextElement();
                String updateFile = multi.getFilesystemName(fileName);
                
                if(updateFile == null)    // 수정시 새로운 파일을 첨부 안했다면 기존 파일명을 세팅
                    border.setBoard_file(existFile);
                else    // 새로운 파일을 첨부했을 경우
                    border.setBoard_file(updateFile);
            }
            
            FeedsDAO dao = FeedsDAO.getInstance();
            boolean result = dao.updateBoard(border);
            
            if(result){
                forward.setRedirect(true); 
                // 원래있던 페이지로 돌아가기 위해 페이지번호를 전달한다.
                forward.setNextPath("BoardListAction.bo?page="+pageNum); 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("글 수정 오류 : " + e.getMessage());
        }
 
        return forward;
    }
}