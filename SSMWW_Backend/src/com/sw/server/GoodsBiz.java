package com.sw.server;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.sw.model.Goods;
import com.sw.util.Page;

public interface GoodsBiz
{
	List<Goods> goodsALL();//查询所有商品
	List<Goods> goodsALLByPage(Page page);//查询某页商品
	List<Goods> goodsByClass(String gc);//查询所有brand类型的商品
	List<Goods> goodsByClassAndPage(String gc,@Param("page")Page page);//查询某页brand类型的商品
	List<String> goodsClassALL();//获取所有商品分类
	Map<String, Integer> goodsClassALLMap();//获取所有商品分类
	Integer goodsMaxId();//获取当前最大的goodsid
	Integer goodsCount();//获取当前最大的goodsid
	List<Goods> goodsSearch(String searchText);
	int addGoods(Goods goods);
	int deleteGoodsById(Integer goodsid);
	int updateGoodsById(Goods goods);
}
