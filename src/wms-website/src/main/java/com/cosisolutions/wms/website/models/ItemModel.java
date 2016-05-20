package com.cosisolutions.wms.website.models;

public class ItemModel {

  private int id;
  private int count;
  private String code;
  private String name;
  private String description;
  private AssetModel asset;
  private ItemGroupModel group;

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

  public AssetModel getAsset() {
    return asset;
  }

  public void setAsset(AssetModel asset) {
    this.asset = asset;
  }

  public ItemGroupModel getGroup() {
    return group;
  }

  public void setGroup(ItemGroupModel group) {
    this.group = group;
  }
}
