package com.sw.server;


import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import com.sw.model.GoodsOrderVO;
import com.sw.model.GoodsOrdersVO;
import com.sw.model.Goodsorders;
import com.sw.util.Page;

public interface GoodsOrderBiz
{

	List<GoodsOrdersVO> goodsOrderALL();
	List<GoodsOrdersVO> goodsOrderALLByPage(Page page);//查询某页商品
	
	List<GoodsOrdersVO> goodsOrderALLByDate(String  startDate,String endDate);
	List<GoodsOrdersVO> goodsOrderALLBySearch(String search);
	List<GoodsOrdersVO> goodsOrderALLBySearchAndDate(String searchText,String startDate,String endDate);
	
	String getToDayMaxOrderCode(String date);
	int goodsOrderCount();
	
	int addGoodsOrder(Goodsorders goodsorders);
	int deleteGoodsOrderById(Integer goodsordersid);
	int updateGoodsOrderById(Goodsorders goodsorders);
	
	int addGoodsOrderAndDetails(GoodsOrderVO goodsOrderVO);
}
