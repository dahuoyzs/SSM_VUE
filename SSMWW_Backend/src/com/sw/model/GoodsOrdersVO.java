package com.sw.model;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsOrdersVO
{
	private String salename;
	private String receivename;
	private String receivephone;
	private String address;
	
	private String ordercode;
	private String confirmdate;
	private BigDecimal countprice;
	public String getSalename()
	{
		return salename;
	}
	public void setSalename(String salename)
	{
		this.salename = salename;
	}
	public String getReceivename()
	{
		return receivename;
	}
	public void setReceivename(String receivename)
	{
		this.receivename = receivename;
	}
	public String getReceivephone()
	{
		return receivephone;
	}
	public void setReceivephone(String receivephone)
	{
		this.receivephone = receivephone;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getOrdercode()
	{
		return ordercode;
	}
	public void setOrdercode(String ordercode)
	{
		this.ordercode = ordercode;
	}

	public BigDecimal getCountprice()
	{
		return countprice;
	}
	public void setCountprice(BigDecimal countprice)
	{
		this.countprice = countprice;
	}

	public String getConfirmdate()
	{
		return confirmdate;
	}
	public void setConfirmdate(String confirmdate)
	{
		this.confirmdate = confirmdate;
	}
	public GoodsOrdersVO(String salename, String receivename, String receivephone, String address, String ordercode,
			String confirmdate, BigDecimal countprice)
	{
		super();
		this.salename = salename;
		this.receivename = receivename;
		this.receivephone = receivephone;
		this.address = address;
		this.ordercode = ordercode;
		this.confirmdate = confirmdate;
		this.countprice = countprice;
	}
	@Override
	public String toString()
	{
		return "GoodsOrdersVO [salename=" + salename + ", receivename=" + receivename + ", receivephone=" + receivephone
				+ ", address=" + address + ", ordercode=" + ordercode + ", confirmdate=" + confirmdate + ", countprice="
				+ countprice + "]";
	}
	public GoodsOrdersVO()
	{
		super();
	}
	
	

}
