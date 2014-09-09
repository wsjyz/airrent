package com.eighth.airrent.controller;

import com.eighth.airrent.domain.Airline;
import com.eighth.airrent.domain.Airport;
import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserInfo;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.AirlineService;
import com.eighth.airrent.proxy.service.AirportService;
import com.eighth.airrent.proxy.service.UserService;
import com.eighth.airrent.util.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

/**
 * Created by kkk on 14/9/4.
 */
@Controller
@RequestMapping(value = "/manage")
public class ManageController {

    @Autowired
    private UserService userService;
    @Autowired
    private AirportService airportService;
    @Autowired
    private AirlineService airlineService;

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        return render(mv,"login");
    }

    @RequestMapping("/toLogin")
    public @ResponseBody JsonResult toLogin(@ModelAttribute UserInfo user){
        JsonResult jsonResult=new JsonResult();
        ModelAndView mv = new ModelAndView();
        user.setType("ADMIN");
        user=userService.findUser(user);
        jsonResult.setSuccess(user != null);
        return jsonResult;
    }

    @RequestMapping("/index")
    public ModelAndView manageIndex() {
        ModelAndView mv = new ModelAndView();
        return render(mv,"index");
    }

    @RequestMapping("/airports")
    public ModelAndView airports() {
        ModelAndView mv = new ModelAndView();
        return render(mv,"airports");
    }

    @RequestMapping("/airports/list")
    public ModelAndView airportList(@ModelAttribute OpenPage page,
                                    @RequestParam String airportName,@RequestParam String address) {
        ModelAndView mv = new ModelAndView();
        page = airportService.findAirportList(page, airportName, address);
        mv.addObject("page", page);
        return render(mv,"airportList");
    }

    @RequestMapping("/airports/add")
    public ModelAndView addAirport() {
        ModelAndView mv = new ModelAndView();
        return render(mv,"airportAdd");
    }

    @RequestMapping("/airports/delete")
    public @ResponseBody JsonResult deleteAirport(@RequestParam String airportId) throws Exception{
        JsonResult jsonResult = new JsonResult();
        String result=airportService.deleteAirprot(airportId);
        jsonResult.setSuccess(StringUtils.equals("SUCCESS",result));
        return jsonResult;
    }

    @RequestMapping(value = "/airports/save",method = RequestMethod.POST)
    public @ResponseBody JsonResult saveAirport(@RequestParam String airportName,@RequestParam String address){
        JsonResult jsonResult = new JsonResult();
        Airport airport=new Airport();
        airport.setAirportName(airportName);
        airport.setAddress(address);
        airport.setDescription(address);
        String result=airportService.saveAirport(airport);
        jsonResult.setSuccess(StringUtils.equals("SUCCESS",result));
        return jsonResult;
    }

    @RequestMapping("/menu/{page}")
    public ModelAndView menuItem(@PathVariable String page) {
        ModelAndView mv = new ModelAndView();
        return render(mv, page);
    }

    @RequestMapping("/{page}/add")
    public ModelAndView addPage(@PathVariable String page) {
        ModelAndView mv = new ModelAndView();
        if (page.equals("airline")) {
            List<Airport> airports=airportService.findAllAirport();
            mv.addObject("airports",airports);
        }
        return render(mv,page+"Add");
    }

    @RequestMapping("/airlines/list")
    public ModelAndView airlineList(@ModelAttribute OpenPage page,
                                    @RequestParam String airlineName,@RequestParam String loginName) {
        ModelAndView mv = new ModelAndView();
        page=airlineService.findAirlineList(page, airlineName, loginName);
        mv.addObject("page", page);
        return render(mv, "airlineList");
    }

    @RequestMapping("/menu/airline/view")
    public ModelAndView viewAirline(@RequestParam String airlineId) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Airport> airports=airportService.findAllAirport();
        mv.addObject("airports",airports);
        Airline airline = airlineService.findAirlineById(airlineId);
        if(StringUtils.isNotBlank(airline.getAirportId())){
            airline.setAirportName(airportService.findAirportById(airline.getAirportId()).getAirportName());
        }
        mv.addObject("airline", airline);
        return render(mv, "airlineView");

    }

    @RequestMapping("/airline/save")
    public
    @ResponseBody
    JsonResult saveAirline(@ModelAttribute Airline airline) {
        JsonResult jsonResult = new JsonResult();
        String result =  airlineService.saveAirline(airline);
        jsonResult.setSuccess(StringUtils.equals("SUCCESS",result));
        return jsonResult;
    }





    private ModelAndView render(ModelAndView mv,String viewName) {
        mv.setViewName("manage/"+viewName);
        return mv;
    }
}
