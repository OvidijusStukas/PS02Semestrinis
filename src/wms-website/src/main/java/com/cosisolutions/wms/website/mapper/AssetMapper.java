package com.cosisolutions.wms.website.mapper;

import com.cosisolutions.wms.website.entity.AccountEntity;
import com.cosisolutions.wms.website.entity.AssetEntity;
import com.cosisolutions.wms.website.models.AccountModel;
import com.cosisolutions.wms.website.models.AssetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper implements IMapper<AssetEntity, AssetModel> {
  @Autowired
  private AccountMapper accountMapper;

  @Override
  public void toModel(AssetModel model, AssetEntity entity) {
    if(model == null || entity == null) return;

    model.setId(entity.getId());
    model.setCode(entity.getCode());
    model.setName(entity.getName());
    model.setAddress(entity.getAddress());
    model.setDescription(entity.getDescription());

    if(model.getAccount() == null)
      model.setAccount(new AccountModel());
    accountMapper.toModel(model.getAccount(), entity.getAccount());
  }

  @Override
  public void toEntity(AssetEntity entity, AssetModel assetModel) {
    if(entity == null || assetModel == null) return;

    entity.setCode(assetModel.getCode());
    entity.setName(assetModel.getName());
    entity.setAddress(assetModel.getAddress());
    entity.setDescription(assetModel.getDescription());

    if(assetModel.getId() > 0)
      entity.setId(assetModel.getId());

    if(entity.getAccount() == null)
      entity.setAccount(new AccountEntity());
    accountMapper.toEntity(entity.getAccount(), assetModel.getAccount());
  }
}
