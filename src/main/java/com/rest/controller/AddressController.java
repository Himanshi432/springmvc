package com.rest.controller;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.rest.dao.Address;
import com.rest.exceptions.AddressException;
import com.rest.manager.AddressManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    @Autowired
    AddressManager addressManager;

    @RequestMapping(method = RequestMethod.GET,value= "/address")
    public ResponseEntity getAddress(@RequestParam(value = "address_id",defaultValue ="NOIDA77") String address_id) throws AddressException {
        ResponseEntity response = null;
        try {
            response = addressManager.getAddressDetails(address_id);
        }
            catch (AddressException e) {
                return ResponseEntity.status(400).body(e.getMessage());
            }
            catch (Exception e) {
                return ResponseEntity.status(500).body(e.getMessage());
            }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST,value= "/address")
    public String addNewAddress(@RequestBody Address address){
        return addressManager.addNewAddress(address);
    }

    @RequestMapping(method = RequestMethod.PUT,value= "/address")
    public UpdateResult updateAddress(@RequestParam(value = "address_id",defaultValue ="NOIDA") String address_id,
                                      @RequestBody Address address){
        return addressManager.updateAddress(address_id,address);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/address")
    public DeleteResult deleteAddress(@RequestParam(value = "address_id",defaultValue ="NOIDA") String address_id){
        return addressManager.deleteAddress(address_id);
    }
}
