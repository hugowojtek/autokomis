package pl.sda.controller;

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

@Controller
@RequestMapping("/cars")
public class ShowCars {

    private CarsService carsService;
    private final CarsRepository carsRepository;
    private final BuyingContractsRepository buyingContractsRepository;
    private final SellingContractsRepository sellingContractsRepository;

    public ShowCars(CarsService carsService, CarsRepository carsRepository, BuyingContractsRepository buyingContractsRepository, SellingContractsRepository sellingContractsRepository) {
        this.carsService = carsService;
        this.carsRepository = carsRepository;
        this.buyingContractsRepository = buyingContractsRepository;
        this.sellingContractsRepository = sellingContractsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCars(Model model) {


        List<DtoShowCar> list = carsService.showCars();
        List<DtoShowCar> list2 = carsService.showAvailableCars();

        model.addAttribute("cars1", list2);
        return "availableCars";

    }

    @RequestMapping("/{carId}/desc")
    public String getCarDescription(
            @PathVariable("carId") Long carId, Model model) {
        Cars car = carsRepository.findOne(carId);
//        String description = car.getDescription();
        model.addAttribute("car1", car);
        return "description";
    }

    @RequestMapping("/new")
//    generuje strone z pustym cars
    public String addCarForm(Model model) {
        model.addAttribute("addedCar", new DtoBuyCar());
        return "addCar";

    }

    @PostMapping
    //zapisze do bazy i idzie na nowa strone
    public String saveVehicle(@Valid @ModelAttribute("addedCar") DtoBuyCar dtoBuyCar, BindingResult bindingResult ) {

        if (bindingResult.hasErrors()){
            return "addCar";
        }

        Cars car = new Cars();
        BuyingContracts buyingContracts = new BuyingContracts();

        car.setYearProduction(dtoBuyCar.getCarYearProduction());
        car.setManufacturer(dtoBuyCar.getCarManufacturer());
        car.setModel(dtoBuyCar.getCarModel());
        car.setMilage(dtoBuyCar.getCarMilage());
        car.setDescription(dtoBuyCar.getCarDescription());
        car.setPrice(dtoBuyCar.getCarPrice());
        car.setNrChassis(dtoBuyCar.getCarNrChassis());

        buyingContracts.setPrice(dtoBuyCar.getBuyingContractsPrice());
        buyingContracts.setDate(new Date());
        buyingContracts.setCars(car);

        carsRepository.save(car);
        buyingContractsRepository.save(buyingContracts);

        return "redirect:/cars";
    }

    @RequestMapping("/{carId}/buy")
    public String buyCar(
            @PathVariable("carId") Long id, Model model) {
        Cars car = carsRepository.findOne(id);
        List<BuyingContracts> list = (List<BuyingContracts>) buyingContractsRepository.findAll();

        SellingContracts sellingContracts = new SellingContracts();
        sellingContracts.setDate(new Date());
        sellingContracts.setCars(car);
        sellingContracts.setPrice(car.getPrice());

        sellingContractsRepository.save(sellingContracts);

        return "redirect:/cars";

    }

    @RequestMapping("/soldcars")
    public String soldCars(Model model){

        List<DtoShowCar> list = carsService.showSoldCars();
        model.addAttribute("cars2",list);
        return "soldCars";
    }
}
