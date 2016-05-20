package com.cosisolutions.wms.website.controller;

import com.cosisolutions.wms.website.entity.AccountEntity;
import com.cosisolutions.wms.website.mapper.AccountMapper;
import com.cosisolutions.wms.website.models.AccountModel;
import com.cosisolutions.wms.website.repository.BaseRepository;
import com.cosisolutions.wms.website.validator.AccountModelValidator;
import com.cosisolutions.wms.website.validator.RegistrationValidator;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/accounts")
public class AccountsController {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AccountModelValidator accountModelValidator;
    @Autowired
    private RegistrationValidator registrationValidator;
    @Autowired
    private BaseRepository<AccountEntity> accountRepository;

    @RequestMapping(value = {"","/", "login"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("accounts/login");
    }

    @RequestMapping(value = {"registration"}, method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("accounts/registration");
        modelAndView.addObject("model", new AccountModel());
        return modelAndView;
    }

    @RequestMapping(value = {"registration"}, method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("model") AccountModel accountModel,
                                     BindingResult result, SessionStatus status) {

        accountModelValidator.validate(accountModel, result);
        ModelAndView modelAndView = new ModelAndView("accounts/registration");
        modelAndView.addObject("model", accountModel);

        if(result.hasErrors()) {
            accountModel.setPassword("");
            accountModel.setPasswordRepeat("");
            return modelAndView;
        }

        try {
            AccountEntity accountEntity = new AccountEntity();
            accountMapper.ToEntity(accountEntity, accountModel);
            accountEntity.setActive(true);
            accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
            accountRepository.insertEntity(accountEntity);
        } catch (HibernateException e) {
            return modelAndView;
        }

        status.setComplete();
        return new ModelAndView("accounts/login");
    }

    @ResponseBody
    @RequestMapping(value = {"registration/validation"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String registrationValidation(@RequestParam(value = "email") String email) {
        return String.valueOf(StringUtils.isBlank(email) || !registrationValidator.isEmailAlreadyTaken(email));
    }
}