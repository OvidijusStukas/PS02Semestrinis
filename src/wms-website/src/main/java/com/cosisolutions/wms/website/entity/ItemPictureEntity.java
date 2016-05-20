package com.cosisolutions.wms.website.entity;

import java.io.Serializable;

public class ItemPictureEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String data;
    private ItemEntity item;

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

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }
}
