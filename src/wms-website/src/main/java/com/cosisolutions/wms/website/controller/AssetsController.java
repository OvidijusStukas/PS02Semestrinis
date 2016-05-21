package com.cosisolutions.wms.website.controller;

import com.cosisolutions.wms.website.entity.AccountEntity;
import com.cosisolutions.wms.website.entity.AssetEntity;
import com.cosisolutions.wms.website.factory.AssetFactory;
import com.cosisolutions.wms.website.mapper.AssetMapper;
import com.cosisolutions.wms.website.models.AssetModel;
import com.cosisolutions.wms.website.repository.AccountRepository;
import com.cosisolutions.wms.website.repository.BaseRepository;
import com.cosisolutions.wms.website.validator.AssetModelValidator;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/assets"})
public class AssetsController {
    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private AssetFactory assetFactory;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AssetModelValidator assetModelValidator;
    @Autowired
    private BaseRepository<AssetEntity> assetRepository;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"add"}, method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("assets/add");
        modelAndView.addObject("model", new AssetModel());
        modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"add"}, method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("model") AssetModel model,
                            BindingResult result, SessionStatus status) {
        assetModelValidator.validate(model, result);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model", model);
        modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());

        if(result.hasErrors()) {
            return modelAndView;
        }

        //Get current user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountEntity accountEntity = accountRepository.findUserByEmail(auth.getName());

        Integer id;
        try {
            AssetEntity entity = new AssetEntity();
            assetMapper.toEntity(entity, model);
            entity.setAccount(accountEntity);
            id = assetRepository.insertEntity(entity);
        } catch (HibernateException e) {
            return modelAndView;
        }

        status.setComplete();
        String route = String.format("redirect:/items?id=%d", id);
        return new ModelAndView(route);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"edit"}, method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam("id") Integer id) {
        AssetEntity entity = assetRepository.getEntity(AssetEntity.class, id);
        AssetModel model = new AssetModel();
        assetMapper.toModel(model, entity);

        ModelAndView modelAndView = new ModelAndView("assets/edit");
        modelAndView.addObject("model", model);
        modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"edit"}, method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute("model") AssetModel model,
                            BindingResult result, SessionStatus status) {
        assetModelValidator.validate(model, result);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model", model);
        modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());

        if(result.hasErrors()) {
            return  modelAndView;
        }

        //Get current user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountEntity accountEntity = accountRepository.findUserByEmail(auth.getName());

        try {
            AssetEntity entity = new AssetEntity();
            assetMapper.toEntity(entity, model);
            entity.setAccount(accountEntity);
            assetRepository.updateEntity(entity);
        } catch (HibernateException e) {
            return modelAndView;
        }

        status.setComplete();
        String route = String.format("redirect:/items?id=%d", model.getId());
        return new ModelAndView(route);
    }

}
