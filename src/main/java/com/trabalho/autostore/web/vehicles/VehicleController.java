package com.trabalho.autostore.web.vehicles;

import com.trabalho.autostore.model.Vehicle;
import com.trabalho.autostore.service.VehicleService;
import com.trabalho.autostore.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController extends BaseController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(value = { "/list" })
    public String list(HttpSession session, Model model) {

        List<Vehicle> vehicles = vehicleService.findAll();

        model.addAttribute("vehicles", vehicles);
        model.addAttribute("vehicleSection", Boolean.TRUE);
        model.addAttribute("pageTitle", getMessage("page.title.vehicles"));

        return "vehicles/list";
    }
}
