package com.contemplator.john.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CollisionsServerController {

    @RequestMapping("/date")
    public Date date() {
        return new Date();
    }
}
