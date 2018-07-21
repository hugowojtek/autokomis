package pl.sda.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/raports")
public class Raports {

    private String dateFrom;
    private String dateTo;
    private CarsService carsService;
    private RaportsService raportsService;

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

    @RequestMapping("/salefilter")
    public String ShowSaleRaport(Model model){
        Date date1 = null;
        Date date2 = null;
        String strDate1 = dateFrom;
        String strDate2 = dateTo;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(strDate1);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(strDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<DtoShowCar> list = raportsService.SaleFilter(date1,date2);
        List<DtoShowCar> list2 = raportsService.ShowSoldCarsWithMarginAndProfit2(list);
        Long value = raportsService.CalculateSaleFilterValue(list);

        String formattedStrDate1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String formattedStrDate2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);

        model.addAttribute("list",list2);
        model.addAttribute("value",value);
        model.addAttribute("date1",formattedStrDate1);
        model.addAttribute("date2",formattedStrDate2);

        return "saleRaports";
    }


    @RequestMapping("/saleraport")
    public String saleRaportForm(Model model) {
        model.addAttribute("saleRaport", new DtoRaport());
        return "saleForm";
    }

    @PostMapping
    public String saveDateForSaleRaport(@ModelAttribute("saleRaport") DtoRaport dtoRaport, Model model){
        dateFrom = dtoRaport.getDateFrom();
        dateTo = dtoRaport.getDateTo();


        return "redirect:/raports/salefilter";
    }






}
