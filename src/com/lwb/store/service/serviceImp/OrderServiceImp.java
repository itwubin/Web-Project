package com.lwb.store.service.serviceImp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.lwb.store.dao.OrderDao;
import com.lwb.store.dao.daoImp.OrderDaoImp;
import com.lwb.store.domain.Order;
import com.lwb.store.domain.OrderItem;
import com.lwb.store.domain.PageModel;
import com.lwb.store.domain.User;
import com.lwb.store.service.OrderService;
import com.lwb.store.utils.JDBCUtils;

public class OrderServiceImp implements OrderService {

	//调用Dao层对象
	OrderDao orderDao = new OrderDaoImp();
	@Override
	public void saveOrder(Order order) throws SQLException {
		Connection conn =null;
		try {
			//保存订单和订单下所有的订单项(同时成功,失败)(事务)
			
			//获取连接
			conn = JDBCUtils.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//保存订单
			orderDao.saveOrder(conn,order);
			//保存订单项
			for (OrderItem item : order.getList()) {
				orderDao.saveOrderItem(conn,item);
			}
			//提交
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//回滚
			conn.rollback();
		}
	}
	@Override
	public List<Order> findAllOrders() throws SQLException {
		return orderDao.findAllOrders();
	}
	@Override
	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
		//1_创建PageModel对象,目的:计算并且携带分页参数
		int totalRecords = orderDao.getTotalRecords(user);
		PageModel pm = new PageModel(curNum, totalRecords, 3);
	    //2_关联集合 select * from orders where uid=? limit ? ,?
		List list = orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}
	
	
	@Override
	public Order findOrderByOid(String oid) throws Exception {
		return orderDao.findOrderByOid(oid);
	}
	
	
	@Override
	public void updateOrder(Order order) throws SQLException {
		String sql="UPDATE orders SET ordertime=? ,total=? ,state= ?, address=?,NAME=?, telephone =? WHERE oid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid()};
		qr.update(sql,params);
	}
	@Override
	public List<Order> findAllOrders(String st) throws SQLException {
		return orderDao.findAllOrders(st);
	}

}
