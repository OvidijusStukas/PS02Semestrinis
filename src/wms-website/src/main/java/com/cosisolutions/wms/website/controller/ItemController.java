package com.cosisolutions.wms.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by CosISolutions on 2016-05-21.
 */

@Controller
@RequestMapping(value = {"/items"})
public class ItemController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("items/dashboard");
    }
}
