package com.sw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UtilHandler
{
	static int number=1;
	@RequestMapping(value="VisitsNumber",method={RequestMethod.GET,RequestMethod.POST}) 
	public  @ResponseBody Integer VisitsNumber()
	{
		return number++;
	}
}
/*
 * 自己的分层开发规则
 * dao层操作数据库查询出来对象
 * Biz层把多个对象进行重组构建新的数据对象和List集合
 * Control层把List进行重组构造出json串最后返回json串
 * 
 * 
 * 
 */