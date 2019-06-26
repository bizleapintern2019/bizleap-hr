package com.bizleap.hr.loader.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizleap.commons.domain.entity.Address;
import com.bizleap.commons.domain.exception.ServiceUnavailableException;
import com.bizleap.commons.domain.utils.Printer;
import com.bizleap.hr.loader.AddressSaver;
import com.bizleap.service.AddressService;

@Service
public class AddressSaverImpl implements AddressSaver{
	
	private static Logger logger = Logger.getLogger(DepartmentSaverImpl.class);
    private static Printer printer = new Printer(logger);

    @Autowired
    private AddressService addressService;

    private List<Address> addressList;

    public void savePass1() throws ServiceUnavailableException, IOException {
        printer.line("Saving Address: "+ getAddressList().size());
        for(Address address:getAddressList()) {
        	addressService.saveAddress(address);
        }
        printer.line("Saving Completed");
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList=addressList;
    }

    public List<Address> getAddressList() {
        return this.addressList;
    }
}