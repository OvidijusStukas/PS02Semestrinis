package com.cosisolutions.wms.website.entity;

import java.io.Serializable;

public class AssetAccountGroupEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String role;
  private AssetEntity asset;
  private AccountEntity account;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public AssetEntity getAsset() {
    return asset;
  }

  public void setAsset(AssetEntity asset) {
    this.asset = asset;
  }

  public AccountEntity getAccount() {
    return account;
  }

  public void setAccount(AccountEntity account) {
    this.account = account;
  }
}
