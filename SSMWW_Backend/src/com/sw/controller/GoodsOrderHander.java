package com.sw.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.sw.model.Goods;
import com.sw.model.GoodsOrderVO;
import com.sw.model.GoodsOrdersVO;
import com.sw.model.Goodsorders;
import com.sw.server.GoodsOrderBiz;
import com.sw.util.Page;

@Controller
public class GoodsOrderHander
{
	@Autowired
	GoodsOrderBiz goodsOrderBiz;
	@RequestMapping(value="getToDayMaxOrderCode",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody String getToDayMaxOrderCode(@RequestParam String date)
	{
		JSONObject jsonObject = new JSONObject();
		try
		{
			String todayMaxOrderCode = goodsOrderBiz.getToDayMaxOrderCode(date);
			
			if (todayMaxOrderCode!=null&&todayMaxOrderCode!="")
			{
				jsonObject.put("code", 200);
			}else {
				jsonObject.put("code", 500);
			}
			jsonObject.put("maxordercode", todayMaxOrderCode);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	@RequestMapping(value="goodsOrderCount",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody Integer goodsOrderCount()
	{
		return goodsOrderBiz.goodsOrderCount();
	}
	@RequestMapping(value="goodsOrderALL",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody List<GoodsOrdersVO> goodsOrderALL()
	{
		List<GoodsOrdersVO> temlist=goodsOrderBiz.goodsOrderALL();
		Collections.sort(temlist, new Comparator<GoodsOrdersVO>() {
		       public int compare(GoodsOrdersVO h1, GoodsOrdersVO h2) {
		           return h2.getConfirmdate().compareTo(h1.getConfirmdate());
		       }
		   });
		System.out.println(temlist);
		return temlist;
	}
	@RequestMapping(value="goodsOrderALLByPage",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody List<GoodsOrdersVO> goodsOrderALLByPage(@RequestParam Integer pageNow,@RequestParam Integer pageLimit)
	{
		return goodsOrderBiz.goodsOrderALLByPage(new Page(pageNow,pageLimit));
	}
	@RequestMapping(value="addGoodsOrder",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody String addGoodsOrder(@RequestBody GoodsOrderVO jsonparam)
	{
		System.out.println(jsonparam);
		
		
		int code=goodsOrderBiz.addGoodsOrderAndDetails(jsonparam);//该方法暂时未进行事务处理，可能存在风险
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
	@RequestMapping(value="goodsOrderALLByDate",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody List<GoodsOrdersVO> goodsOrderALLByDate(@RequestParam String startDate,@RequestParam String endDate)
	{
		return goodsOrderBiz.goodsOrderALLByDate(startDate, endDate);
	}
	@RequestMapping(value="goodsOrderALLBySearch",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody List<GoodsOrdersVO> goodsOrderALLBySearch(@RequestParam String searchText)
	{
		return goodsOrderBiz.goodsOrderALLBySearch(searchText);
	}
	@RequestMapping(value="goodsOrderALLBySearchAndDate",method={RequestMethod.GET,RequestMethod.POST}) 
	public @ResponseBody List<GoodsOrdersVO> goodsOrderALLBySearchAndDate(@RequestParam String searchText,@RequestParam String startDate,@RequestParam String endDate)
	{
		return goodsOrderBiz.goodsOrderALLBySearchAndDate(searchText,startDate,endDate);
	}

}