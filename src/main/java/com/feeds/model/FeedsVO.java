package com.feeds.model;
 
import java.sql.Date;
 
public class FeedsVO 
{
    private int idx;          // 글번호
    private String email;         // 글 작성자
    private String title;     // 글 제목
    private String contents;     // 글 내용
    private Date created;			// 글 작성일
    private Date updated;			// 글 수정일
    
    
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
    
}