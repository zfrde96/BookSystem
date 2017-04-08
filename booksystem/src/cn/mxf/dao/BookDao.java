package cn.mxf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.mxf.entity.Book;
import cn.mxf.utils.BaseDao;
//本类为继承于BaseDao
public class BookDao extends BaseDao {
	// 实现查询所有书籍的功能
	public List<Book> getAll() {
		List<Book> list = new ArrayList<Book>();
		// 查询的SQL语句
		String sql = "select * from book";
		// 调用查询数据的方法对书籍进行查询操作
		ResultSet rs = this.executeQuery(sql);
		try {
			// 将查询的结果存放于List<Book>中
			while (rs.next()) {
				list.add(new Book(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 最后别忘记关闭连接
			this.close();
		}
		return list;
	}

	// 实现增加书籍的功能
	public int addBook(Book book) {
		String sql = "insert into book(name,price,author,pubDate,categoryId) values(?,?,?,?,?)";
		return this.executeUpdate(sql, book.getName(), book.getPrice(), book.getAuthor(),
				new SimpleDateFormat("yyyy-MM-dd").format(book.getPubDate()), book.getCategoryId());
	}

	// 实现通过ID找到对应书籍的功能--在此之后才能进行修改操作
	public Book selectBookById(int id) {
		// 通过id值查询对应书籍的SQL语句
		String sql = "select * from book where id=" + id;
		// 执行查询操作
		ResultSet rs = this.executeQuery(sql);
		try {
			if (rs.next()) {
				return new Book(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 最后别忘记关闭连接
			this.close();
		}
		return null;
	}

	// 实现修改书籍的功能--在此之前需要找到要修改的书籍信息
	public int modifyBook(Book book) {
		// 更新书籍信息的SQL语句
		String sql = "update book set name=?,price=?,author=?,pubDate=?,categoryId=? where id=?";
		return this.executeUpdate(sql, book.getName(), book.getPrice(), book.getAuthor(),
				new SimpleDateFormat("yyyy-MM-dd").format(book.getPubDate()), book.getCategoryId(), book.getId());
	}

	// 实现删除书籍的功能--直接通过id找到对应书籍然后删除
	public int deleteBook(int id) {
		// 删除书籍的SQL语句
		String sql = "delete from book where id=?";
		return this.executeUpdate(sql, id);
	}
}
