package com.example.injection.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @GetMapping("/checkHealth")
    public String checkHealth() {
        return "Health status: OK";
    }
    @Value("${info.app.name}")
    private String AppName;

    @Value("${info.app.description}")
    private String AppDescription;

    @Value("${info.app.version}")
    private String AppVersion;

    @GetMapping("/info")
    public String getInfo() {
        return "Name: " + AppName + ", Description: " + AppDescription + ", Version: " + AppVersion;
    }
}
