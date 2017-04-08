package cn.mxf.entity;

import java.util.Date;

//书籍类
public class Book {

	private int id;// 书籍编号
	private String name;// 书籍名称
	private double price;// 价格
	private String author;// 作者
	private Date pubDate;// 出版日期
	private int categoryId;

	public Book() {
	}

	public Book(String name, double price, String author, Date pubDate, int categoryId) {
		super();
		this.name = name;
		this.price = price;
		this.author = author;
		this.pubDate = pubDate;
		this.categoryId = categoryId;
	}

	public Book(int id, String name, double price, String author, Date pubDate, int categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.author = author;
		this.pubDate = pubDate;
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
