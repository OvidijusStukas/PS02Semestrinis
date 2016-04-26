package com.cosisolutions.wms.website.entity;

import java.io.Serializable;

/**
 * Created by CosISolutions on 2016-04-26.
 */
public class AssetEntity implements Serializable {

    private int id;
    private String Name;
    private int Type;
    private String Adress;

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
