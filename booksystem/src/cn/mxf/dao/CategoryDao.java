package cn.mxf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.mxf.entity.Category;
import cn.mxf.utils.BaseDao;
//本类为继承于BaseDao
public class CategoryDao extends BaseDao {
	// 实现查询所有书籍分类的功能
	public List<Category> getAll() {
		List<Category> list = new ArrayList<Category>();
		// 查询所有分类的SQL语句
		String sql = "select * from category";
		// 根据SQL语句执行查询操作
		ResultSet rs = this.executeQuery(sql);
		try {
			while (rs.next()) {
				// 将查询的结果存放于List<Category>中
				list.add(new Category(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 最后别忘记关闭连接
			this.close();
		}
		return list.size() > 0 ? list : null;
	}

}
