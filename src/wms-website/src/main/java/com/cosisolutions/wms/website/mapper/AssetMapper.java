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

    if(model.getAccount() == null && entity.getAccount() != null)
      model.setAccount(new AccountModel());
    accountMapper.toModel(model.getAccount(), entity.getAccount());
  }

  @Override
  public void toEntity(AssetEntity entity, AssetModel model) {
    if(entity == null || model == null) return;

    entity.setCode(model.getCode());
    entity.setName(model.getName());
    entity.setAddress(model.getAddress());
    entity.setDescription(model.getDescription());

    if(model.getId() > 0)
      entity.setId(model.getId());

    if(entity.getAccount() == null && model.getAccount() != null)
      entity.setAccount(new AccountEntity());
    accountMapper.toEntity(entity.getAccount(), model.getAccount());
  }
}
