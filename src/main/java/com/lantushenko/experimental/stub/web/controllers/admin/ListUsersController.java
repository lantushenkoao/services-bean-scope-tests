package com.lantushenko.experimental.stub.web.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lantushenko.experimental.stub.dao.User;
import com.lantushenko.experimental.stub.services.JsonSerializer;
import com.lantushenko.experimental.stub.services.UserService;
import com.lantushenko.experimental.stub.services.WebUtils;
import com.lantushenko.experimental.stub.web.serialize.json.UserDto;

@Controller
public class ListUsersController {

    @Autowired
    private JsonSerializer serializer;

    @Autowired
    private WebUtils webUtils;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/listUsers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS')")
    public ModelAndView listUsers() {
        return new ModelAndView("admin/listUsers");
    }

    @RequestMapping(value = "/admin/ajax/listUsersJSON", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS')")
    public @ResponseBody ResponseEntity<String> listUsersJson() throws Exception {

        List<UserDto> dtos = new ArrayList<UserDto>();
        for (User user : userService.listUsers()) {
            dtos.add(new UserDto(user));
        }

        return new ResponseEntity<String>(serializer.serialize(dtos), webUtils.makeJsonHeader(), HttpStatus.OK);
    }
}
