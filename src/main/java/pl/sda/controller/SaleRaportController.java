package pl.sda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.model.DtoRaport;
import pl.sda.model.DtoShowCar;
import pl.sda.service.CarsService;
import pl.sda.service.RaportsService;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleRaportController {

    private String dateFrom;
    private String dateTo;
    private CarsService carsService;
    private RaportsService raportsService;

    @Autowired
    public SaleRaportController(CarsService carsService, RaportsService raportsService) {
        this.carsService = carsService;
        this.raportsService = raportsService;
    }

    @RequestMapping("/raport")
    public String saleRaportForm(Model model) {
        model.addAttribute("saleRaport", new DtoRaport());
        return "saleForm";
    }

    @PostMapping
    public String saveDateForSaleRaport(@Valid @ModelAttribute("saleRaport") DtoRaport dtoRaport, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "saleForm";
        }

        dateFrom = dtoRaport.getDateFrom();
        dateTo = dtoRaport.getDateTo();


        return "redirect:/sale/salefilter";
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
        Long value2 = raportsService.CalculateProfit(list2);

        String formattedStrDate1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String formattedStrDate2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);

        model.addAttribute("list",list2);
        model.addAttribute("value",value);
        model.addAttribute("value2",value2);
        model.addAttribute("date1",formattedStrDate1);
        model.addAttribute("date2",formattedStrDate2);

        return "saleRaports";
    }

}
