package com.sw.model;

public class OrderDetailsVO
{
	private int goodsid;
	private String goodsname;
	private int index;
	private int number;
	private Double price;
	public int getGoodsid()
	{
		return goodsid;
	}
	public void setGoodsid(int goodsid)
	{
		this.goodsid = goodsid;
	}
	public String getGoodsname()
	{
		return goodsname;
	}
	public void setGoodsname(String goodsname)
	{
		this.goodsname = goodsname;
	}
	public int getIndex()
	{
		return index;
	}
	public void setIndex(int index)
	{
		this.index = index;
	}
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
	public Double getPrice()
	{
		return price;
	}
	public void setPrice(Double price)
	{
		this.price = price;
	}
	public OrderDetailsVO(int goodsid, String goodsname, int index, int number, Double price)
	{
		super();
		this.goodsid = goodsid;
		this.goodsname = goodsname;
		this.index = index;
		this.number = number;
		this.price = price;
	}
	public OrderDetailsVO()
	{
		super();
	}
	@Override
	public String toString()
	{
		return "OrderDetailsVO [goodsid=" + goodsid + ", goodsname=" + goodsname + ", index=" + index + ", number="
				+ number + ", price=" + price + "]";
	}
	
}
