package com.rest.controller;

import com.rest.dao.Role;
import com.rest.manager.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    RoleManager roleManager;

    @RequestMapping(method = RequestMethod.GET,value = "/roles")
    public Role getRoles(@RequestParam(value = "role_id",defaultValue = "Role test") String role_id){
        return roleManager.getRoleDetails(role_id);
    }
}
