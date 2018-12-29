package com.lwb.store.service;

import java.sql.SQLException;
import java.util.List;

import com.lwb.store.domain.PageModel;
import com.lwb.store.domain.Product;


public interface ProductService {
	//查询最新商品
	List<Product> findNewProduct() throws SQLException;
	//查询最热商品
	List<Product> findHotProduct() throws SQLException;
	//根据pid查询商品信息
	Product findProductByPid(String pid) throws SQLException;
	//以分页形式查询数据库商品信息
	PageModel findProductsByCidWithPage(String cid, int curNum) throws SQLException;
	//
	PageModel findAllProductsWithPage(int curNum) throws SQLException;
	//
	void saveProduct(Product product) throws SQLException;

}
