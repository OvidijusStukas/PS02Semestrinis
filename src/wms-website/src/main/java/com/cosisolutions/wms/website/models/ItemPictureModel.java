package com.cosisolutions.wms.website.models;

public class ItemPictureModel {

  private int id;
  private String data;
  private ItemModel item;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public ItemModel getItem() {
    return item;
  }

  public void setItem(ItemModel item) {
    this.item = item;
  }
}
