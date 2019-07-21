package com.rest.controller;

import com.rest.dao.Client;
import com.rest.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    ClientManager clientManager;

    @RequestMapping(method = RequestMethod.GET,value = "/client")
    public Client getClient(@RequestParam(value = "client_id",defaultValue = "client test") String client_id){
       return clientManager.getClientDetails(client_id);
    }
}
