package com.cosisolutions.wms.website.entity;

import java.io.Serializable;

public class ItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int count;
    private String code;
    private String name;
    private String description;
    private AssetEntity asset;
    private ItemGroupEntity group;
    private ItemPictureEntity picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AssetEntity getAsset() {
        return asset;
    }

    public void setAsset(AssetEntity asset) {
        this.asset = asset;
    }

    public ItemGroupEntity getGroup() {
      return group;
    }

    public void setGroup(ItemGroupEntity group) {
      this.group = group;
    }

    public ItemPictureEntity getPicture() {
        return picture;
    }

    public void setPicture(ItemPictureEntity picture) {
        this.picture = picture;
    }
}
