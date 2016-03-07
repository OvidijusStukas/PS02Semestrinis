package com.cosisolutions.wms.website.controller;

import com.cosisolutions.wms.website.helpers.AccountHelper;
import com.cosisolutions.wms.website.models.UserRegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by CosISolutions on 2016-03-07.
 */
@Controller
@RequestMapping(value = "/accounts")
public class AccountsController {

    @Autowired
    private AccountHelper accountHelper;

    @RequestMapping(value = {"login","/"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("accounts/login");
    }

    @RequestMapping(value = {"registration"}, method = RequestMethod.GET)
    public String registration(Model model) {
        UserRegisterModel userModel = new UserRegisterModel();
        model.addAttribute("userRegisterModel",userModel);

        return "accounts/registration";
    }

    @RequestMapping(value = {"registration"}, method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("userRegisterModel") UserRegisterModel userModel) {
        if(accountHelper.validateRegisterUser(userModel) == AccountHelper.ValidationResult.SUCCESS) {
            accountHelper.registerUser(userModel);
            return new ModelAndView("accounts/login"); //TODO should redirect to message that VALIDATION EMAIL SENT
        }
        return new ModelAndView("accounts/registration");
    }

    @ResponseBody
    @RequestMapping(value = {"registration/validate"}, method = RequestMethod.GET, produces = "application/json")
    public String checkRegistrationEmail(@RequestParam("email") String email) {
        if(email == null || email.isEmpty() || !accountHelper.isEmailAvailable(email))
            return "false";

        return "true";
    }

    // Login form with error
    @RequestMapping("login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);

        return "accounts/login";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(){
        return "error/404.html";
    }
}