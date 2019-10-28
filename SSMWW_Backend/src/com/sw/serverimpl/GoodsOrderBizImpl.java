package com.sw.serverimpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw.dao.AddressMapper;
import com.sw.dao.GoodsordersMapper;
import com.sw.dao.OrderdetailsMapper;
import com.sw.dao.SaleMapper;
import com.sw.model.Address;
import com.sw.model.GoodsOrderVO;
import com.sw.model.GoodsOrdersVO;
import com.sw.model.Goodsorders;
import com.sw.model.GoodsordersExample;
import com.sw.model.OrderDetailsVO;
import com.sw.model.Orderdetails;
import com.sw.model.Sale;
import com.sw.server.GoodsOrderBiz;
import com.sw.util.Page;
@Service
public class GoodsOrderBizImpl implements GoodsOrderBiz
{
	@Autowired
	GoodsordersMapper goodsOrderdao;
	@Autowired
	OrderdetailsMapper orderdetailsdao;
	@Autowired
	SaleMapper saledao;
	@Autowired
	AddressMapper addressdao;
	
	
	
	@Override
	public String getToDayMaxOrderCode(String date)
	{
		return goodsOrderdao.getToDayMaxOrderCode(date);
	}
	@Override
	public int goodsOrderCount()
	{
		return (int) goodsOrderdao.countByExample(new GoodsordersExample());
	}
	@Override
	public int addGoodsOrder(Goodsorders goodsorders)
	{
		Integer maxid=goodsOrderdao.goodsordersMaxId();
		if (maxid==null) goodsorders.setOrderid(1);
		else goodsorders.setOrderid(maxid<1?1:maxid+1);
		return goodsOrderdao.insert(goodsorders);
	}
	@Override
	public int deleteGoodsOrderById(Integer goodsordersid)
	{
		return goodsOrderdao.deleteByPrimaryKey(goodsordersid);
	}
	@Override
	public int updateGoodsOrderById(Goodsorders goodsorders)
	{
		return goodsOrderdao.updateByPrimaryKey(goodsorders);
	}
	@Override
	public int addGoodsOrderAndDetails(GoodsOrderVO goodsOrderVO)
	{
		try {
			Goodsorders goodsorders = new Goodsorders();
			goodsorders.setAddressid(goodsOrderVO.getAddressid());
			System.out.println("----------------------------------------");
			goodsorders.setSaleid(goodsOrderVO.getSaleid());
			
			Date date=new Date();
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(goodsOrderVO.getConfirmdate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			goodsorders.setConfirmdate(date);
			goodsorders.setCountprice(new BigDecimal(goodsOrderVO.getCountprice()));
			goodsorders.setOrdercode(goodsOrderVO.getOrdercode());
			System.out.println(goodsOrderVO.getSaleid());
			System.out.println(goodsorders.getSaleid());
			System.out.println("----------------------------------------");
			goodsOrderdao.insert(goodsorders);
			
			List<OrderDetailsVO> orderDetailsVOs = goodsOrderVO.getOrderdetails();
			for (OrderDetailsVO orderDetailsVO : orderDetailsVOs)
			{
				Orderdetails orderdetails = new Orderdetails();
				orderdetails.setGoodsid(orderDetailsVO.getGoodsid());
				orderdetails.setNumber(orderDetailsVO.getNumber());
				orderdetails.setOrdervode(goodsOrderVO.getOrdercode());
				orderdetailsdao.insert(orderdetails);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	@Override
	public List<GoodsOrdersVO> goodsOrderALLByPage(Page page)
	{
		List<Goodsorders> gos=goodsOrderdao.goodsOrderALLByPage(page);
		List<GoodsOrdersVO> govs=new ArrayList<GoodsOrdersVO>();
		for (Goodsorders goodsorders : gos)
		{
			Integer saleid=goodsorders.getSaleid();
			Integer addressid=goodsorders.getAddressid();
			Sale sale = saledao.selectByPrimaryKey(saleid);
			Address address = addressdao.selectByPrimaryKey(addressid);
			GoodsOrdersVO gov=new GoodsOrdersVO();
			gov.setAddress(address.getAddress());
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goodsorders.getConfirmdate());
			gov.setConfirmdate(date);
			gov.setCountprice(goodsorders.getCountprice());
			gov.setOrdercode(goodsorders.getOrdercode());
			gov.setReceivename(address.getReceivename());
			gov.setReceivephone(address.getReceivephone());
			gov.setSalename(sale.getSalename());
			govs.add(gov);
		}
		
		return govs;
	}
	@Override
	public List<GoodsOrdersVO> goodsOrderALL()
	{
		List<Goodsorders> gos = goodsOrderdao.selectByExample(new GoodsordersExample());
		List<GoodsOrdersVO> govs=new ArrayList<GoodsOrdersVO>();
		for (Goodsorders goodsorders : gos)
		{
			Integer saleid=goodsorders.getSaleid();
			Integer addressid=goodsorders.getAddressid();
			Sale sale = saledao.selectByPrimaryKey(saleid);
			Address address = addressdao.selectByPrimaryKey(addressid);
			GoodsOrdersVO gov=new GoodsOrdersVO();
			gov.setAddress(address==null?"此地址已删除":address.getAddress());
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goodsorders.getConfirmdate());
			gov.setConfirmdate(date);
			gov.setCountprice(goodsorders.getCountprice());
			gov.setOrdercode(goodsorders.getOrdercode());
			gov.setReceivename(address==null?"此地址已删除":address.getReceivename());
			gov.setReceivephone(address==null?"此地址已删除":address.getReceivephone());
			gov.setSalename(sale.getSalename());
			govs.add(gov);
		}
		return govs;
	}
//	List<GoodsOrdersVO> goodsOrderALLByDate(String  startDate,String endDate);
//	List<GoodsOrdersVO> goodsOrderALLBySearch(String search);
//	List<GoodsOrdersVO> goodsOrderALLBySearchAndDate(String searchText,String startDate,String endDate);

	@Override
	public List<GoodsOrdersVO> goodsOrderALLByDate(String startDate, String endDate)
	{
		List<Goodsorders> gos = goodsOrderdao.goodsOrderALLByDate(startDate, endDate);
		
		List<GoodsOrdersVO> govs=new ArrayList<GoodsOrdersVO>();
		for (Goodsorders goodsorders : gos)
		{
			System.out.println(goodsorders);
			Integer saleid=goodsorders.getSaleid();
			Integer addressid=goodsorders.getAddressid();
			Sale sale = saledao.selectByPrimaryKey(saleid);
			Address address = addressdao.selectByPrimaryKey(addressid);
			GoodsOrdersVO gov=new GoodsOrdersVO();
			gov.setAddress(address.getAddress());
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goodsorders.getConfirmdate());
			gov.setConfirmdate(date);
			gov.setCountprice(goodsorders.getCountprice());
			gov.setOrdercode(goodsorders.getOrdercode());
			gov.setReceivename(address.getReceivename());
			gov.setReceivephone(address.getReceivephone());
			gov.setSalename(sale.getSalename());
			govs.add(gov);
		}
		return govs;
	}
	@Override
	public List<GoodsOrdersVO> goodsOrderALLBySearch(String searchText)
	{
		List<Goodsorders> gos = goodsOrderdao.selectByExample(new GoodsordersExample());
		List<GoodsOrdersVO> govs=new ArrayList<GoodsOrdersVO>();
		for (Goodsorders goodsorders : gos)
		{
			Integer saleid=goodsorders.getSaleid();
			Integer addressid=goodsorders.getAddressid();
			Sale sale = saledao.selectByPrimaryKey(saleid);
			Address address = addressdao.selectByPrimaryKey(addressid);
			GoodsOrdersVO gov=new GoodsOrdersVO();
			gov.setAddress(address.getAddress());
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goodsorders.getConfirmdate());
			gov.setConfirmdate(date);
			gov.setCountprice(goodsorders.getCountprice());
			gov.setOrdercode(goodsorders.getOrdercode());
			gov.setReceivename(address.getReceivename());
			gov.setReceivephone(address.getReceivephone());
			gov.setSalename(sale.getSalename());
			if (gov.getReceivename().contains(searchText)||gov.getReceivephone().contains(searchText))
			{
				govs.add(gov);
			}
		}
		return govs;
	}
	@Override
	public List<GoodsOrdersVO> goodsOrderALLBySearchAndDate(String searchText, String startDate, String endDate)
	{
		List<Goodsorders> gos = goodsOrderdao.goodsOrderALLByDate(startDate, endDate);
		
		List<GoodsOrdersVO> govs=new ArrayList<GoodsOrdersVO>();
		for (Goodsorders goodsorders : gos)
		{
			System.out.println(goodsorders);
			Integer saleid=goodsorders.getSaleid();
			Integer addressid=goodsorders.getAddressid();
			Sale sale = saledao.selectByPrimaryKey(saleid);
			Address address = addressdao.selectByPrimaryKey(addressid);
			GoodsOrdersVO gov=new GoodsOrdersVO();
			gov.setAddress(address.getAddress());
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goodsorders.getConfirmdate());
			gov.setConfirmdate(date);
			gov.setCountprice(goodsorders.getCountprice());
			gov.setOrdercode(goodsorders.getOrdercode());
			gov.setReceivename(address.getReceivename());
			gov.setReceivephone(address.getReceivephone());
			gov.setSalename(sale.getSalename());
			if (gov.getReceivename().contains(searchText)||gov.getReceivephone().contains(searchText))
			{
				govs.add(gov);
			}
		}
		return govs;
	}
}
