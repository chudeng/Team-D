package com.feeds.controller;
 
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.action.Action;
import com.common.action.ActionForward;
 
/**
 * 회원관련 Controller
 *
 */
public class FeedsController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
 
    /**
     * GET 방식일 경우 doGet()
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            doProcess(request,response);
    }      
        
    /**
     * POST 방식일 경우 doPost()
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            doProcess(request,response);
    }                          
        
    /**
     * 명령어에 따른 해당 Action을 지정해 준다.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doProcess(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException {
        
        // 넘어온 커맨드를 추출하는 과정
        String requestURI = request.getRequestURI();
        int cmdIdx = requestURI.lastIndexOf("/")+1;
        
        String command = requestURI.substring(cmdIdx);
        
        // URI, command 확인
        //System.out.println("requestURI : "+requestURI);
        //System.out.println("command : "+command);
        
        ActionForward forward = null;
        Action action = null;
        
        // 보여줄 화면 URL
        String form = "MainForm.jsp?contentPage=feeds/";
        
        // 커맨드에 해당하는 액션을 실행한다.
        try {
            // 화면전환
            if(command.equals("FeedsWriteForm.bo")) // 글쓰기 화면 이동
            {
                forward=new ActionForward();
                forward.setRedirect(false);
                forward.setNextPath(form + "FeedsWriteForm.jsp");
            }
            else if(command.equals("FeedsListForm.bo"))    // 게시글 리스트 화면 이동
            {
                forward=new ActionForward();
                forward.setRedirect(false);
                forward.setNextPath(form + "FeedsListForm.jsp");
            }
            else if(command.equals("FeedsDetailForm.bo"))    // 게시글 상세보기 화면 이동
            {
                forward=new ActionForward();
                forward.setRedirect(false);
                forward.setNextPath(form + "FeedsDetailForm.jsp");
            }
            else if(command.equals("FeedsReplyForm.bo"))    // 댓글 작성 화면 이동
            {
                forward=new ActionForward();
                forward.setRedirect(false);
                forward.setNextPath(form + "FeedsReplyForm.jsp");
            }
            else if(command.equals("FeedsUpdateForm.bo"))    // 게시글 수정 화면 이동
            {
                forward=new ActionForward();
                forward.setRedirect(false);
                forward.setNextPath(form + "FeedsUpdateForm.jsp");
            }
            
            // 각종 처리 액션
            else if(command.equals("FeedsWriteAction.bo")) // 글쓰기 처리
            {
                action = new FeedsWriteAction();
                forward = action.execute(request, response);
            }
            else if(command.equals("FeedsListAction.bo")) // 글쓰기 처리
            {
                action = new FeedsListAction();
                forward = action.execute(request, response);
            }
            else if(command.equals("FeedsDetailAction.bo")) // 글쓰기 처리
            {
                action = new FeedsDetailAction();
                forward = action.execute(request, response);
            }
//            else if(command.equals("FileDownloadAction.bo")) // 글쓰기 처리
//            {
//                action = new FileDownloadAction();
//                forward = action.execute(request, response);
//            }
//            else if(command.equals("FeedsReplyAction.bo")) // 답글 작성 처리
//            {
//                action = new FeedsReplyAction();
//                forward = action.execute(request, response);
//            }
//            else if(command.equals("FeedsReplyFormAction.bo")) // 답글달기 요청 처리
//            {
//                action = new FeedsReplyFormAction();
//                forward = action.execute(request, response);
//            }
            else if(command.equals("FeedsDeleteAction.bo")) // 게시글 삭제 처리
            {
                action = new FeedsDeleteAction();
                forward = action.execute(request, response);
            }
            else if(command.equals("FeedsUpdateFormAction.bo")) // 수정 글 처
            {
                action = new FeedsUpdateFormAction();
                forward = action.execute(request, response);
            }
            else if(command.equals("FeedsUpdateAction.bo")) // 수정 요청 처리
            {
                action = new FeedsUpdateAction();
                forward = action.execute(request, response);
            }
 
            // 화면이동 - isRedirext() 값에 따라 sendRedirect 또는 forward를 사용
            // sendRedirect : 새로운 페이지에서는 request와 response객체가 새롭게 생성된다.
            // forward : 현재 실행중인 페이지와 forwad에 의해 호출될 페이지는 request와 response 객체를 공유
            if(forward != null){
                if (forward.isRedirect()) {
                    response.sendRedirect(forward.getNextPath());
                } else {
                    RequestDispatcher dispatcher = request
                            .getRequestDispatcher(forward.getNextPath());
                    dispatcher.forward(request, response);
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end doProcess
}