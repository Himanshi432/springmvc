package com.rest.controller;

import com.rest.dao.Client;
import com.rest.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    ClientManager clientManager;

    @RequestMapping(method = RequestMethod.GET,value = "/client")
    public Client getClient(@RequestParam(value = "client_id",defaultValue = "client test") String client_id){
       return clientManager.getClientDetails(client_id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/client")
    public String addClient(@RequestBody Client requestBody){
        System.out.println("add client method");
        return clientManager.createClient(requestBody);
    }


}
