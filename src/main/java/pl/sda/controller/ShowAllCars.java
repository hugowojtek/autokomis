package pl.sda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.model.BuyingContracts;
import pl.sda.model.Cars;
import pl.sda.model.DtoBuyCar;
import pl.sda.model.DtoShowCar;

import java.util.ArrayList;
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
        List<BuyingContracts> buyingContracts = (List<BuyingContracts>) buyingContractsRepository
                .findAll();
        List<DtoShowCar> list = new ArrayList<>();
        for (Cars c:cars){
            DtoShowCar dtoShowCar = new DtoShowCar();
            dtoShowCar.setId(c.getId());
            dtoShowCar.setCarManufacturer(c.getManufacturer());
            dtoShowCar.setCarModel(c.getModel());
            dtoShowCar.setCarYearProduction(c.getYearProduction());
            dtoShowCar.setCarMilage(c.getMilage());

            for(BuyingContracts bc:buyingContracts){

                if (c.getId().equals(bc.getCars().getId())){
                    dtoShowCar.setBuyingContractsPrice(bc.getPrice());
                }
            }
            list.add(dtoShowCar);
        }

        model.addAttribute("cars1", list);
        return "allCars";
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
    public String saveVehicle(@ModelAttribute("addedCar") DtoBuyCar dtoBuyCar){

        Cars car = new Cars();
        BuyingContracts buyingContracts = new BuyingContracts();

        car.setYearProduction(dtoBuyCar.getCarYearProduction());
        car.setManufacturer(dtoBuyCar.getCarManufacturer());
        car.setModel(dtoBuyCar.getCarModel());
        car.setDescription(dtoBuyCar.getCarDescription());

        buyingContracts.setPrice(dtoBuyCar.getBuyingContractsPrice());
        buyingContracts.setDate(new Date());
        buyingContracts.setCars(car);

        carsRepository.save(car);
        buyingContractsRepository.save(buyingContracts);

        return "redirect:/cars";
    }
}
