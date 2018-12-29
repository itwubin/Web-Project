package com.lwb.store.service;

import java.sql.SQLException;
import java.util.List;

import com.lwb.store.domain.Order;
import com.lwb.store.domain.PageModel;
import com.lwb.store.domain.User;

public interface OrderService {

	void saveOrder(Order order) throws SQLException;

	PageModel findMyOrdersWithPage(User user, int curNum) throws Exception;

	Order findOrderByOid(String oid) throws Exception;

	void updateOrder(Order order) throws SQLException;

	List<Order> findAllOrders() throws SQLException;

	List<Order> findAllOrders(String st) throws SQLException;

}
