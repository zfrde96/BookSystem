package cn.mxf.entity;

public class Category {
	// 书籍分类编号
	private int id;
	// 书籍对应分类名
	private String name;
	// 生成带参构造方法
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category(String name) {
		super();
		this.name = name;
	}
	// 生成无参构造方法
	public Category() {
	}
	// 并对属性进行设置set和get方法
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

}
