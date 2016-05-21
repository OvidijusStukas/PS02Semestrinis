package com.cosisolutions.wms.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"errors"})
public class ErrorController {

  @RequestMapping(value = {"403"}, method = RequestMethod.GET)
  public ModelAndView error403() {
    return new ModelAndView("errors/403");
  }
}
