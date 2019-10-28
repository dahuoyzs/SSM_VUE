package com.sw.serverimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw.dao.SaleMapper;
import com.sw.model.Sale;
import com.sw.model.SaleExample;
import com.sw.server.SaleBiz;

@Service
public class SaleBizImpl implements SaleBiz
{
	@Autowired
	SaleMapper saledao;
	@Override
	public Sale findSaleById(Integer id)
	{
		return saledao.selectByPrimaryKey(id);
	}
	@Override
	public Sale findSaleBySaleName(String salename)
	{
		SaleExample example = new SaleExample();
		example.createCriteria().andSalenameEqualTo(salename);
		List<Sale> sales=saledao.selectByExample(example);
		return sales.size()<1?null:sales.get(0);
	}
	@Override
	public int register(Sale sale)
	{
		Integer maxid=saledao.saleMaxId();
		if (maxid==null) sale.setSaleid(1);
		else sale.setSaleid(maxid<1?1:maxid+1);
		return saledao.insert(sale);
	}
	@Override
	public int saleCount()
	{
		return (int) saledao.countByExample(new SaleExample());
	}
	@Override
	public int addSale(Sale sale)
	{
		int maxid=saledao.saleMaxId();
		sale.setSaleid(maxid<1?1:maxid+1);
		return saledao.insert(sale);
	}
	@Override
	public int deleteSaleById(Integer saleid)
	{
		return saledao.deleteByPrimaryKey(saleid);
	}
	@Override
	public int updateSaleById(Sale sale)
	{
		return saledao.updateByPrimaryKey(sale);
	}
	@Override
	public int saleMaxId()
	{
		return saledao.saleMaxId();
	}
}
