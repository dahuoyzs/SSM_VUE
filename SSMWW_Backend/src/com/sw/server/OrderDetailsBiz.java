package com.sw.server;

import java.util.List;
import com.sw.model.Goodsorders;
import com.sw.model.OrderDetailsVO;
import com.sw.model.Orderdetails;
import com.sw.model.PrintVo;

public interface OrderDetailsBiz
{
	
	List<PrintVo> getPrintOrders(String[] ordercodes);
	List<OrderDetailsVO> orderDetailsByOrderCode(String ordercode);
	int orderdetailsCount();
	int addOrderdetails(Orderdetails orderdetails);
	int deleteOrderdetailsById(Integer orderdetailsid);
	int deleteOrderdetailsByOrderCode(String ordercode);
	int updateOrderdetailsById(Orderdetails orderdetails);
}
