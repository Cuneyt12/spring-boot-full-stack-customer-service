package com.cuneytokmen.controller;

import com.cuneytokmen.entity.LoginInformation;
import com.cuneytokmen.service.ILoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    private final ILoginService iLoginService;

    public LoginController(ILoginService iLoginService) {
        this.iLoginService = iLoginService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String checklogin(@RequestParam("username") String username, @RequestParam("password") String password){
        LoginInformation loginInformation = iLoginService.getLoginInformationByUsername(username);
        if (loginInformation == null)
            return "login";
        else if (loginInformation.getUsername().equals(username) && loginInformation.getPassword().equals(password)){
            return "redirect:/home/get-customers";
        }
        return "login";
    }
}
