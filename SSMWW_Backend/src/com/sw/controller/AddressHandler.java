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
import com.sw.model.Address;
import com.sw.server.AddressBiz;
import com.sw.util.Page;

@Controller
public class AddressHandler
{
	@Autowired
	private AddressBiz addressBiz;
	@RequestMapping(value="addressALL",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody List<Address> addressAll()
	{
		return addressBiz.addressALL();
	}
	@RequestMapping(value="addressALLByPage",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody List<Address> addressALLByPage(@RequestParam Integer pageNow,@RequestParam Integer pageLimit)
	{
		return addressBiz.addressALLByPage(new Page(pageNow,pageLimit));
	}
	@RequestMapping(value="addressCount",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody int addressCount()
	{
		return addressBiz.addressCount();
	}
	@RequestMapping(value="addressMaxId",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody int addressMaxid()
	{
		return addressBiz.addressMaxId();
	}
	@RequestMapping(value="deleteAddressById",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody String deleteAddressById(@RequestParam Integer addressid)
	{
		int code = addressBiz.deleteAddressById(addressid);
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
	@RequestMapping(value="updateAddress",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody String updateAddress(@RequestBody Address address)
	{
		int code = addressBiz.updateAddress(address);
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
	@RequestMapping(value="addAddress",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody String addAddress(@RequestBody Address address)
	{
		int code = addressBiz.addAddress(address);
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
}