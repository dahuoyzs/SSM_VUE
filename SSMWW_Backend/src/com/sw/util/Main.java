package com.sw.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sw.dao.SaleMapper;

@Component
public class Main
{
	@Autowired
	private SaleMapper saleMapper;//成员测试变量不会被注入，再说一遍，别纠结这个东西了，因为xml还没解析
	@Test
	public void testFindSale()throws Exception{
		System.out.println(saleMapper);
	}
}
