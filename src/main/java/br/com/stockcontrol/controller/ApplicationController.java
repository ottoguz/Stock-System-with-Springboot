package br.com.stockcontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApplicationController {
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String index() {
        return "/customer/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/customer/login";
    }
}
