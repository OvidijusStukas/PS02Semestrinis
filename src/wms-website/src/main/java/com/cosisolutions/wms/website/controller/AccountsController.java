package com.cosisolutions.wms.website.controller;

import com.cosisolutions.wms.website.entity.AccountEntity;
import com.cosisolutions.wms.website.factory.AssetFactory;
import com.cosisolutions.wms.website.mapper.AccountMapper;
import com.cosisolutions.wms.website.models.AccountModel;
import com.cosisolutions.wms.website.repository.AccountRepository;
import com.cosisolutions.wms.website.validator.AccountEditValidator;
import com.cosisolutions.wms.website.validator.AccountModelValidator;
import com.cosisolutions.wms.website.validator.RegistrationValidator;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AssetFactory assetFactory;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AccountEditValidator accountEditValidator;
    @Autowired
    private AccountModelValidator accountModelValidator;
    @Autowired
    private RegistrationValidator registrationValidator;

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = {"","/", "login"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("accounts/login");
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"edit"}, method = RequestMethod.GET)
    public ModelAndView edit() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountEntity entity = accountRepository.findUserByEmail(auth.getName());

        AccountModel model = new AccountModel();
        accountMapper.toModel(model, entity);

        ModelAndView modelAndView = new ModelAndView("accounts/edit");
        modelAndView.addObject("model", model);
        modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());

        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"edit"}, method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute("model") AccountModel model,
                             BindingResult result, SessionStatus status) {
        accountEditValidator.validate(model, result);


        if(result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("accounts/edit");
            modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());
            modelAndView.addObject("model", model);
            return modelAndView;
        }

        AccountEntity entity = accountRepository.getEntity(AccountEntity.class, model.getId());
        if(entity != null) {
            entity.setFirstName(model.getFirstName());
            entity.setLastName(model.getLastName());
            accountRepository.updateEntity(entity);
        }

        status.setComplete();
        ModelAndView modelAndView = new ModelAndView("home/index");
        modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());
        return modelAndView;
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = {"registration"}, method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("accounts/registration");
        modelAndView.addObject("model", new AccountModel());
        return modelAndView;
    }

    @PreAuthorize("isAnonymous()")
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
            accountMapper.toEntity(accountEntity, accountModel);
            accountEntity.setActive(true);
            accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
            accountRepository.insertEntity(accountEntity);
        } catch (HibernateException e) {
            return modelAndView;
        }

        status.setComplete();
        return new ModelAndView("accounts/login");
    }

    @PreAuthorize("isAnonymous()")
    @ResponseBody
    @RequestMapping(value = {"registration/validation"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String registrationValidation(@RequestParam(value = "email") String email) {
        return String.valueOf(StringUtils.isBlank(email) || !registrationValidator.isEmailAlreadyTaken(email));
    }
}