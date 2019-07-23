package com.rest.controller;

import com.rest.dao.Address;
import com.rest.manager.AddressManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    AddressManager addressManager;

    @RequestMapping(method = RequestMethod.GET,value= "/address")
    public Address getAddress(@RequestParam(value = "address_id",defaultValue ="NOIDA") String address_id){
        return addressManager.getAddressDetails(address_id);
    }
}
