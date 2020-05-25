package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//controller tag makes this a spring bean
public class IndexController {
    @RequestMapping({"","/","/index"})
    public String getIndexPage(){
        return "index";
    }
}
