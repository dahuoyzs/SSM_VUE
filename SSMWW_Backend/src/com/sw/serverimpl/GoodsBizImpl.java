package com.sw.serverimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw.dao.GoodsDao;
import com.sw.model.Goods;
import com.sw.model.GoodsExample;
import com.sw.server.GoodsBiz;
import com.sw.util.Page;
@Service
public class GoodsBizImpl implements GoodsBiz
{
	@Autowired
	private GoodsDao goodsdao;

	@Override
	public List<Goods> goodsALL()
	{
		return goodsdao.goodsALL();
	}

	@Override
	public List<Goods> goodsALLByPage(Page page)
	{
		return goodsdao.goodsALLByPage(page);
	}

	@Override
	public List<Goods> goodsByClass(String gc)
	{
		return goodsdao.goodsByClass(gc);
	}

	@Override
	public List<Goods> goodsByClassAndPage(String gc, Page page)
	{
		return goodsdao.goodsByClassAndPage(gc, page);
	}

	@Override
	public List<String> goodsClassALL()
	{
		return goodsdao.goodsClassALL();
	}
	
	@Override
	public Map<String, Integer> goodsClassALLMap()
	{

		Map<String, Integer> map = new HashMap<String, Integer>();
		List<String> goodsclasss=goodsdao.goodsClassALL();
		for (String string : goodsclasss)
		{
			int count=goodsdao.goodsClassCount(string);
			map.put(string, count);
		}
		return map;
	}

	@Override
	public Integer goodsMaxId()
	{
		return goodsdao.goodsMaxId();
	}

	@Override
	public Integer goodsCount()
	{
		return (int)goodsdao.countByExample(new GoodsExample());
	}

	@Override
	public List<Goods> goodsSearch(String searchText)
	{
		GoodsExample example = new GoodsExample();
		example.createCriteria().andGoodsnameLike("%"+searchText+"%");
		return goodsdao.selectByExample(example);
	}

	@Override
	public int addGoods(Goods goods)
	{
		Integer maxid=goodsdao.goodsMaxId();
		if (maxid==null)goods.setGoodsid(1001);
		else goods.setGoodsid(maxid<1?1:maxid+1);
		return goodsdao.insert(goods);
	}

	@Override
	public int deleteGoodsById(Integer goodsid)
	{
		return goodsdao.deleteByPrimaryKey(goodsid);
	}

	@Override
	public int updateGoodsById(Goods goods)
	{
		return goodsdao.updateByPrimaryKey(goods);
	}

	
}
