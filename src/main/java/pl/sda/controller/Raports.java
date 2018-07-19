package pl.sda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.model.Cars;
import pl.sda.model.DtoShowCar;
import pl.sda.service.CarsService;
import pl.sda.service.RaportsService;

import java.util.List;

@Controller
@RequestMapping("/raports")
public class Raports {

    CarsService carsService;
    RaportsService raportsService;

    public Raports(CarsService carsService, RaportsService raportsService) {
        this.carsService = carsService;
        this.raportsService = raportsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String ShowSumOfBoughtCars(Model model){
        List<DtoShowCar> list1 = carsService.showBoughtCars();
        List<DtoShowCar> list2 = carsService.showAvailableCars();
        //List<DtoShowCar> list3 = carsService.showSoldCars();
        List<DtoShowCar> list3 = raportsService.ShowSoldCarsWithMarginAndProfit();
        Long value = raportsService.CalculateAllProfit();
        model.addAttribute("cars1",list1);
        model.addAttribute("cars2",list2);
        model.addAttribute("cars3",list3);
        model.addAttribute("value",value);
        return "fullRaports";
    }

}
