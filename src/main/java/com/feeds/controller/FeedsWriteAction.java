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

public class FeedsWriteAction implements Action
{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
				
		// 업로드 파일 사이즈
		int fileSize= 5*1024*1024;
		// 파일 업로드 경로
		String uploadPath = request.getServletContext().getRealPath("/UploadFolder");
		System.out.println("FeedsWriteAction 경로 : "+uploadPath);		
		try {
			
			// MultipartRequest	객체 생성. 파일 업로드
			MultipartRequest multi = new MultipartRequest
					(request, uploadPath, fileSize, "euc-kr", new DefaultFileRenamePolicy());

			// 파일이름을 Enumeration으로 추출. 
			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			if(names.hasMoreElements())
			{
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name);
			}
			
			FeedsDAO dao = FeedsDAO.getInstance();
			FeedsVO feedsData = new FeedsVO();
			
			feedsData.setIdx(dao.getSeq()); // Sequence 값을 가져옴.
			feedsData.setEmail(multi.getParameter("email"));
			feedsData.setTitle(multi.getParameter("title"));
			feedsData.setContents(multi.getParameter("contents"));
		
			boolean result = dao.feedsInsert(feedsData);
			
			if(result){
				forward.setRedirect(true);
				forward.setNextPath("FeedsListAction.bo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("글 작성 오류: " + e.getMessage());
		}
		return forward;
	}
}
