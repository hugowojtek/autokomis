package pl.sda.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.model.Cars;
import pl.sda.model.DtoBuyCar;
import pl.sda.model.DtoRaport;
import pl.sda.model.DtoShowCar;
import pl.sda.service.CarsService;
import pl.sda.service.RaportsService;


import java.util.List;

@Controller
@RequestMapping("/raports")
public class FullRaportController {


    private CarsService carsService;
    private RaportsService raportsService;

    @Autowired
    public FullRaportController(CarsService carsService, RaportsService raportsService) {
        this.carsService = carsService;
        this.raportsService = raportsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String ShowSumOfBoughtCars(Model model){
        List<DtoShowCar> list1 = carsService.showBoughtCars();
        List<DtoShowCar> list2 = carsService.showAvailableCars();
        //List<DtoShowCar> list3 = carsService.showSoldCars();
        List<DtoShowCar> list3 = raportsService.ShowSoldCarsWithMarginAndProfit();
        Long value1 = raportsService.CalculateAllSumOfBoughtCars();
        Long value2 = raportsService.CalculateAllSumOfSoldCars();
        Long value3 = raportsService.CalculateProfit(list3);
        model.addAttribute("cars1",list1);
        model.addAttribute("cars2",list2);
        model.addAttribute("cars3",list3);
        model.addAttribute("value1",value1);
        model.addAttribute("value2",value2);
        model.addAttribute("value3",value3);
        return "fullRaports";
    }







}
