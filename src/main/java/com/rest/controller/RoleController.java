package com.rest.controller;

import com.mongodb.client.result.UpdateResult;
import com.rest.dao.Role;
import com.rest.manager.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    RoleManager roleManager;

    @RequestMapping(method = RequestMethod.GET,value = "/roles")
    public Role getRoles(@RequestParam(value = "role_id",defaultValue = "Role test") String role_id){
        return roleManager.getRoleDetails(role_id);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/roles")
    public UpdateResult updateRoleDetails(@RequestParam(value = "role_id",defaultValue = "Role test") String role_id,
                                                      @RequestBody Role role_body){
        return roleManager.updateRoleDetails(role_id,role_body);
    }

}
