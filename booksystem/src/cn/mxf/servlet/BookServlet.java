package cn.mxf.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mxf.dao.BookDao;
import cn.mxf.dao.CategoryDao;
import cn.mxf.entity.Book;
import cn.mxf.entity.Category;

// 通过注解的方式配置Servlet
@WebServlet("/book")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 获取BookDao对象
	private BookDao bookDao = new BookDao();
	// 获取CategoryDao对象
	private CategoryDao categoryDao = new CategoryDao();

	public BookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 解决乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/**
		 * 获取操作的参数，确定好下一步该做什么 
		 * op = list :查询所有的书籍信息 
		 * op = toAdd :跳转到添加书籍的页面 
		 * op = add :添加书籍 
		 * op = delete :删除书籍 
		 * op = getById :根据id值找到需要更改的书籍信息 
		 * op = modify :修改书籍信息
		 */
		// 根据传入的op判断执行那一个操作
		String op = request.getParameter("op");
		if (op == null || "list".equals(op)) {
			// 封装为方法
			list(request, response);
		} else if ("toadd".equals(op)) {
			toadd(request, response);
		} else if ("add".equals(op)) {
			add(request, response);
		} else if ("getById".equals(op)) {
			getById(request, response);
		} else if ("modify".equals(op)) {
			modify(request, response);
		} else if ("delete".equals(op)) {
			delete(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取id
		int id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		// 根据获取到的书籍id调用BookDao中的deleteBook(id)方法进行书籍的删除
		if (bookDao.deleteBook(id) > 0) {
			response.sendRedirect("book?op=list");
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 获取id
		int id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		String name = request.getParameter("name");// 书名
		double price = Double.parseDouble(request.getParameter("price"));// 价格
		String author = request.getParameter("author");// 作者
		Date pubDate = null;// 出版日期
		try {
			pubDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("pubDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));// 书籍分类
		// 根据获取到的书籍属性新创建一个书籍对象并调用BookDao中的modifyBook方法进行书籍的更新
		Book b = new Book(id, name, price, author, pubDate, categoryId);
		if (bookDao.modifyBook(b) > 0) {
			response.sendRedirect("book?op=list");
		} else {
			response.getWriter().print("修改失败!!");
		}
	}

	private void getById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取id
		int id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		// 根据id查询对应记录
		Book book = bookDao.selectBookById(id);
		List<Category> clist = categoryDao.getAll();
		request.setAttribute("clist", clist);
		request.setAttribute("book", book);
		request.getRequestDispatcher("modify.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String bookName = request.getParameter("name");// 书名
		double price = Double.parseDouble(request.getParameter("price"));// 价格
		String author = request.getParameter("author");// 作者
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date pubDate = null;// 出版日期
		try {
			pubDate = sdf.parse(request.getParameter("pubDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));// 书籍分类
		// 根据获取到的书籍属性新创建一个书籍对象并调用BookDao中的addBook方法进行书籍的添加
		Book b = new Book(bookName, price, author, pubDate, categoryId);
		if (bookDao.addBook(b) > 0) {
			response.sendRedirect("book?op=list");
		} else {
			response.getWriter().print("添加失败!!");
		}
	}
	private void toadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取所有的书籍分类名称
		List<Category> clist = categoryDao.getAll();
		request.setAttribute("clist", clist);
		request.getRequestDispatcher("add.jsp").forward(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取所有书籍
		List<Book> list = bookDao.getAll();
		// 获取所有书籍分类
		List<Category> clist = categoryDao.getAll();
		// 用来在同一个request周期中保存list变量使用
		request.setAttribute("list", list);
		// 用来在同一个request周期中保存clist变量使用
		request.setAttribute("clist", clist);
		// response.sendRedirect()方法是通过浏览器对象重定向的，在两个不同的页面间传值会生成两个不同的request对象。
		// 由于是在不同页面间传值，所以有使用RequestDispatcher接口的forward()方法。
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
