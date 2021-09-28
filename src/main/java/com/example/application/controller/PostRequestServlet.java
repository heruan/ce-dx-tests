package com.example.application.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.collaborationengine.UserInfo;

@RestController
public class PostRequestServlet {

    @Autowired
    private ServletContext servletContext;

    private final UserInfo userInfo = new UserInfo("ext", "External");

    @PostMapping("/submit")
    public String submitMessage(@RequestParam("message") String message) {
        // Add code here
        return message;
    }

}
