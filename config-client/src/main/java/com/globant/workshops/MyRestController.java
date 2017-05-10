package com.globant.workshops;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
class MyRestController {

    @Value("${value: default}")
    String value;

    @RequestMapping("/")
    public String home() {
        return value;
    }
}
