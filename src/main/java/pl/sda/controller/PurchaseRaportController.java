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
@RequestMapping("/purchase")
public class PurchaseRaportController {

    private String dateFrom;
    private String dateTo;
    private CarsService carsService;
    private RaportsService raportsService;

    @Autowired
    public PurchaseRaportController(CarsService carsService, RaportsService raportsService) {
        this.carsService = carsService;
        this.raportsService = raportsService;
    }

    @RequestMapping("/raport")
    public String purchaseRaportForm(Model model) {
        model.addAttribute("purchaseRaport", new DtoRaport());
        return "purchaseForm";
    }

    @PostMapping
    public String saveDateForPurchaseRaport(@Valid @ModelAttribute("purchaseRaport") DtoRaport dtoRaport, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "purchaseForm";
        }

        dateFrom = dtoRaport.getDateFrom();
        dateTo = dtoRaport.getDateTo();


        return "redirect:/purchase/purchasefilter";
    }

    @RequestMapping("/purchasefilter")
    public String ShowPurchaseRaport(Model model){
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

        List<DtoShowCar> list = raportsService.PurchaseFilter(date1,date2);
        Long value = raportsService.CalculatePurchaseFilterValue(list);

        String formattedStrDate1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String formattedStrDate2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);

        model.addAttribute("list",list);
        model.addAttribute("value",value);
        model.addAttribute("date1",formattedStrDate1);
        model.addAttribute("date2",formattedStrDate2);

        return "purchaseRaports";
    }

}
