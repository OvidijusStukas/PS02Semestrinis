package com.cosisolutions.wms.website.controller;

import com.cosisolutions.wms.website.entity.AssetEntity;
import com.cosisolutions.wms.website.entity.ItemEntity;
import com.cosisolutions.wms.website.entity.ItemGroupEntity;
import com.cosisolutions.wms.website.entity.ItemPictureEntity;
import com.cosisolutions.wms.website.factory.AssetFactory;
import com.cosisolutions.wms.website.mapper.AssetMapper;
import com.cosisolutions.wms.website.mapper.ItemGroupMapper;
import com.cosisolutions.wms.website.mapper.ItemMapper;
import com.cosisolutions.wms.website.models.AssetModel;
import com.cosisolutions.wms.website.models.ItemGroupModel;
import com.cosisolutions.wms.website.models.ItemModel;
import com.cosisolutions.wms.website.repository.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = {"/items"})
public class ItemController {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private AssetFactory assetFactory;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private ItemGroupMapper itemGroupMapper;
    @Autowired
    private ItemGroupRepository itemGroupRepository;
    @Autowired
    private ItemPictureRepository itemPictureRepository;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView index(@RequestParam("id") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AssetEntity assetEntity = assetRepository.getEntity(AssetEntity.class, id);

        // Trying to load other user asset
        if(!assetEntity.getAccount().getEmail().equals(auth.getName())) {
            return new ModelAndView("errors/403");
        }

        AssetModel assetModel = new AssetModel();
        assetMapper.toModel(assetModel, assetEntity);

        List<ItemEntity> items = itemRepository.getEntities(assetEntity);
        items.stream().forEach(item -> {
            item.setPicture(itemPictureRepository.getEntity(item));
        });

        ModelAndView modelAndView = new ModelAndView("items/dashboard");
        modelAndView.addObject("model", assetModel);
        modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());
        modelAndView.addObject("groups", itemGroupRepository.getEntities(assetEntity));
        modelAndView.addObject("items", items);
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"add"}, method = RequestMethod.GET)
    public ModelAndView add(@RequestParam("assetId") Integer assetId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AssetEntity assetEntity = assetRepository.getEntity(AssetEntity.class, assetId);

        // Trying to load other user asset
        if(!assetEntity.getAccount().getEmail().equals(auth.getName())) {
            return new ModelAndView("errors/403");
        }

        ModelAndView modelAndView = new ModelAndView("items/add");
        modelAndView.addObject("model", new ItemModel());
        modelAndView.addObject("assetId", assetId);
        modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());
        modelAndView.addObject("groups", itemGroupRepository.getEntities(assetEntity));
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"add"}, method = RequestMethod.POST)
    public ModelAndView add(@RequestParam("assetId") Integer assetId, @RequestParam("groupId") Integer groupId,
                            @RequestParam("picturebase64") String base64, @RequestParam("picturetype") String type,
                            @ModelAttribute("model") ItemModel model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AssetEntity assetEntity = assetRepository.getEntity(AssetEntity.class, assetId);
        ItemGroupEntity groupEntity = itemGroupRepository.getEntity(ItemGroupEntity.class, groupId);
        // Trying to load other user asset
        if(!assetEntity.getAccount().getEmail().equals(auth.getName()) || groupEntity.getAsset().getId() != assetEntity.getId()) {
            return new ModelAndView("errors/403");
        }

        String pictureData = String.format("data:%s;base64,%s", type, base64);
        try {
            ItemEntity itemEntity = new ItemEntity();
            itemMapper.toEntity(itemEntity, model);
            itemEntity.setAsset(assetEntity);
            itemEntity.setGroup(groupEntity);
            Integer id = itemRepository.insertEntity(itemEntity);
            itemEntity = itemRepository.getEntity(ItemEntity.class, id);
            ItemPictureEntity pictureEntity = new ItemPictureEntity();
            pictureEntity.setData(pictureData);
            pictureEntity.setItem(itemEntity);
            itemPictureRepository.insertEntity(pictureEntity);
        } catch (HibernateException e) {
            ModelAndView modelAndView = new ModelAndView("items/add");
            modelAndView.addObject("model", model);
            modelAndView.addObject("assetId", assetId);
            modelAndView.addObject("assets", assetFactory.createAssetModelsForUser());
            return modelAndView;
        }

        String route = String.format("redirect:/items?id=%d", assetId);
        return new ModelAndView(route);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"addGroup"}, method = RequestMethod.GET)
    public ModelAndView addGroup(@RequestParam("assetId") Integer assetId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AssetEntity assetEntity = assetRepository.getEntity(AssetEntity.class, assetId);

        // Trying to load other user asset
        if(!assetEntity.getAccount().getEmail().equals(auth.getName())) {
            return new ModelAndView("errors/403");
        }

        ModelAndView modelAndView = new ModelAndView("items/add-item-group");
        modelAndView.addObject("model", new ItemGroupModel());
        modelAndView.addObject("assetId", assetId);
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"addGroup"}, method = RequestMethod.POST)
    public ModelAndView addGroup(@RequestParam("assetId") Integer assetId, @ModelAttribute("model") ItemGroupModel model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AssetEntity assetEntity = assetRepository.getEntity(AssetEntity.class, assetId);

        // Trying to load other user asset
        if(!assetEntity.getAccount().getEmail().equals(auth.getName())) {
            return new ModelAndView("errors/403");
        }

        try {
            ItemGroupEntity itemGroupEntity = new ItemGroupEntity();
            itemGroupMapper.toEntity(itemGroupEntity, model);
            itemGroupEntity.setAsset(assetEntity);
            itemGroupRepository.insertEntity(itemGroupEntity);
        } catch (HibernateException e) {
            ModelAndView modelAndView = new ModelAndView("items/add-item-group");
            modelAndView.addObject("model", model);
            modelAndView.addObject("assetId", assetId);
            return modelAndView;
        }

        String route = String.format("redirect:/items?id=%d", assetId);
        return new ModelAndView(route);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"editGroup"}, method = RequestMethod.GET)
    public ModelAndView editGroup(@RequestParam("groupId") Integer groupId) {
        ItemGroupEntity entity = itemGroupRepository.getEntity(ItemGroupEntity.class, groupId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AssetEntity assetEntity = assetRepository.getEntity(AssetEntity.class, entity.getAsset().getId());

        // Trying to load other user asset
        if(!assetEntity.getAccount().getEmail().equals(auth.getName())) {
            return new ModelAndView("errors/403");
        }

        ItemGroupModel model = new ItemGroupModel();
        itemGroupMapper.toModel(model, entity);

        ModelAndView modelAndView = new ModelAndView("items/edit-item-group");
        modelAndView.addObject("model", model);
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"editGroup"}, method = RequestMethod.POST)
    public ModelAndView editGroup(@ModelAttribute("model") ItemGroupModel model) {
        ItemGroupEntity entity = itemGroupRepository.getEntity(ItemGroupEntity.class, model.getId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AssetEntity assetEntity = assetRepository.getEntity(AssetEntity.class, entity.getAsset().getId());

        // Trying to load other user asset
        if(!assetEntity.getAccount().getEmail().equals(auth.getName())) {
            return new ModelAndView("errors/403");
        }

        try {
            itemGroupMapper.toEntity(entity, model);
            itemGroupRepository.updateEntity(entity);
        } catch (HibernateException e) {
            ModelAndView modelAndView = new ModelAndView("items/add-item-group");
            modelAndView.addObject("model", model);
            return modelAndView;
        }

        String route = String.format("redirect:/items?id=%d", assetEntity.getId());
        return new ModelAndView(route);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    @RequestMapping(value = {"removeGroup"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String removeGroup(@RequestParam("groupId") Integer groupId) {
        ItemGroupEntity entity = itemGroupRepository.getEntity(ItemGroupEntity.class, groupId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AssetEntity assetEntity = assetRepository.getEntity(AssetEntity.class, entity.getAsset().getId());

        // Trying to load other user asset
        if(!assetEntity.getAccount().getEmail().equals(auth.getName())) {
            return "false";
        }

        try {
            itemGroupRepository.deleteEntity(ItemGroupEntity.class, groupId);
        } catch (HibernateException e) {
            return "false";
        }
        return "true";
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    @RequestMapping(value = {"remove"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String remove(@RequestParam("id") Integer id) {
        ItemEntity entity = itemRepository.getEntity(ItemEntity.class, id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AssetEntity assetEntity = assetRepository.getEntity(AssetEntity.class, entity.getAsset().getId());

        // Trying to load other user asset
        if(!assetEntity.getAccount().getEmail().equals(auth.getName())) {
            return "false";
        }

        try {
            itemRepository.deleteEntity(ItemEntity.class, id);
        } catch (HibernateException e) {
            return "false";
        }
        return "true";
    }
}
