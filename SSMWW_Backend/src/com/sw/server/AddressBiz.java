package com.sw.server;

import java.util.List;
import com.sw.model.Address;
import com.sw.util.Page;

public interface AddressBiz
{
	List<Address> addressALL();
	List<Address> addressALLByPage(Page page);
	int addressCount();
	int addressMaxId();
	int deleteAddressById(Integer addressid);
	int updateAddress(Address address);
	int addAddress(Address address);
}
