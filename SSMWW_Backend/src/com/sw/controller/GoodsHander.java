package com.sw.controller;

import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sun.org.apache.bcel.internal.generic.GOTO_W;
import com.sw.model.Goods;
import com.sw.server.GoodsBiz;
import com.sw.util.Page;

@Controller
public class GoodsHander
{
	@Autowired
	private GoodsBiz goodsbiz;
	@RequestMapping(value="goodsALL",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody List<Goods> goodsALL()
	{
		return goodsbiz.goodsALL();
	}
	@RequestMapping(value="goodsALLByPage",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody List<Goods> goodsALLByPage(@RequestParam Integer pageNow,@RequestParam Integer pageLimit)
	{
		return goodsbiz.goodsALLByPage(new Page(pageNow,pageLimit));
	}
	@RequestMapping(value="goodsByClass",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody List<Goods> goodsByClass(@RequestParam String gc)
	{
		return goodsbiz.goodsByClass(gc);
	}
	@RequestMapping(value="goodsByClassAndPage",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody List<Goods> goodsByClassAndPage(@RequestParam String goodsclass,@RequestParam Integer pageNow,@RequestParam Integer pageLimit)
	{
		return goodsbiz.goodsByClassAndPage(goodsclass, new Page(pageNow,pageLimit));
	}
	@RequestMapping(value="goodsClassALL",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody List<String> goodsClassALL()
	{
		return goodsbiz.goodsClassALL();
	}
	@RequestMapping(value="goodsClassALLMap",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody Map<String, Integer> goodsClassALLMap()
	{
		return goodsbiz.goodsClassALLMap();
	}
	
	@RequestMapping(value="goodsMaxId",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody Integer goodsMaxId()
	{
		return goodsbiz.goodsMaxId();
	}
	@RequestMapping(value="goodsCount",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody Integer goodsCount()
	{
		return goodsbiz.goodsCount();
	}
	@RequestMapping(value="goodsSearch",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody List<Goods> goodsSearch(@RequestParam String searchText)
	{
		return goodsbiz.goodsSearch(searchText);
	}
	@RequestMapping(value="addGoods",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody String addGoods(@RequestBody Goods jsonparam)
	{
		System.out.println(jsonparam);
		
		
		int code=goodsbiz.addGoods(jsonparam);
		System.out.println("code:"+code);
		JSONObject jsonObject = new JSONObject();
		try
		{
			jsonObject.put("code", code>=1?"200":"500");
			jsonObject.put("msg", "添加成功");
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	@RequestMapping(value="deleteGoodsById",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody String deleteGoodsById(@RequestBody Goods goods)
	{
		System.out.println(goods.getGoodsid());
		int code=goodsbiz.deleteGoodsById(goods.getGoodsid());
		System.out.println("code:"+code);
		JSONObject jsonObject = new JSONObject();
		try
		{
			jsonObject.put("code", code>=1?"200":"500");
			jsonObject.put("msg", "删除成功");
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	@RequestMapping(value="updateGoods",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody String updateGoodsById(@RequestBody Goods goods)
	{
		System.out.println(goods.getGoodsid());
		int code=goodsbiz.updateGoodsById(goods);
		System.out.println("code:"+code);
		JSONObject jsonObject = new JSONObject();
		try
		{
			jsonObject.put("code", code>=1?"200":"500");
			jsonObject.put("msg", "更新成功");
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
