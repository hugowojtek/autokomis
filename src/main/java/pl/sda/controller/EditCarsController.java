package pl.sda.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.Repository.BuyingContractsRepository;
import pl.sda.Repository.CarsRepository;
import pl.sda.model.BuyingContracts;
import pl.sda.model.Cars;
import pl.sda.model.DtoBuyCar;
import pl.sda.model.DtoShowCar;
import pl.sda.service.CarsService;
import pl.sda.service.RaportsService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/edit")
public class EditCarsController {

    private Cars carLocal;
    private CarsService carsService;
    private RaportsService raportsService;
    private final CarsRepository carsRepository;
    private final BuyingContractsRepository buyingContractsRepository;

    public EditCarsController(CarsService carsService, RaportsService raportsService, CarsRepository carsRepository, BuyingContractsRepository buyingContractsRepository) {
        this.carsService = carsService;
        this.raportsService = raportsService;
        this.carsRepository = carsRepository;
        this.buyingContractsRepository = buyingContractsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String selectCarToEdit(Model model) {

        List<DtoShowCar> list = carsService.showAvailableCars();
        model.addAttribute("cars1", list);
        return "editCar";

    }

    @RequestMapping("/{carId}/editable")
    public String editCarForm(
            @PathVariable("carId") Long carId, Model model) {
        Cars car = carsRepository.findOne(carId);
        this.carLocal=car;
        Long purchasePrice = 0L;
        List<BuyingContracts> buyingContracts = (List<BuyingContracts>) buyingContractsRepository.findAll();
        for (BuyingContracts bc : buyingContracts) {
            if (bc.getCars().getId().equals(car.getId())) {
                purchasePrice = bc.getPrice();
                break;
            }
        }

        Long profit = raportsService.CalculateProfit2(car,purchasePrice);
        float margin = raportsService.CalculateMargin(car,purchasePrice);

        //zapisywanie formularza danymi
        DtoBuyCar dtoBuyCar = new DtoBuyCar();
        dtoBuyCar.setCarVisibility(car.getVisibility());
        dtoBuyCar.setCarManufacturer(car.getManufacturer());
        dtoBuyCar.setCarModel(car.getModel());
        dtoBuyCar.setCarYearProduction(car.getYearProduction());
        dtoBuyCar.setCarMilage(car.getMilage());
        dtoBuyCar.setCarNrChassis(car.getNrChassis());
        dtoBuyCar.setCarDescription(car.getDescription());
        dtoBuyCar.setCarPrice(car.getPrice());
        dtoBuyCar.setBuyingContractsPrice(purchasePrice);


        model.addAttribute("car1", car);
        model.addAttribute("purchasePrice", purchasePrice);
        model.addAttribute("profit", profit);
        model.addAttribute("margin", margin);
        model.addAttribute("editedCar",dtoBuyCar);
        return "editForm";
    }

    @PostMapping
    public String saveEditedCar(@Valid @ModelAttribute("editedCar") DtoBuyCar dtoBuyCar, Model model) {

        //Cars car = new Cars();
        Long l = dtoBuyCar.getBuyingContractsPrice();
        Cars car = carsRepository.findOne(this.carLocal.getId());
        //car.setId(this.carLocal.getId());
        car.setYearProduction(dtoBuyCar.getCarYearProduction());
        car.setManufacturer(dtoBuyCar.getCarManufacturer());
        car.setModel(dtoBuyCar.getCarModel());
        car.setMilage(dtoBuyCar.getCarMilage());
        car.setDescription(dtoBuyCar.getCarDescription());
        car.setPrice(dtoBuyCar.getCarPrice());
        car.setNrChassis(dtoBuyCar.getCarNrChassis());
        car.setVisibility(dtoBuyCar.getCarVisibility());

        BuyingContracts buyingContracts = new BuyingContracts();
        List<BuyingContracts> list = (List<BuyingContracts>) buyingContractsRepository.findAll();
        for (BuyingContracts bc:list){
            if (bc.getCars().getId().equals(car.getId())){
                buyingContracts = buyingContractsRepository.findOne(bc.getId());
                break;
            }
        }
        buyingContracts.setPrice(dtoBuyCar.getBuyingContractsPrice());
        //buyingContracts.setDate(new Date());//stara data edytowane samochodu zostaje
        buyingContracts.setCars(car);

        System.out.println();


        carsRepository.save(car);
        buyingContractsRepository.save(buyingContracts);
        return "redirect:/cars";

    }
}
