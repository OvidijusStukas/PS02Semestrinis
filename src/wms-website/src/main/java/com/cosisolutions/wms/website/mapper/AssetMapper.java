package com.cosisolutions.wms.website.mapper;

import com.cosisolutions.wms.website.entity.AssetEntity;
import com.cosisolutions.wms.website.models.AssetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetMapper implements IMapper<AssetEntity, AssetModel> {
  @Autowired
  private AccountMapper accountMapper;

  @Override
  public void toModel(AssetModel assetModel, AssetEntity assetEntity) {
    if(assetEntity == null) return;

    assetModel.setId(assetEntity.getId());
    assetModel.setCode(assetEntity.getCode());
    assetModel.setName(assetEntity.getName());
    assetModel.setAddress(assetEntity.getAddress());
    assetModel.setDescription(assetEntity.getDescription());

    accountMapper.toModel(assetModel.getAccount(), assetEntity.getAccount());
  }

  @Override
  public void toEntity(AssetEntity assetEntity, AssetModel assetModel) {
    if(assetModel == null) return;

    assetEntity.setCode(assetModel.getCode());
    assetEntity.setName(assetModel.getName());
    assetEntity.setAddress(assetModel.getAddress());
    assetEntity.setDescription(assetModel.getDescription());

    if(assetModel.getId() > 0)
      assetEntity.setId(assetModel.getId());

    accountMapper.toEntity(assetEntity.getAccount(), assetModel.getAccount());
  }
}
