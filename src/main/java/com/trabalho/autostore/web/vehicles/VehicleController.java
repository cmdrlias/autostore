package com.trabalho.autostore.web.vehicles;

import com.trabalho.autostore.model.Group;
import com.trabalho.autostore.model.Status;
import com.trabalho.autostore.model.Vehicle;
import com.trabalho.autostore.service.VehicleService;
import com.trabalho.autostore.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController extends BaseController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
    public String list(HttpSession session, Model model, HttpServletRequest request) {
        List<Vehicle> vehicles = vehicleService.findAll();

        model.addAttribute("details", false);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("vehicleSection", Boolean.TRUE);
        model.addAttribute("pageTitle", getMessage("page.title.vehicles"));

        return "vehicles/list";
    }

    @RequestMapping(value = {"/details"}, method = RequestMethod.GET)
    public String details(@RequestParam(value="id", required=true) Integer vclCode, HttpSession session, Model model, HttpServletRequest request) {
        Vehicle vehicle = vehicleService.findByVclCode(vclCode);

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("details", true);
        model.addAttribute("vehicleSection", Boolean.TRUE);
        model.addAttribute("pageTitle", getMessage("page.title.vehicles"));

        return list(session, model, request);
    }

    @RequestMapping(value = { "/search" })
    public String search(@RequestParam(value="searchbar", required=true) String result,
                         HttpSession session, Model model, HttpServletRequest request) {

        List<Vehicle> vehicles = vehicleService.findAll();
        List<Vehicle> searchList = new ArrayList<>();

        for(Vehicle v : vehicles) {
            if(v.getVclName().toLowerCase().contains(result.toLowerCase()) || v.getVclPlaque().toLowerCase().contains(result.toLowerCase()) ||
                    v.getGroup().getGrpDesc().toLowerCase().contains(result.toLowerCase()) || v.getStatus().getStsName().toLowerCase().contains(result.toLowerCase())) {
                searchList.add(v);
            }
        }

        model.addAttribute("vehicles", searchList);
        model.addAttribute("vehicleSection", Boolean.TRUE);
        model.addAttribute("pageTitle", getMessage("page.title.vehicles.search")
                + " " + result);
        return "vehicles/list";
    }

    @RequestMapping(value = { "/add" })
    public String add(HttpSession session, Model model) {
        List<Group> groups = vehicleService.findAllGroups();
        List<Status> status = vehicleService.findAllStatus();

        model.addAttribute("groups", groups);
        model.addAttribute("status", status);

        model.addAttribute("add", true);
        model.addAttribute("vehicleSection", Boolean.TRUE);
        model.addAttribute("pageTitle", getMessage("page.title.vehicles.add"));
        return "vehicles/add";
    }

    @RequestMapping(value = { "/edit" })
    public String edit(@RequestParam(value="id", required=true) Integer vclCode, HttpSession session, Model model) {
        Vehicle vehicle = vehicleService.findByVclCode(vclCode);

        List<Group> groups = vehicleService.findAllGroups();
        List<Status> status = vehicleService.findAllStatus();

        model.addAttribute("groups", groups);
        model.addAttribute("status", status);

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("edit", true);
        model.addAttribute("vehicleSection", Boolean.TRUE);
        model.addAttribute("pageTitle", getMessage("page.title.vehicles.add"));
        return "vehicles/add";
    }

    @RequestMapping(value = { "/save" }, method = RequestMethod.POST, headers={"content-type=multipart/form-data"})
    public String save(@RequestParam(value="state", required=true) String state, @RequestParam(value="vclCode", required=false) Integer vclCode, HttpServletRequest request, Model model, HttpSession session, @RequestParam("vcl_photo") MultipartFile file) throws IOException, SQLException {
        String vclName = request.getParameter("vcl_name");
        String vclPlaque = request.getParameter("vcl_plaque");
        String vclGroupCode = request.getParameter("vcl_group");
        String vclStatusCode = request.getParameter("vcl_status");

        Blob vclPhoto = new SerialBlob(file.getBytes());

        if (state.equals("add")) {
            Vehicle v = new Vehicle();

            v.setVclName(vclName);
            v.setVclPlaque(vclPlaque);
            v.setGroup(vehicleService.findGrpByCode(Integer.parseInt(vclGroupCode)));
            v.setStatus(vehicleService.findStsByCode(Integer.parseInt(vclStatusCode)));
            v.setVclPhoto(vclPhoto);

            v.setBasePhoto(file);

            vehicleService.add(v);

            setModalSuccess(getMessage("message.vehicles.success.add"), model);
        }

        if (state.equals("edit")) {
            Vehicle v = vehicleService.findByVclCode(vclCode);

            v.setVclName(vclName);
            v.setVclPlaque(vclPlaque);
            v.setGroup(vehicleService.findGrpByCode(Integer.parseInt(vclGroupCode)));
            v.setStatus(vehicleService.findStsByCode(Integer.parseInt(vclStatusCode)));
            v.setVclPhoto(vclPhoto);

            v.setBasePhoto(file);

            setModalSuccess(getMessage("message.vehicles.success.edit"), model);
        }

        return list(session, model, request);
    }

    @RequestMapping(value = { "/delete" })
    public String delete(@RequestParam(value="id", required=true) Integer vclCode, HttpServletRequest request, Model model, HttpSession session) {
        Vehicle vehicle = vehicleService.findByVclCode(vclCode);

        vehicleService.delete(vehicle);

        setModalSuccess(getMessage("message.vehicles.success.remove"), model);

        return list(session, model, request);
    }
}
