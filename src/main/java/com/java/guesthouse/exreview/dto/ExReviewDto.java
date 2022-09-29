package com.java.exreview.dto;

import java.util.Date;

public class ExReviewDto {
	private int exReserveCode;
	private int memberCode;
	private Date revDate;
	private String revContent;
	private int revRate;
	
	public ExReviewDto() {}

	public ExReviewDto(int exReserveCode, int memberCode, Date revDate, String revContent, int revRate) {
		
		this.exReserveCode = exReserveCode;
		this.memberCode = memberCode;
		this.revDate = revDate;
		this.revContent = revContent;
		this.revRate = revRate;
	}

	public int getExReserveCode() {
		return exReserveCode;
	}

	public void setExReserveCode(int exReserveCode) {
		this.exReserveCode = exReserveCode;
	}

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public Date getRevDate() {
		return revDate;
	}

	public void setRevDate(Date revDate) {
		this.revDate = revDate;
	}

	public String getRevContent() {
		return revContent;
	}

	public void setRevContent(String revContent) {
		this.revContent = revContent;
	}

	public int getRevRate() {
		return revRate;
	}

	public void setRevRate(int revRate) {
		this.revRate = revRate;
	}

	@Override
	public String toString() {
		return "ExReviewDto [exReserveCode=" + exReserveCode + ", memberCode=" + memberCode + ", revDate=" + revDate
				+ ", revContent=" + revContent + ", revRate=" + revRate + "]";
	}
	
	
	
	
	
	
	
}
