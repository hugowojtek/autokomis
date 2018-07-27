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

    private final static String pictures[]={
            "/images/auto2.jpg","/images/auto3.jpg",
            "/images/auto10.jpg","/images/auto11.jpg","/images/auto12.jpg","/images/auto13.jpg","/images/auto14.jpg",
            "/images/auto15.jpg","/images/auto16.jpg","/images/auto17.jpg","/images/auto18.jpg","/images/auto19.jpg",
            "/images/auto20.jpg","/images/auto21.jpg","/images/auto22.jpg","/images/auto23.jpg","/images/auto24.jpg",
    };
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
        int i = random.nextInt(16);


        String image = this.pictures[i];//"/images/auto4.jpg";

        model.addAttribute("picture",image) ;

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