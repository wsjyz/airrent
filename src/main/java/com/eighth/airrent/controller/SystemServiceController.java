package com.eighth.airrent.controller;


import com.eighth.airrent.domain.APKVersion;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/SystemService")
public class SystemServiceController {
    @Autowired
    SystemService systemService;

    @ResponseBody
    @RequestMapping(value = "/updateAPK")
    public APKVersion updateAPK(@RequestParam String currentVersionCode) {
        APKVersion aPKVersion = null;
        try {
            aPKVersion = systemService.updateAPK(currentVersionCode);
        } catch (RemoteInvokeException e) {
            e.printStackTrace();
        }
        return aPKVersion;
    }
}
