package pl.sda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.model.Cars;
import pl.sda.model.DtoShowCar;
import pl.sda.service.CarsService;
import pl.sda.service.RaportsService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        Long value1 = raportsService.CalculateAllSumOfBoughtCars();
        Long value2 = raportsService.CalculateAllSumOfSoldCars();
        model.addAttribute("cars1",list1);
        model.addAttribute("cars2",list2);
        model.addAttribute("cars3",list3);
        model.addAttribute("value1",value1);
        model.addAttribute("value2",value2);
        return "fullRaports";
    }

    @RequestMapping("/sale")
    public String ShowSaleRaport(Model model){
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-06");
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-30");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<DtoShowCar> list = raportsService.SaleFilter(date1,date2);

        return "saleRaports";
    }

}
