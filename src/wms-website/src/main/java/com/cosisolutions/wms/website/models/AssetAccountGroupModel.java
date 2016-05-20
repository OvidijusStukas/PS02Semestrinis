package com.cosisolutions.wms.website.models;

public class AssetAccountGroupModel {

  private int id;
  private String role;
  private AssetModel asset;
  private AccountModel account;

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

  public AssetModel getAsset() {
    return asset;
  }

  public void setAsset(AssetModel asset) {
    this.asset = asset;
  }

  public AccountModel getAccount() {
    return account;
  }

  public void setAccount(AccountModel account) {
    this.account = account;
  }
}
