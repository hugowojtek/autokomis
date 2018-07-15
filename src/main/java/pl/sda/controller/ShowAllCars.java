package pl.sda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.Repository.CarsRepository;
import pl.sda.model.Cars;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class ShowAllCars {


    private final CarsRepository carsRepository;

    public ShowAllCars(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @RequestMapping(method =  RequestMethod.GET)
    public String getCars(Model model){
        List<Cars> cars = (List<Cars>)carsRepository.findAll();
        model.addAttribute("cars1",cars);
        return "allcars";
    }

    @RequestMapping("/{carId}/desc")
    public String getCarDescription(
            @PathVariable("carId") Long carId, Model model){
        Cars car = carsRepository.findOne(carId);
        String description = car.getDescription();
        model.addAttribute("carDesc",description);
        return "description";
    }
//
//    @RequestMapping("/{vehicleId}/sell")
//    public String sellVehicleForm(
//            @PathVariable("vehicleId") Long vehicleId,
//            Model model){
//        model.addAttribute("vehicleId",vehicleId);
//        model.addAttribute("sellData",new PurchaseFormData());
//        return "sellVehicle";
//    }
}
