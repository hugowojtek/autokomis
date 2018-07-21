package pl.sda.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.Repository.CarsRepository;
import pl.sda.model.Cars;
import pl.sda.model.DtoShowCar;
import pl.sda.service.CarsService;

import java.util.List;

@Controller
@RequestMapping("/edit")
public class EditCarsController {

    private CarsService carsService;
    private final CarsRepository carsRepository;

    public EditCarsController(CarsService carsService, CarsRepository carsRepository) {
        this.carsService = carsService;
        this.carsRepository = carsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCars(Model model) {

        List<DtoShowCar> list = carsService.showAvailableCars();

        model.addAttribute("cars1", list);
        return "editCar";

    }

    @RequestMapping("/{carId}/desc")
    public String getCarDescription(
            @PathVariable("carId") Long carId, Model model) {
        Cars car = carsRepository.findOne(carId);
//        String description = car.getDescription();
        model.addAttribute("car1", car);
        return "editForm";
    }

}
