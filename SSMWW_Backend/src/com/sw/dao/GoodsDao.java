package com.sw.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sw.model.Goods;
import com.sw.util.Page;

public interface GoodsDao extends GoodsMapper
{
	List<Goods> goodsALL();//查询所有商品
	List<Goods> goodsALLByPage(@Param("page")Page page);//查询某页商品
	List<Goods> goodsByClass(@Param("gc")String gc);//查询所有brand类型的商品
	List<Goods> goodsByClassAndPage(@Param("gc")String gc,@Param("page")Page page);//查询某页brand类型的商品
	List<String> goodsClassALL();//获取所有商品分类
	Integer goodsMaxId();//获取当前最大的goodsid
	Integer goodsClassCount(@Param("gc")String gc);//根据goodsClass获取个数
	//List<Goods> goodsSearch(@Param("searchText")String searchText);
}
