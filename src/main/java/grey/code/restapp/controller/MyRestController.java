package grey.code.restapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
grey.code.restapp.controller
Tarih: 19.06.2022, Saat: 12:28, Author: Grey 
*/
@RestController
@RequestMapping("/api")
public class MyRestController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Say hello in ResponseBoddy";
    }
}
