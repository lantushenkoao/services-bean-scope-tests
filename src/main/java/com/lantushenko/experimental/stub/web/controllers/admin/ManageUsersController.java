package com.lantushenko.experimental.stub.web.controllers.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lantushenko.experimental.stub.dao.User;
import com.lantushenko.experimental.stub.services.JsonSerializer;
import com.lantushenko.experimental.stub.services.UserService;
import com.lantushenko.experimental.stub.services.WebUtils;
import com.lantushenko.experimental.stub.web.ModelParamNames;
import com.lantushenko.experimental.stub.web.serialize.json.AjaxStatusDto;

@Controller
public class ManageUsersController {

    @Autowired
    private UserService userService;
    @Autowired
    private JsonSerializer serializer;
    @Autowired
    private WebUtils webUtils;

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS')")
    public ModelAndView addUserForm() {
        return new ModelAndView("admin/addUser");
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS')")
    public ModelAndView addUser(
            @RequestParam String login,
            @RequestParam String fullName,
            @RequestParam String password
            ) {
        userService.add(login, fullName, password);
        return new ModelAndView("redirect:/admin/listUsers");
    }

    @RequestMapping(value = {"/admin/users/edit", "/myaccount"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS') or ((null == #userId) && (null != authentication.name))")
    public ModelAndView editUserForm(@RequestParam(required = false) Long userId) {
        Map<String, Object> model = new HashMap<>();
        Long id = userId;
        if (null == userId) {
            id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        }
        model.put(ModelParamNames.USER, userService.findUserById(id));
        return new ModelAndView("/admin/editUser", model);
    }

    @RequestMapping(value = {"/admin/users/edit", "/myaccount"}, method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS') or (authentication.id == #userId)")
    public ModelAndView editUser(
            @RequestParam Long userId,
            @RequestParam String fullName,
            @RequestParam(required = false) String password) {

        User user = userService.findUserById(userId);
        user.setFullName(fullName);
        userService.save(user);

        if (StringUtils.hasText(password)) {
            userService.updateUserPassword(userId, password);
        }
        ModelAndView mv = new ModelAndView("redirect:/");
        mv.addObject("userId", userId);
        return mv;
    }

    @RequestMapping(value = "/admin/users/delete", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_MANAGE_USERS')")
    public @ResponseBody ResponseEntity<String> deleteUser(@RequestParam Long userId) throws Exception {
        User user = userService.findUserById(userId);
        userService.delete(user);

        return new ResponseEntity<String>(serializer.serialize(new AjaxStatusDto(true, "saved")),
                webUtils.makeJsonHeader(), HttpStatus.OK);
    }
}
