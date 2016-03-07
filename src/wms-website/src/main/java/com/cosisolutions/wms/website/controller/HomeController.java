package com.cosisolutions.wms.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Ovidijus Stukas on 2/13/2016.
 * For CosISolutions
 */
@Controller
@RequestMapping(value = {"/"})
public class HomeController {

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("home/index");
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView Dashboard() {

        return new ModelAndView("home/dashboard");
    }
}
