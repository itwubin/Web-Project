package com.lwb.store.service.serviceImp;

import java.sql.SQLException;
import java.util.List;

import com.lwb.store.dao.ProductDao;
import com.lwb.store.dao.daoImp.ProductDaoImp;
import com.lwb.store.domain.PageModel;
import com.lwb.store.domain.Product;
import com.lwb.store.service.ProductService;

public class ProductServiceImp implements ProductService {

	//调用DAO层对象
	ProductDao pd = new ProductDaoImp();
	
	@Override
	public List<Product> findNewProduct() throws SQLException {
		return pd.findNewProduct();
	}

	@Override
	public List<Product> findHotProduct() throws SQLException {
		return pd.findHotProduct();
	}

	//根据pid查询商品信息
	@Override
	public Product findProductByPid(String pid) throws SQLException {
		return pd.findProductByPid(pid);
	}

	//findProductsByCidWithPage
	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws SQLException {
		//创建PageModel对象 目的:计算分页参数
		
		//查询当前分类下的商品个数"select count(*) from product where cid = ?"
		int totalRecords = pd.findTotalRecords(cid);
		PageModel pm = new PageModel(curNum, totalRecords, 12);
		//关联集合"select*from product where cid=? limit ?,?"
		List list = pd.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);  //这一步骤,你忽略了
		//关联url
/*<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductsByCidWithPage&cid=1&num=1">首页</a>*/
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);//分析一下pageFile.jsp
		return pm;
	}

	@Override
	public PageModel findAllProductsWithPage(int curNum) throws SQLException {
		//创建PageModel对象
		int totalRecords = pd.findTotalRecords();
		PageModel pm = new PageModel(curNum, totalRecords, 5);
		//2_关联集合 select * from product limit ? , ?
		List<Product> list = pd.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//url
		pm.setUrl("AdminProductServlet?method=findAllProductsWithPage");
		return pm;
	}

	@Override
	public void saveProduct(Product product) throws SQLException {
		pd.saveProduct(product);
	}

}
