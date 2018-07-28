package pl.sda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.Repository.SellingContractsRepository;
import pl.sda.model.BuyingContracts;
import pl.sda.model.Cars;
import pl.sda.model.DtoBuyCar;
import pl.sda.model.SellingContracts;
import pl.sda.service.CarsService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/add")
public class AddCarsController {

    private CarsService carsService;
    private final CarsRepository carsRepository;
    private final BuyingContractsRepository buyingContractsRepository;
    private final SellingContractsRepository sellingContractsRepository;

    @Autowired
    public AddCarsController(CarsService carsService, CarsRepository carsRepository, BuyingContractsRepository buyingContractsRepository, SellingContractsRepository sellingContractsRepository) {
        this.carsService = carsService;
        this.carsRepository = carsRepository;
        this.buyingContractsRepository = buyingContractsRepository;
        this.sellingContractsRepository = sellingContractsRepository;
    }



    @RequestMapping("/new")
//    generuje strone z pustym cars
    public String addCarForm(Model model) {
        model.addAttribute("addedCar", new DtoBuyCar());
        return "addCarForm";

    }

    @PostMapping
    //zapisze do bazy i idzie na nowa strone
    public String saveNewCar(@Valid @ModelAttribute("addedCar") DtoBuyCar dtoBuyCar, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){
            return "addCarForm";
        }

        List<Cars> cars = (List<Cars>) carsRepository.findAll();
        for (Cars c:cars) {
            if (c.getNrChassis().equals(dtoBuyCar.getCarNrChassis())) {
                final String message = "samochód nie może być wprowadzony do bazy bo już kiedyś był kupiony";
                model.addAttribute("message",message);
                return "addCarForm";
            }
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
        car.setVisibility(false);//każdy kupiony samochód niewidoczny

        buyingContracts.setPrice(dtoBuyCar.getBuyingContractsPrice());
        buyingContracts.setDate(new Date());
        buyingContracts.setCars(car);

        carsRepository.save(car);
        buyingContractsRepository.save(buyingContracts);

        return "redirect:/cars";
    }



}


