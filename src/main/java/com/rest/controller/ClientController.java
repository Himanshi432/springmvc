package com.rest.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.rest.dao.Client;
import com.rest.exceptions.DbExceptions;
import com.rest.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    ClientManager clientManager;

    @RequestMapping(method = RequestMethod.GET,value = "/client")
    public ResponseEntity getClient(@RequestParam(value = "client_id",defaultValue = "client test") String client_id){
        ResponseEntity responseEntity = null;
        try {
            responseEntity = clientManager.getClientDetails(client_id);
        }
        catch(DbExceptions e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/client")
    public ResponseEntity addClient(@RequestBody Client requestBody) {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = clientManager.createClient(requestBody);
        }
        catch(DbExceptions e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/client")
    public ResponseEntity updateClient(@RequestBody Client requestBody, @RequestParam(value = "client_id") String client_id) throws DbExceptions{
        ResponseEntity updateResult = null;
        try {
            updateResult = clientManager.updateClient(requestBody, client_id);
        }
        catch(DbExceptions e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return updateResult;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/client")
    public ResponseEntity DeleteClient(@RequestParam(value = "client_id") String client_id){
        ResponseEntity deleteResult =null;
        try{
            deleteResult = clientManager.deleteClient(client_id);
        }
        catch(DbExceptions e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return deleteResult;
    }


}
