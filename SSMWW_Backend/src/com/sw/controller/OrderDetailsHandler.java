package com.sw.controller;

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
import com.sw.dao.OrderdetailsMapper;
import com.sw.model.OrderDetailsVO;
import com.sw.model.PrintVo;
import com.sw.server.OrderDetailsBiz;


@Controller
public class OrderDetailsHandler
{
	@Autowired
	OrderDetailsBiz orderDetailsBiz;
	@RequestMapping(value="orderDetailsByOrderCode",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody List<OrderDetailsVO> orderDetailsByOrderCode(@RequestParam String ordercode)
	{
		
		return orderDetailsBiz.orderDetailsByOrderCode(ordercode);
	}
	@RequestMapping(value="deleteOrderDetailsByOrderCode",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody String deleteOrderDetailsByOrderCode(@RequestParam String ordercode)
	{
		int code=orderDetailsBiz.deleteOrderdetailsByOrderCode(ordercode);
		System.out.println("code【【【【【【【【【【【【:"+code);
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
	@RequestMapping(value="getPrintOrders",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody List<PrintVo> getPrintOrders(@RequestBody String[] params)
	{
		for (String string : params)
		{
			System.out.println(string);
		}
		List<PrintVo> tems=orderDetailsBiz.getPrintOrders(params);
		System.out.println(tems);
		return tems;
	}
}