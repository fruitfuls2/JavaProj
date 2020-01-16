package model;

import java.sql.Date;

public class Book {
	private String title; // 제목
	private String isbn; // 책번호
	private String author; // 작가
	private String publisher; // 출판사
	private Date loanDate; // 대출날짜
	private Date returnDate; // 반납날짜
	private String isOverdue; // 연체여부
	private String Genre; // 장르

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	public Book(String title, String isbn, String author, String publisher) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.publisher = publisher;
		
	}

	public Book(String title, String isbn, String author, String publisher, Date loanDate,
			Date returnDate) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.publisher = publisher;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
	}
	public Book(String title, String author, Date loanDate2, Date returnDate2, String isOverdue) {
		super();
		this.title = title;
		this.author = author;
		this.loanDate = loanDate2;
		this.returnDate = returnDate2;
		this.isOverdue = isOverdue;
	}

	public Book(String title, String isbn, String author) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public String getIsOverdue() {
		return isOverdue;
	}

}
