package com.example.SpringBootDemo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/DoTest")
    public  void  test(){

    }
}
