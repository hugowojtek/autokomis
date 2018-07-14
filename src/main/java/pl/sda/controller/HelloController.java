package pl.sda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/hi")
    public String  welcome(Model model){
        model.addAttribute("name","cokolwiek");
        return "hello";
    }
}
