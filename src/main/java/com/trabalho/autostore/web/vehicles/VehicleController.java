package com.trabalho.autostore.web.vehicles;

import com.trabalho.autostore.model.Group;
import com.trabalho.autostore.model.Status;
import com.trabalho.autostore.model.Vehicle;
import com.trabalho.autostore.service.VehicleService;
import com.trabalho.autostore.web.BaseController;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
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

    @RequestMapping(value = { "/add" })
    public String add(HttpSession session, Model model) {
        List<Group> groups = vehicleService.findAllGroups();
        List<Status> status = vehicleService.findAllStatus();

        model.addAttribute("groups", groups);
        model.addAttribute("status", status);

        model.addAttribute("vehicleSection", Boolean.TRUE);
        model.addAttribute("pageTitle", getMessage("page.title.vehicles.add"));
        return "vehicles/add";
    }

    @RequestMapping(value = { "/save" })
    public String save(HttpServletRequest request, Model model, HttpSession session) throws ServletException, IOException {
        String vclName = request.getParameter("vcl_name");
        String vclPlaque = request.getParameter("vcl_plaque");
        String vclGroupCode = request.getParameter("vcl_group");
        String vclStatusCode = request.getParameter("vcl_status");
        String vclPhotoName = request.getParameter("vcl_photo");

        return list(session, model);
    }
}
