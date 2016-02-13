package com.cosisolutions.wms.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ovidijus Stukas on 2/13/2016.
 * For CosISolutions
 */
@Controller
@RequestMapping(value = {"/"})
public class HomeController {

    @ResponseBody
    @RequestMapping(value = {"", "/test"}, method = RequestMethod.GET)
    public String test() {
        return "Website is running";
    }
}
