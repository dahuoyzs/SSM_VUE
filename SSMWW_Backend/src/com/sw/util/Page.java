package com.sw.util;

public class Page
{
	private int pageNow;//当前页码		外部传入的
	private int pageLimit;//每页显示的个数  外部传入的
	private int mysqlLimitParam1;
	public Page()
	{
		super();
	}
	public Page(int pageNow, int pageLimit)
	{
		super();
		this.pageNow = pageNow;
		this.pageLimit = pageLimit;
		this.mysqlLimitParam1 = pageNow<=1?0:pageLimit*(pageNow-1);
	}
	public int getPageNow()
	{
		return pageNow;
	}

	public void setPageNow(int pageNow)
	{
		this.pageNow = pageNow;
	}

	public int getPageLimit()
	{
		return pageLimit;
	}

	public void setPageLimit(int pageLimit)
	{
		this.pageLimit = pageLimit;
	}

	public int getMysqlLimitParam1()
	{
		return mysqlLimitParam1;
	}

	public void setMysqlLimitParam1(int mysqlLimitParam1)
	{
		this.mysqlLimitParam1 = mysqlLimitParam1;
	}

	@Override
	public String toString()
	{
		return "Page [pageNow=" + pageNow + ", pageLimit=" + pageLimit + ", mysqlLimitParam1=" + mysqlLimitParam1 + "]";
	}


	
	
	
}
