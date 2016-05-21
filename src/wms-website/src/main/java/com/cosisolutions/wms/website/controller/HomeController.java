package com.cosisolutions.wms.website.controller;

import com.cosisolutions.wms.website.factory.AssetFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/"})
public class HomeController {
    @Autowired
    private AssetFactory assetFactory;

    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("home/index");
        modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView("home/dashboard");
        modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());
        return modelAndView;
    }
}
