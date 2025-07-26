package com.donedeal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticsReturnsController {


        @GetMapping("/success")
        public String success(){
            return "success";
        }

        @GetMapping("/failed")
        public String failed(){
            return "failed";
        }

}
