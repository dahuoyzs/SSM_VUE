package com.sw.model;

public class PrintZO
{
	private String goodsname;
	private Integer number;
	private Double price;
	public String getGoodsname()
	{
		return goodsname;
	}
	public void setGoodsname(String goodsname)
	{
		this.goodsname = goodsname;
	}
	public Integer getNumber()
	{
		return number;
	}
	public void setNumber(Integer number)
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
	@Override
	public String toString()
	{
		return "PrintZO [goodsname=" + goodsname + ", number=" + number + ", price=" + price + "]";
	}
	public PrintZO(String goodsname, Integer number, Double price)
	{
		super();
		this.goodsname = goodsname;
		this.number = number;
		this.price = price;
	}
	public PrintZO()
	{
		super();
	}
	
}
