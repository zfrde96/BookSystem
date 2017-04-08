package cn.mxf.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//数据库连接类
public class BaseDao {
	//声明Connection连接类
	private Connection conn;
	//创建PreparedStatement对象，用来执行SQL语句
	private PreparedStatement ps;
	//用来存放数据的结果集
	private ResultSet rs;

	// 连接数据库
	public void getConnection() {
		// 加载驱动程序
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/booksys?characterEncoding=utf-8";
			//通过getConnection方法连接数据库
			conn = DriverManager.getConnection(url, "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭数据库  从后往前依次关闭
	public void close() {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 实现对数据的更新--增加，删除，修改操作；其中Object...为可变参数
	public int executeUpdate(String sql, Object... objects) {
		try {
			this.getConnection();
			ps = conn.prepareStatement(sql);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
				return ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return -1;
	}

	// 实现对数据的查询操作
	public ResultSet executeQuery(String sql, Object... objects) {
		try {
			this.getConnection();
			ps = conn.prepareStatement(sql);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
				return rs = ps.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
