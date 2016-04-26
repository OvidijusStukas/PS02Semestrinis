package com.cosisolutions.wms.website.controller;

import com.cosisolutions.wms.website.models.AssetModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by CosISolutions on 2016-04-26.
 */

@Controller
@RequestMapping(value = {"/Assets"})
public class AssetsController {



    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("assets/management");
    }
}
