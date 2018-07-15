package pl.sda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.model.BuyingContracts;
import pl.sda.model.Cars;
import pl.sda.model.DtoBuyCar;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class ShowAllCars {


    private final CarsRepository carsRepository;
    private final BuyingContractsRepository buyingContractsRepository;

    public ShowAllCars(CarsRepository carsRepository, BuyingContractsRepository buyingContractsRepository) {
        this.carsRepository = carsRepository;
        this.buyingContractsRepository = buyingContractsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCars(Model model) {
        List<Cars> cars = (List<Cars>) carsRepository.findAll();
        model.addAttribute("cars1", cars);
        return "allCars";
    }

    @RequestMapping("/{carId}/desc")
    public String getCarDescription(
            @PathVariable("carId") Long carId, Model model) {
        Cars car = carsRepository.findOne(carId);
        String description = car.getDescription();
        model.addAttribute("carDesc", description);
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
    public String saveVehicle(@ModelAttribute("addedVehicle") DtoBuyCar dtoBuyCar){

        Cars car = new Cars();
        BuyingContracts buyingContracts = new BuyingContracts();

        car.setYearProduction(dtoBuyCar.getCarYearProduction());
        car.setManufacturer(dtoBuyCar.getCarManufacturer());
        car.setModel(dtoBuyCar.getCarModel());

        buyingContracts.setPrice(dtoBuyCar.getBuyingContractsPrice());
        buyingContracts.setDate(new Date());

        carsRepository.save(car);
        buyingContractsRepository.save(buyingContracts);

        return "redirect:/cars";
    }
}
