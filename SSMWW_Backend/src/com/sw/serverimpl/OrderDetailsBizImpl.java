package com.sw.serverimpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw.dao.AddressMapper;
import com.sw.dao.GoodsDao;
import com.sw.dao.GoodsMapper;
import com.sw.dao.GoodsordersMapper;
import com.sw.dao.OrderdetailsMapper;
import com.sw.dao.SaleMapper;
import com.sw.model.Address;
import com.sw.model.Goods;
import com.sw.model.GoodsOrdersVO;
import com.sw.model.Goodsorders;
import com.sw.model.GoodsordersExample;
import com.sw.model.OrderDetailsVO;
import com.sw.model.Orderdetails;
import com.sw.model.OrderdetailsExample;
import com.sw.model.PrintVo;
import com.sw.model.PrintZO;
import com.sw.model.Sale;
import com.sw.server.OrderDetailsBiz;
@Service
public class OrderDetailsBizImpl implements OrderDetailsBiz
{
	@Autowired
	OrderdetailsMapper orderdetailsdao;
	@Autowired
	GoodsordersMapper goodsordersdao;
	@Autowired
	GoodsDao goodsdao;
	@Autowired
	SaleMapper saledao;
	@Autowired
	AddressMapper addressdao;
	@Override
	public int orderdetailsCount()
	{
		return (int) orderdetailsdao.countByExample(new OrderdetailsExample());
	}

	@Override
	public int addOrderdetails(Orderdetails orderdetails)
	{
		Integer maxid=orderdetailsdao.orderdetailsMaxId();
		if (maxid==null) orderdetails.setOrderdetailsid(1);
		else orderdetails.setOrderdetailsid(maxid<1?1:maxid+1);
		return orderdetailsdao.insert(orderdetails);
	}

	@Override
	public int deleteOrderdetailsById(Integer orderdetailsid)
	{
		return orderdetailsdao.deleteByPrimaryKey(orderdetailsid);
	}

	@Override
	public int updateOrderdetailsById(Orderdetails orderdetails)
	{
		return orderdetailsdao.updateByPrimaryKey(orderdetails);
	}

	@Override
	public List<OrderDetailsVO> orderDetailsByOrderCode(String ordercode)
	{
		OrderdetailsExample example = new OrderdetailsExample();
		example.createCriteria().andOrdervodeEqualTo(ordercode);
		List<Orderdetails> ods =orderdetailsdao.selectByExample(example);
		
		List<OrderDetailsVO> odvos=new ArrayList<OrderDetailsVO>();
		for (Orderdetails od : ods)
		{
			Integer goodsid=od.getGoodsid();
			Goods goods = goodsdao.selectByPrimaryKey(goodsid);
			OrderDetailsVO odvo=new OrderDetailsVO();
			odvo.setGoodsid(goodsid);
			odvo.setGoodsname(goods.getGoodsname());
			odvo.setNumber(od.getNumber());
			odvo.setPrice(goods.getPrice().doubleValue());
			odvos.add(odvo);
		}
		System.out.println(ods);
		return odvos;
	}

	@Override
	public int deleteOrderdetailsByOrderCode(String ordercode)
	{
		OrderdetailsExample example = new OrderdetailsExample();
		example.createCriteria().andOrdervodeEqualTo(ordercode);
		
		List<Orderdetails> ots=orderdetailsdao.selectByExample(example);
		for (Orderdetails ods : ots)
		{
			int delODCode=orderdetailsdao.deleteByPrimaryKey(ods.getOrderdetailsid());
			System.out.println("delODCode【【【【【【【【【【【【"+delODCode);
		}
		
		GoodsordersExample example1= new GoodsordersExample();
		example1.createCriteria().andOrdercodeEqualTo(ordercode);
		int resint = goodsordersdao.deleteByExample(example1);
		System.out.println("resint【【【【【【【【【【【【"+resint);
		return resint;
	}

	@Override
	public List<PrintVo> getPrintOrders(String[] ordercodes)
	{
		List<PrintVo> printVos=new ArrayList<PrintVo>();
		for (String string : ordercodes)
		{
			PrintVo printVo = new PrintVo();
			GoodsordersExample example = new GoodsordersExample();
			example.createCriteria().andOrdercodeEqualTo(string);
			List<Goodsorders> gos=goodsordersdao.selectByExample(example);//查询  商品订单表
			Goodsorders goodsorders=gos.get(0);	//获取商品订单 
			Sale sale=saledao.selectByPrimaryKey(goodsorders.getSaleid());
			Address address=addressdao.selectByPrimaryKey(goodsorders.getAddressid());
			OrderdetailsExample example2 = new OrderdetailsExample();
			example2.createCriteria().andOrdervodeEqualTo(string);
			List<Orderdetails> orderdetails = orderdetailsdao.selectByExample(example2);//获取订单详情表 
			List<PrintZO> detail = new ArrayList<PrintZO>();
			
			int sum=0;
			for (Orderdetails od : orderdetails)
			{
				PrintZO printZO = new PrintZO();
				Goods goods = goodsdao.selectByPrimaryKey(od.getGoodsid());
				printZO.setGoodsname(goods.getGoodsname());
				printZO.setNumber(od.getNumber());
				sum+=od.getNumber();
				printZO.setPrice(goods.getPrice().doubleValue());
				detail.add(printZO);
			}
			printVo.setAddressid(goodsorders.getAddressid());
			printVo.setSaleid(sale.getSaleid());
			
			printVo.setAddress(address.getAddress());;
			printVo.setReceivename(address.getReceivename());
			printVo.setReceivephone(address.getReceivephone());
			printVo.setSalename(sale.getSalename());
			printVo.setDetail(detail);
			printVo.setConfirmdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goodsorders.getConfirmdate()));
			
			printVo.setOrdercode(string);
			printVo.setCountprice(goodsorders.getCountprice().doubleValue());
			printVo.setCount(sum);
			printVos.add(printVo);
		}
		return printVos;
	}
}
