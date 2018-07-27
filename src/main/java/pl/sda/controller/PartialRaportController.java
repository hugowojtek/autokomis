package pl.sda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.model.DtoShowCar;
import pl.sda.service.CarsService;

import java.util.List;

@Controller
@RequestMapping("/part")
public class PartialRaportController {

    private CarsService carsService;

    @Autowired
    public PartialRaportController(CarsService carsService) {
        this.carsService = carsService;
    }



    @RequestMapping("/soldcars")
    public String soldCars(Model model){

        List<DtoShowCar> list = carsService.showSoldCars();
        model.addAttribute("cars2",list);
        return "soldCars";
    }

    @RequestMapping("/boughtcars")
    public String boughtCars(Model model){
        List<DtoShowCar> list = carsService.showBoughtCars();
        model.addAttribute("cars3",list);
        return "boughtCars";


    }
}
