package pl.sda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.Repository.SellingContractsRepository;
import pl.sda.model.*;
import pl.sda.service.CarsService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/cars")
public class ShowCarsController {

    private CarsService carsService;
    private final CarsRepository carsRepository;
    private final BuyingContractsRepository buyingContractsRepository;
    private final SellingContractsRepository sellingContractsRepository;

    @Autowired
    public ShowCarsController(CarsService carsService, CarsRepository carsRepository, BuyingContractsRepository buyingContractsRepository, SellingContractsRepository sellingContractsRepository) {
        this.carsService = carsService;
        this.carsRepository = carsRepository;
        this.buyingContractsRepository = buyingContractsRepository;
        this.sellingContractsRepository = sellingContractsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCars(Model model) {

        List<DtoShowCar> list = carsService.showAvailableCars();
        List<DtoShowCar> list1 = carsService.showAvailableCars2(list);
        model.addAttribute("cars1", list1);
        return "availableCars";

    }

    @RequestMapping("/{carId}/desc")
    public String getCarDescription(
            @PathVariable("carId") Long carId, Model model) {
        Cars car = carsRepository.findOne(carId);
//        String description = car.getDescription();
        model.addAttribute("car1", car);
        Random random = new Random();
        int i = random.nextInt(6);
        Object object = new Object();

        model.addAttribute("obrazek",i) ;

        return "description";
    }

    @RequestMapping("/{carId}/buy")
    public String buyCar(
            @PathVariable("carId") Long id, Model model) {
        Cars car = carsRepository.findOne(id);
//        List<BuyingContracts> list = (List<BuyingContracts>) buyingContractsRepository.findAll();

        SellingContracts sellingContracts = new SellingContracts();
        sellingContracts.setDate(new Date());
        sellingContracts.setCars(car);
        sellingContracts.setPrice(car.getPrice());

        sellingContractsRepository.save(sellingContracts);

        return "redirect:/cars";

    }
}