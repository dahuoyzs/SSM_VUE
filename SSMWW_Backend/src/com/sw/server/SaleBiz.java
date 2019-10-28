package com.sw.server;

import com.sw.model.Sale;
public interface SaleBiz
{
	Sale findSaleById(Integer id);    		//根据主键查找Sale         
	Sale findSaleBySaleName(String salename);//根据salename(账号)查找Sale
	int register(Sale sale);
	int saleMaxId();
	
	int saleCount();
	int addSale(Sale sale);
	int deleteSaleById(Integer saleid);
	int updateSaleById(Sale sale);
}
