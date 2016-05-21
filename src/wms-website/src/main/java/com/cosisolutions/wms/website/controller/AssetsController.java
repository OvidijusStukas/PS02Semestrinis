package com.cosisolutions.wms.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/assets"})
public class AssetsController {

    @RequestMapping(value = {"add"}, method = RequestMethod.GET)
    public ModelAndView add() {

        return new ModelAndView("assets/add");
    }
}
