package com.sw.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sw.model.Sale;
import com.sw.server.SaleBiz;

@Controller
public class SaleHandler
{
	@Autowired
	private SaleBiz saleBiz;

	@RequestMapping(value="login",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody String login(@RequestParam(value="salename")String salename,@RequestParam(value="password")String password) throws JSONException
	{
		System.out.println(salename+","+password);
		Sale sale=saleBiz.findSaleBySaleName(salename);
		System.out.println(sale);
		
		JSONObject jsonObject = new JSONObject();
		if(sale.getSalename().equals(salename)&&sale.getSalepassword().equals(password))
		{
			System.out.println("登录成功");
			jsonObject.put("code", 200);
			jsonObject.put("msg", "登录成功");
			return jsonObject.toString();
		}else {
			System.out.println("登录失败");
			jsonObject.put("code", 303);
			jsonObject.put("msg", "登录失败");
			return jsonObject.toString();
		}
	}
	@RequestMapping(value="checkname",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody boolean checkname(@RequestParam(value="salename")String salename)
	{
		System.out.println(salename);
		Sale sale=saleBiz.findSaleBySaleName(salename);
		System.out.println(sale);
		
		return sale==null?false:true;
	}
	
	@RequestMapping(value="findSaleidBySaleName",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody Integer findSaleidBySaleName(@RequestParam(value="salename")String salename)
	{
		System.out.println(salename+",");
		Sale sale;
		if (salename.equals("admin"))
		{
			sale=new Sale();
			sale.setSaleid(1);
			sale.setNickname("admin");
			sale.setSalename("admin");
			sale.setSalepassword("admin");
		}else sale=saleBiz.findSaleBySaleName(salename);
		System.out.println(sale);
		return sale.getSaleid();
	}
	@RequestMapping(value="saleRegister",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody String saleRegister(@RequestParam String salename,@RequestParam String password) throws JSONException
	{
		System.out.println(salename+","+password);
		Sale sale=new Sale();
		int salemaxid=saleBiz.saleMaxId();
		sale.setSaleid(salemaxid);
		sale.setSalename(salename);
		sale.setNickname("默认名称");
		sale.setSalepassword(password);
		System.out.println(sale);
		int code=saleBiz.register(sale);
		
		System.out.println("code:"+code);
		JSONObject jsonObject = new JSONObject();
		try
		{
			jsonObject.put("code", code>=1?"200":"500");
			jsonObject.put("msg", "注册成功");
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
