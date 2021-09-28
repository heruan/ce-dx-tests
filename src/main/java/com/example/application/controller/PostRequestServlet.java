package com.example.application.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaadin.collaborationengine.UserInfo;

@RestController
@RequestMapping("/submit")
public class PostRequestServlet {

    private static final String FORM = "<form method=\"post\">"
            + "<input name=\"message\"><button type=\"submit\">Submit</button>"
            + "</form>";

    @Autowired
    private ServletContext servletContext;

    private final UserInfo userInfo = new UserInfo("ext", "External");

    @PostMapping(produces = "text/html")
    public String submitMessage(@RequestParam("message") String message) {
        // Add code here
        return FORM;
    }

    @GetMapping(produces = "text/html")
    public String showForm() {
        return FORM;
    }

}
