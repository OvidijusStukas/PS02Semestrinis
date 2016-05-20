package com.cosisolutions.wms.website.models;

public class ItemGroupModel {

  private int id;
  private String name;
  private AssetModel asset;

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

  public AssetModel getAsset() {
    return asset;
  }

  public void setAsset(AssetModel asset) {
    this.asset = asset;
  }
}
