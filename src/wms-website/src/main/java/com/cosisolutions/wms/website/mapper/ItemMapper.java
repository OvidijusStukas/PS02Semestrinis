package com.cosisolutions.wms.website.mapper;

import com.cosisolutions.wms.website.entity.ItemEntity;
import com.cosisolutions.wms.website.models.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements IMapper<ItemEntity, ItemModel> {
  @Autowired
  private AssetMapper assetMapper;
  @Autowired
  private ItemGroupMapper itemGroupMapper;

  @Override
  public void toModel(ItemModel itemModel, ItemEntity itemEntity) {
    if(itemEntity == null) return;

    itemModel.setId(itemEntity.getId());
    itemModel.setCode(itemEntity.getCode());
    itemModel.setName(itemEntity.getName());
    itemModel.setCount(itemEntity.getCount());
    itemModel.setDescription(itemEntity.getDescription());

    assetMapper.toModel(itemModel.getAsset(), itemEntity.getAsset());
    itemGroupMapper.toModel(itemModel.getGroup(), itemEntity.getGroup());
  }

  @Override
  public void toEntity(ItemEntity itemEntity, ItemModel itemModel) {
    if(itemModel == null) return;

    itemEntity.setCode(itemModel.getCode());
    itemEntity.setName(itemModel.getName());
    itemEntity.setCount(itemModel.getCount());
    itemEntity.setDescription(itemModel.getDescription());

    if(itemModel.getId() > 0)
      itemEntity.setId(itemModel.getId());

    assetMapper.toEntity(itemEntity.getAsset(), itemModel.getAsset());
    itemGroupMapper.toEntity(itemEntity.getGroup(), itemModel.getGroup());
  }
}
