package com.sw.model;

import java.util.List;

public class PrintVo
{
	private int addressid;
	private int saleid;
	
	private String receivename;
	private String receivephone;
	private String address;

	private String salename;
	private String confirmdate;
	private Double countprice;
	private int count;
	private String ordercode;
	List<PrintZO>  detail;
	
	public int getAddressid()
	{
		return addressid;
	}
	public void setAddressid(int addressid)
	{
		this.addressid = addressid;
	}
	public int getSaleid()
	{
		return saleid;
	}
	public void setSaleid(int saleid)
	{
		this.saleid = saleid;
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
	public String getSalename()
	{
		return salename;
	}
	public void setSalename(String salename)
	{
		this.salename = salename;
	}
	public String getConfirmdate()
	{
		return confirmdate;
	}
	public void setConfirmdate(String confirmdate)
	{
		this.confirmdate = confirmdate;
	}
	
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public Double getCountprice()
	{
		return countprice;
	}
	public void setCountprice(Double countprice)
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
	public List<PrintZO> getDetail()
	{
		return detail;
	}
	public void setDetail(List<PrintZO> detail)
	{
		this.detail = detail;
	}
	
	@Override
	public String toString()
	{
		return "PrintVo [addressid=" + addressid + ", saleid=" + saleid + ", receivename=" + receivename
				+ ", receivephone=" + receivephone + ", address=" + address + ", salename=" + salename
				+ ", confirmdate=" + confirmdate + ", countprice=" + countprice + ", count=" + count + ", ordercode="
				+ ordercode + ", detail=" + detail + "]";
	}
	
	public PrintVo(int addressid, int saleid, String receivename, String receivephone, String address, String salename,
			String confirmdate, Double countprice, int count, String ordercode, List<PrintZO> detail)
	{
		super();
		this.addressid = addressid;
		this.saleid = saleid;
		this.receivename = receivename;
		this.receivephone = receivephone;
		this.address = address;
		this.salename = salename;
		this.confirmdate = confirmdate;
		this.countprice = countprice;
		this.count = count;
		this.ordercode = ordercode;
		this.detail = detail;
	}
	public PrintVo()
	{
		super();
	}
	
	

}
