package com.lwb.store.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lwb.store.dao.ProductDao;
import com.lwb.store.domain.Product;
import com.lwb.store.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao {
	
	//查询最新商品
	@Override
	public List<Product> findNewProduct() throws SQLException {
		String sql = "SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0 ,9  ";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	//查询最热商品
	@Override
	public List<Product> findHotProduct() throws SQLException {
		String sql = "SELECT * FROM product WHERE pflag=0 AND is_hot=1 ORDER BY pdate DESC LIMIT 0 ,9 ";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
	}
	
	//根据pid查询商品信息
	@Override
	public Product findProductByPid(String pid) throws SQLException {
		String sql = "select*from product where pid=?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanHandler<Product>(Product.class), pid);
	}

	@Override
	public int findTotalRecords(String cid) throws SQLException {
		String sql = "select count(*) from product where cid = ?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long)queryRunner.query(sql, new ScalarHandler(),cid);
		///qr.query()返回object类型 ，先转成 ScalarHandler的Long类型 然后 在转为 int类型
		return num.intValue();
	}

	//findProductsByCidWithPage
	@Override
	public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException {
		String sql = "select*from product where cid=? LIMIT ?,?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), cid,startIndex,pageSize);
	}

	@Override
	public int findTotalRecords() throws SQLException {
		String sql = "SELECT COUNT(*) FROM product";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) queryRunner.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	public List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws SQLException {
		String sql="select * from product limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
	}

	@Override
	public void saveProduct(Product product) throws SQLException {
		String sql="INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
		qr.update(sql,params);
	}

}
