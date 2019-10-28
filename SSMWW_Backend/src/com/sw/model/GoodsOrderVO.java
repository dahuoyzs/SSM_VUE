package com.sw.model;

import java.util.List;

public class GoodsOrderVO
{
	private int addressid;
	private int saleid;
	private String confirmdate;
	private double countprice;
	private String ordercode;
	private List<OrderDetailsVO> orderdetails;
	
	
	public int getSaleid()
	{
		return saleid;
	}
	public void setSaleid(int saleid)
	{
		this.saleid = saleid;
	}
	public int getAddressid()
	{
		return addressid;
	}
	public void setAddressid(int addressid)
	{
		this.addressid = addressid;
	}
	public String getConfirmdate()
	{
		return confirmdate;
	}
	public void setConfirmdate(String confirmdate)
	{
		this.confirmdate = confirmdate;
	}
	public double getCountprice()
	{
		return countprice;
	}
	public void setCountprice(double countprice)
	{
		this.countprice = countprice;
	}
	public String getOrdercode()
	{
		return ordercode;
	}
	public void setOrdercode(String ordercode)
	{
		this.ordercode = ordercode;
	}
	public List<OrderDetailsVO> getOrderdetails()
	{
		return orderdetails;
	}
	public void setOrderdetails(List<OrderDetailsVO> orderdetails)
	{
		this.orderdetails = orderdetails;
	}

	public GoodsOrderVO()
	{
		super();
	}
	public GoodsOrderVO(int addressid, String confirmdate, double countprice, String ordercode,
			List<OrderDetailsVO> orderdetails, int saleid)
	{
		super();
		this.addressid = addressid;
		this.confirmdate = confirmdate;
		this.countprice = countprice;
		this.ordercode = ordercode;
		this.orderdetails = orderdetails;
		this.saleid = saleid;
	}
	@Override
	public String toString()
	{
		return "GoodsOrderVO [addressid=" + addressid + ", confirmdate=" + confirmdate + ", countprice=" + countprice
				+ ", ordercode=" + ordercode + ", orderdetails=" + orderdetails + ", saleid=" + saleid + "]";
	}

	
	
}
