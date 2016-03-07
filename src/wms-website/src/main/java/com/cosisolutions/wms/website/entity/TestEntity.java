package com.cosisolutions.wms.website.entity;

import java.io.Serializable;

/**
 * Created by Ovidijus Stukas on 3/7/2016.
 * For CosISolutions
 */
public class TestEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
