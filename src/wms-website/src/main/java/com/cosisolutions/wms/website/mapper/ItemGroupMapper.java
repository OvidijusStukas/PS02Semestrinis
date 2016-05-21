package com.cosisolutions.wms.website.mapper;

import com.cosisolutions.wms.website.entity.ItemGroupEntity;
import com.cosisolutions.wms.website.models.ItemGroupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemGroupMapper implements IMapper<ItemGroupEntity, ItemGroupModel> {
  @Autowired
  private AssetMapper assetMapper;

  @Override
  public void toModel(ItemGroupModel itemGroupModel, ItemGroupEntity itemGroupEntity) {
    if(itemGroupEntity == null) return;

    itemGroupModel.setId(itemGroupEntity.getId());
    itemGroupModel.setName(itemGroupEntity.getName());

    assetMapper.toModel(itemGroupModel.getAsset(), itemGroupEntity.getAsset());
  }

  @Override
  public void toEntity(ItemGroupEntity itemGroupEntity, ItemGroupModel itemGroupModel) {
    if(itemGroupModel == null) return;

    itemGroupEntity.setName(itemGroupModel.getName());

    if(itemGroupModel.getId() > 0)
      itemGroupEntity.setId(itemGroupModel.getId());

    assetMapper.toEntity(itemGroupEntity.getAsset(), itemGroupModel.getAsset());
  }
}
