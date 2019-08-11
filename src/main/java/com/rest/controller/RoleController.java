package com.rest.controller;

import com.mongodb.client.result.UpdateResult;
import com.rest.dao.Role;
import com.rest.exceptions.DbExceptions;
import com.rest.manager.RoleManager;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
public class RoleController {

    @Autowired
    RoleManager roleManager;

    @RequestMapping(method = RequestMethod.GET,value = "/roles")
    public ResponseEntity getRoles(@RequestParam(value = "role_id",defaultValue = "Role test") String role_id){
        ResponseEntity responseEntity =null;
        try{
            responseEntity = roleManager.getRoleDetails(role_id);
        }
        catch(DbExceptions e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/roles")
    public ResponseEntity updateRoleDetails(@RequestParam(value = "role_id",defaultValue = "Role test") String role_id,
                                      @RequestBody Role role_body)throws DbExceptions{
        ResponseEntity responseEntity = null;
        try {
            responseEntity = roleManager.updateRoleDetails(role_id, role_body);
        }
        catch(DbExceptions e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return responseEntity;
    }

}
