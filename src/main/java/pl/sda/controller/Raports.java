package pl.sda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/raports")
public class Raports {
    public String ShowSumOfBoughtCars(Model model){
        return "hello";
    }

}
