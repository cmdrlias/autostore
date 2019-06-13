package com.trabalho.autostore.web.dashboard;

import com.trabalho.autostore.dao.VehicleDao;
import com.trabalho.autostore.model.User;
import com.trabalho.autostore.model.Vehicle;
import com.trabalho.autostore.service.UserService;
import com.trabalho.autostore.service.VehicleService;
import com.trabalho.autostore.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;


    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpSession session, Model model) {

        final User user = getLoggedUser(session);

        List<Vehicle> vehicles = vehicleService.findAll();

        List<Vehicle> available = new ArrayList<>();
        List<Vehicle> rented = new ArrayList<>();
        List<Vehicle> carshop = new ArrayList<>();

        for(Vehicle v : vehicles) {
            if(v.getStatus().getStsCode() == 1) {
                available.add(v);
            }

            if(v.getStatus().getStsCode() == 2) {
                rented.add(v);
            }

            if(v.getStatus().getStsCode() == 3) {
                carshop.add(v);
            }
        }

        model.addAttribute("carshopCount", carshop.size());
        model.addAttribute("rentedCount", rented.size());
        model.addAttribute("availableCount", available.size());
        model.addAttribute("vehicleCount", vehicleService.count());
        model.addAttribute("userCount", userService.count());
        model.addAttribute("user", user);
        model.addAttribute("dashboardSection", Boolean.TRUE);
        model.addAttribute("pageTitle", getMessage("page.title.dashboard"));
        return "dashboard";
    }
}
