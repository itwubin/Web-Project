package com.lwb.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.lwb.store.domain.Product;


public interface ProductDao {
	//查询最新商品
	List<Product> findNewProduct() throws SQLException;
	//查询最热商品
	List<Product> findHotProduct() throws SQLException;
	//根据pid查询商品信息
	Product findProductByPid(String pid) throws SQLException;
	//查询当前分类商品下的个数
	int findTotalRecords(String cid) throws SQLException;
	//查询集合信息
	List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException;
	
	int findTotalRecords() throws SQLException;
	
	List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws SQLException;
	//
	void saveProduct(Product product) throws SQLException;

}
