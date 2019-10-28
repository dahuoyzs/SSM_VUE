package com.sw.serverimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw.dao.AddressMapper;
import com.sw.model.Address;
import com.sw.model.AddressExample;
import com.sw.server.AddressBiz;
import com.sw.util.Page;
@Service
public class AddressBizImpl implements AddressBiz
{
	@Autowired
	AddressMapper addressdao;

	@Override
	public List<Address> addressALL()
	{
		return addressdao.addressALL();
	}

	@Override
	public List<Address> addressALLByPage(Page page)
	{
		return addressdao.addressALLByPage(page);
	}

	@Override
	public int addressCount()
	{
		return (int) addressdao.countByExample(new AddressExample());
	}

	@Override
	public int deleteAddressById(Integer addressid)
	{
		return addressdao.deleteByPrimaryKey(addressid);
	}

	@Override
	public int updateAddress(Address address)
	{
		return addressdao.updateByPrimaryKey(address);
	}

	@Override
	public int addAddress(Address address)
	{
		Integer maxid=addressdao.addressMaxId();
		if (maxid==null) address.setAddressid(1);
		else address.setAddressid(maxid<1?1:maxid+1);
		return addressdao.insert(address);
	}

	@Override
	public int addressMaxId()
	{
		return addressdao.addressMaxId();
	}
	
}
