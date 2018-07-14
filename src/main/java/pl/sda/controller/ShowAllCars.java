package pl.sda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.Repository.CarsRepository;
import pl.sda.model.Cars;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class ShowAllCars {

    @Autowired
    private final CarsRepository carsRepository;

    public ShowAllCars(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }


    public String getCars(Model model){
        List<Cars>cars = (List<Cars>)carsRepository.findAll();
        model.addAttribute("cars1",cars);
        return "allCars";
    }
}
