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
  public void toModel(ItemModel model, ItemEntity entity) {
    if(model == null || entity == null) return;

    model.setId(entity.getId());
    model.setCode(entity.getCode());
    model.setName(entity.getName());
    model.setCount(entity.getCount());
    model.setDescription(entity.getDescription());

    assetMapper.toModel(model.getAsset(), entity.getAsset());
    itemGroupMapper.toModel(model.getGroup(), entity.getGroup());
  }

  @Override
  public void toEntity(ItemEntity entity, ItemModel model) {
    if(entity == null || model == null) return;

    entity.setCode(model.getCode());
    entity.setName(model.getName());
    entity.setCount(model.getCount());
    entity.setDescription(model.getDescription());

    if(model.getId() > 0)
      entity.setId(model.getId());

    assetMapper.toEntity(entity.getAsset(), model.getAsset());
    itemGroupMapper.toEntity(entity.getGroup(), model.getGroup());
  }
}
