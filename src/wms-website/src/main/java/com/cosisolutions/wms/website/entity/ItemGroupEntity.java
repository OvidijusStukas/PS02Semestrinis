package com.cosisolutions.wms.website.entity;

import java.io.Serializable;

public class ItemGroupEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String name;
  private AssetEntity asset;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AssetEntity getAsset() {
    return asset;
  }

  public void setAsset(AssetEntity asset) {
    this.asset = asset;
  }
}
