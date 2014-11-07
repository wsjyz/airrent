package com.eighth.airrent.controller;

import com.eighth.airrent.domain.*;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.*;
import com.eighth.airrent.util.AirrentUtils;
import com.eighth.airrent.util.CommonUtils;
import com.eighth.airrent.util.JsonResult;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private PlaneService planeService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private SettingService settingService;

    @Value("#{configProperties['host']}")
    private String host;
    
    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        return render(mv, "login");
    }

    @RequestMapping("/toLogin")
    public
    @ResponseBody
    JsonResult toLogin(@ModelAttribute UserInfo user,HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        ModelAndView mv = new ModelAndView();
        if (user.getLoginName().equals("admin")) {
            user.setType("ADMIN");
            user = userService.findUser(user);
            jsonResult.setSuccess(user != null);
            if (user != null) {
                session.setAttribute(AirrentUtils.SEESION_ROLE, "ADMIN");
                session.setAttribute(AirrentUtils.SEESION_ROLE_ID,user.getUserId());
            }
        }else{
            Airline airLine=airlineService.loginAirline(user.getLoginName(),user.getPassword());
            jsonResult.setSuccess(airLine != null);
            if (airLine != null) {
                session.setAttribute(AirrentUtils.SEESION_ROLE, "GUEST");
                session.setAttribute(AirrentUtils.SEESION_ROLE_ID,airLine.getAirlineId());
            }
        }
        return jsonResult;
    }

    @RequestMapping("/index")
    public ModelAndView manageIndex(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Object role=session.getAttribute(AirrentUtils.SEESION_ROLE);
        if (role!=null) {
            mv.addObject("role", role);
        }
        return render(mv, "index");
    }

    @RequestMapping("/airports")
    public ModelAndView airports() {
        ModelAndView mv = new ModelAndView();
        return render(mv, "airports");
    }

    @RequestMapping("/airports/list")
    public ModelAndView airportList(@ModelAttribute OpenPage page,
                                    @RequestParam String airportName, @RequestParam String address) {
        ModelAndView mv = new ModelAndView();
        page = airportService.findAirportList(page, airportName, address);
        mv.addObject("page", page);
        return render(mv, "airportList");
    }

    @RequestMapping("/airports/add")
    public ModelAndView addAirport() {
        ModelAndView mv = new ModelAndView();
        return render(mv, "airportAdd");
    }

    @RequestMapping("/airports/delete")
    public
    @ResponseBody
    JsonResult deleteAirport(@RequestParam String airportId) throws Exception {
        JsonResult jsonResult = new JsonResult();
        String result = airportService.deleteAirprot(airportId);
        jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
        return jsonResult;
    }

    @RequestMapping(value = "/airports/save", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult saveAirport(@RequestParam String airportName, @RequestParam String address,
                            @RequestParam String lat,@RequestParam String lng) {
        JsonResult jsonResult = new JsonResult();
        Airport airport = new Airport();
        airport.setAirportName(airportName);
        airport.setAddress(address);
        airport.setDescription(address);
        airport.setLat(lat);
        airport.setLng(lng);
        String result = airportService.saveAirport(airport);
        jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
        return jsonResult;
    }

    @RequestMapping("/menu/{page}")
    public ModelAndView menuItem(@PathVariable String page) throws RemoteInvokeException {
        ModelAndView mv = new ModelAndView();
        if(page.equals("setting")) {
            Setting setting = settingService.loadSetting();
            mv.addObject("setting", setting);
        }
        return render(mv, page);
    }

    @RequestMapping("/{page}/add")
    public ModelAndView addPage(@PathVariable String page,
                                @RequestParam (required = false) String airlineId,
                                @RequestParam(required = false)String userId)throws Exception{
        ModelAndView mv = new ModelAndView();
        if (page.equals("airline")) {
            List<Airport> airports = airportService.findAllAirport();
            mv.addObject("airports", airports);
        }else if (page.equals("plane")) {
            mv.addObject("airlineId", airlineId);
        }else if(page.equals("user")) {
            mv.addObject("user", userService.getById(userId));
        }
        return render(mv, page + "Add");
    }

    /**
     * 机构管理列表
     *
     * @param page
     * @param airlineName
     * @param loginName
     * @return
     */
    @RequestMapping("/airlines/list")
    public ModelAndView airlineList(@ModelAttribute OpenPage page,
                                    @RequestParam String airlineName, @RequestParam String loginName) {
        ModelAndView mv = new ModelAndView();
        page = airlineService.findAirlineList(page, airlineName, loginName);
        mv.addObject("page", page);
        return render(mv, "airlineList");
    }

    /**
     * 查看公司机构
     *
     * @param airlineId
     * @return
     * @throws Exception
     */
    @RequestMapping("/menu/airline/view")
    public ModelAndView viewAirline(@RequestParam String airlineId) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Airport> airports = airportService.findAllAirport();
        mv.addObject("airports", airports);
        Airline airline = airlineService.findAirlineById(airlineId);
        if (StringUtils.isNotBlank(airline.getAirportId())) {
            airline.setAirportName(airportService.findAirportById(airline.getAirportId()).getAirportName());
        }
        mv.addObject("airline", airline);
        return render(mv, "airlineView");

    }

    /**
     * 新增公司机构
     *
     * @param request
     * @return
     */
    @RequestMapping("/airline/save")
    public
    @ResponseBody
    JsonResult saveAirline(MultipartHttpServletRequest request,HttpServletRequest httpServletRequest) throws Exception{
        JsonResult jsonResult = new JsonResult();
        MultipartFile file = request.getFile("airlineImage");
        String imgPath=saveFile(file, httpServletRequest);
        Airline airline = new Airline();
        airline.setAirlineId(request.getParameter("airlineId"));
        airline.setAirlineName(request.getParameter("airlineName"));
        airline.setAirlineImage(imgPath);
        airline.setAirlineImageName(file.getOriginalFilename());
        airline.setLoginName(request.getParameter("loginName"));
        airline.setAirportId(request.getParameter("airportId"));
        airline.setStatus(request.getParameter("status"));
        airline.setPassword(request.getParameter("password"));
        airline.setWeixin(request.getParameter("weixin"));
        airline.setAddress(request.getParameter("address"));
        airline.setLat(request.getParameter("lat"));
        airline.setPhone(request.getParameter("phone"));
        airline.setLng(request.getParameter("lng"));
        String result = airlineService.saveAirline(airline);
        jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
        return jsonResult;
    }

    /**
     * 管理公司机构 :飞机列表
     *
     * @param airlineId
     * @return
     * @throws Exception
     */
    @RequestMapping("/menu/airline/manage")
    public ModelAndView manageAirline(@RequestParam String airlineId) throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.addObject("airlineId", airlineId);
        mv.addObject("airlineName", airlineService.findAirlineById(airlineId).getAirlineName());
        return render(mv, "planes");
    }
    /**
     * 合作机构首页 :飞机列表
     *
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/menu/planes")
    public ModelAndView planes(HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView();
        Object sessionAirlineId = session.getAttribute(AirrentUtils.SEESION_ROLE_ID);
        if (sessionAirlineId != null) {
            String airlineId=sessionAirlineId.toString();
            mv.addObject("airlineId", airlineId);
            mv.addObject("airlineName", airlineService.findAirlineById(airlineId).getAirlineName());
        }
        return render(mv, "planes");
    }

    /**
     * 飞机列表数据
     * @param page
     * @param plane
     * @return
     */
    @RequestMapping("/planes/list")
    public ModelAndView planesList(@ModelAttribute OpenPage page,@ModelAttribute Plane plane) {
        ModelAndView mv = new ModelAndView();
        page = planeService.findPlaneList(page, plane);
        mv.addObject("page", page);
        return render(mv, "planeList");
    }

    /**
     * 新增飞机
     *
     * @param request
     * @return
     */
    @RequestMapping("/plane/save")
    public
    @ResponseBody
    JsonResult savePlane(MultipartHttpServletRequest request,HttpServletRequest httpServletRequest) throws Exception{
        JsonResult jsonResult = new JsonResult();
        MultipartFile file = request.getFile("planeImage");
        String imgPath=saveFile(file, httpServletRequest);
        Plane plane=new Plane();
        plane.setPlaneId(request.getParameter("planeId"));
        plane.setPlaneImage(imgPath);
        plane.setPlaneImageName(file.getOriginalFilename());
        plane.setAirlineId(request.getParameter("airlineId"));
        plane.setPlaneName(request.getParameter("planeName"));
        plane.setPlaneNo(request.getParameter("planeNo"));
        plane.setUnitCost(new BigDecimal(request.getParameter("unitCost")));
        plane.setPlaneType(request.getParameter("planeType"));
        plane.setProductArea(request.getParameter("productArea"));
        plane.setDrivingMile(new BigDecimal(request.getParameter("drivingMile")));
        plane.setPlanePrice(new BigDecimal(request.getParameter("planePrice")));
        plane.setSitCounts(request.getParameter("sitCounts"));
        plane.setTimeInProduct(request.getParameter("timeInProduct"));
        plane.setColour(request.getParameter("colour"));
        plane.setSpeed(new BigDecimal(request.getParameter("speed")));
        plane.setFlyUnitCost(new BigDecimal(request.getParameter("flyUnitCost")));
        plane.setShowUnitCost(new BigDecimal(request.getParameter("showUnitCost")));
        String result = planeService.savePlane(plane);
        jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
        return jsonResult;
    }

    @RequestMapping("/menu/plane/view")
    public ModelAndView planeView(@RequestParam String planeId) throws Exception{
        ModelAndView mv = new ModelAndView();
        Plane plane = planeService.findPlaneById(planeId);
        mv.addObject("plane", plane);
        return render(mv, "planeView");
    }
    /**
     * 飞机管理：修改状态、删除
     * @param opt
     * @param planeId
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/menu/plane/{opt}")
    public
    @ResponseBody
    JsonResult changePlane(@PathVariable String opt,
                             @RequestParam String planeId,
                             @RequestParam(required = false) String status)throws Exception{
        JsonResult jsonResult = new JsonResult();
        String result = "";
        if (opt.equals("change")) {
            Plane plane=new Plane();
            plane.setPlaneId(planeId);
            plane.setStatus(status);
            result=planeService.updatePlaneStatus(plane);
        } else if (opt.equals("delete")) {
            result=planeService.deletePlane(planeId);
        }
        jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
        return jsonResult;
    }

    /**
     * 机构管理：修改状态、删除
     * @param opt
     * @param airlineId
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/menu/airline/{opt}")
    public
    @ResponseBody
    JsonResult changeAirline(@PathVariable String opt,
                             @RequestParam String airlineId,
                             @RequestParam(required = false) String status)throws Exception{
        JsonResult jsonResult = new JsonResult();
        String result = "";
        if (opt.equals("change")) {
            Airline airline=new Airline();
            airline.setAirlineId(airlineId);
            airline.setStatus(status);
            result=airlineService.updateAirline(airline);
        } else if (opt.equals("delete")) {
            result=airlineService.deleteAirline(airlineId);
        }
        jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
        return jsonResult;
    }

    @RequestMapping("/user/list")
    public ModelAndView userList(@ModelAttribute OpenPage page,@ModelAttribute UserInfo userInfo) {
        ModelAndView mv = new ModelAndView();
        page = userService.findUserByPage(page, userInfo);
        mv.addObject("page", page);
        return render(mv, "userList");
    }

    @RequestMapping("/user/save")
    public  @ResponseBody JsonResult userSave(@ModelAttribute UserInfo user) {
        JsonResult jsonResult = new JsonResult();
        user.setAvatar("");
        String result = userService.saveUser(user);
        jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
        return jsonResult;
    }

    /**
     * 会员管理：修改状态、删除
     * @param opt
     * @param userId
     * @param status
     * @return
     */
    @RequestMapping("/menu/user/{opt}")
    public
    @ResponseBody
    JsonResult changeUser(@PathVariable String opt,
                           @RequestParam String userId,
                           @RequestParam(required = false) String status){
        JsonResult jsonResult = new JsonResult();
        String result = "";
        if (opt.equals("change")) {
            UserInfo user=new UserInfo();
            user.setUserId(userId);
            user.setStatus(status);
            result=userService.updateUserStatus(user);
        } else if (opt.equals("delete")) {
            result = userService.deleteUser(userId);
        }
        jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
        return jsonResult;
    }

    @RequestMapping("/userOrder/list")
    public ModelAndView userOrderList(@ModelAttribute OpenPage page,
                                      @ModelAttribute UserOrder userOrder,
                                      HttpSession session) throws RemoteInvokeException {
        ModelAndView mv = new ModelAndView();
        Object sessionAirlineId = session.getAttribute(AirrentUtils.SEESION_ROLE_ID);
        if (sessionAirlineId != null) {
            String airlineId=sessionAirlineId.toString();
            Airline airline = airlineService.findAirlineById(airlineId);
            userOrder.setAirportId(airline.getAirportId());
        }
        page = userOrderService.findUserOrders(page, userOrder);
        mv.addObject("page", page);
        return render(mv, "userOrderList");
    }

    @RequestMapping("/userOrder/view")
    public ModelAndView userOrderView(@RequestParam String orderId)throws Exception{
        ModelAndView mv = new ModelAndView();
        UserOrder userOrder = userOrderService.findOrderById(orderId);
        userOrder.setUserInfo(userService.getById(userOrder.getUserId()));
        Plane plane=planeService.findPlaneById(userOrder.getPlaneId());
        userOrder.setPlane(plane);
        userOrder.setAirport(airportService.findAirportById(userOrder.getAirportId()));
        userOrder.setAirline(airlineService.findAirlineById(plane.getAirlineId()));
        mv.addObject("userOrder", userOrder);
        return render(mv, "userOrderView");
    }
    
    @RequestMapping("/userOrder/saveOrderStatus")
    public @ResponseBody JsonResult saveOrderStatus(@RequestParam String orderId
    												,@RequestParam String orderStatus) {
    	JsonResult jsonResult = new JsonResult();
    	String result=userOrderService.updateOrderStatus(orderId,orderStatus);
    	jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
    	return jsonResult;
    }

    @RequestMapping("/setting/save")
    public
    @ResponseBody
    JsonResult saveSetting(@ModelAttribute Setting setting) throws RemoteInvokeException {
        JsonResult jsonResult = new JsonResult();
        String result=settingService.saveSetting(setting);
        jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
        return jsonResult;
    }

    @RequestMapping("resetPass")
    public @ResponseBody JsonResult resetPass(@RequestParam String originalPass,
                                 @RequestParam String changePass,
                                 HttpSession session) throws Exception {
        JsonResult jsonResult = new JsonResult();
        Object role=session.getAttribute(AirrentUtils.SEESION_ROLE);
        Object id=session.getAttribute(AirrentUtils.SEESION_ROLE_ID);
        String result = "";
        if (role!=null) {
            if (role.equals("ADMIN")) {
                UserInfo userInfo = userService.getById(id.toString());
                if (!userInfo.getPassword().equals(DigestUtils.md5Hex(originalPass))) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMessage("原密码不正确");
                    return jsonResult;
                }
                result = userService.resetPasswordById(id.toString(), changePass);
            }else {
                Airline airline = airlineService.findAirlineById(id.toString());
                if (airline.getPassword().equals(originalPass)) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMessage("原密码不正确");
                    return jsonResult;
                }
                result = airlineService.resetPassword(id.toString(), changePass);
            }
        }
        jsonResult.setSuccess(StringUtils.equals("SUCCESS", result));
        return jsonResult;
    }

    @RequestMapping("editor")
    public ModelAndView editor(@RequestParam String editorId,HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.addObject("ctx",request.getContextPath());
        mv.addObject("editorId",editorId);
        return render(mv, "editor");
    }

    @RequestMapping("exit")
    public ModelAndView exit(HttpSession session){
        ModelAndView mv = new ModelAndView();
        session.removeAttribute(AirrentUtils.SEESION_ROLE);
        session.removeAttribute(AirrentUtils.SEESION_ROLE_ID);
        return render(mv, "login");
    }
    
    @RequestMapping("/remote/savefile")
    public String saveRemoteFile(MultipartHttpServletRequest request,HttpServletRequest httpServletRequest) {
    	MultipartFile file = request.getFile("file");
        String originalFilename=file.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.indexOf("."),originalFilename.length());

        String dirname = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String upload = httpServletRequest.getSession().getServletContext().getRealPath("upload");
        String filePath=dirname+File.separator+CommonUtils.genUUID()+fileSuffix;

        File targetFile = new File(upload+File.separator+filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return host+"/upload/"+filePath;
    }

    @RequestMapping("/editor/uploadImg")
    public @ResponseBody String uploadImg(MultipartFile filedata,HttpServletRequest request) {
        String fileurl=saveFile(filedata, request);
        String json = String.format("{'err':'%s',msg:{'url':'%s','localname':'%s','id':'%s'}}",
                "", fileurl, filedata.getOriginalFilename(), fileurl);
        return json;
    }
    
    private String saveFile(MultipartFile file,HttpServletRequest httpServletRequest) {
        String originalFilename=file.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.indexOf("."),originalFilename.length());

        String dirname = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String upload = httpServletRequest.getSession().getServletContext().getRealPath("upload");
        String filePath=dirname+File.separator+CommonUtils.genUUID()+fileSuffix;

        File targetFile = new File(upload+File.separator+filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return host+"/upload/"+filePath;
    }

    private ModelAndView render(ModelAndView mv,String viewName) {
        mv.setViewName("manage/"+viewName);
        return mv;
    }
}
