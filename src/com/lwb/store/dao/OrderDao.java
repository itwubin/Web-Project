package com.lwb.store.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.lwb.store.domain.Order;
import com.lwb.store.domain.OrderItem;
import com.lwb.store.domain.User;

public interface OrderDao {

	void saveOrder(Connection conn, Order order) throws SQLException;

	void saveOrderItem(Connection conn, OrderItem item) throws SQLException;

	List findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception;

	int getTotalRecords(User user) throws SQLException;

	Order findOrderByOid(String oid) throws Exception;

	List<Order> findAllOrders() throws SQLException;

	List<Order> findAllOrders(String st) throws SQLException;

	
}
